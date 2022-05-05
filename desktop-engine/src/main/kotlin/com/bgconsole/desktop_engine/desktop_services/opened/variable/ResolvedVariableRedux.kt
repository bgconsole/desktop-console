package com.bgconsole.desktop_engine.desktop_services.opened.variable

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.desktop_engine.utils.VariableReplacerUtils
import com.bgconsole.domain.Environment
import com.bgconsole.domain.Variable

const val ENGINE_OPENED_RESOLVED_VARIABLES = "engine.opened.resolved.variables"

class ResolvedVariableRedux(store: Store) {

    class ResolveVariables(val environments: List<Environment>) : Action(ENGINE_OPENED_RESOLVED_VARIABLES)
    class ResolveVariablesSucceeded(val variables: List<Variable>) : Action(ENGINE_OPENED_RESOLVED_VARIABLES)

    init {
        store.registerService(ResolvedVariablesService())
        store.registerReducer(ResolvedVariableReducer())
        store.addToStore(ENGINE_OPENED_RESOLVED_VARIABLES, ResolvedVariableContent.default())
    }

    private class ResolvedVariableReducer : Reducer<ResolvedVariableContent> {
        override fun getKey(): String = ENGINE_OPENED_RESOLVED_VARIABLES

        override fun reduce(store: Store, action: Action): ResolvedVariableContent {
            val current = store.get(ENGINE_OPENED_RESOLVED_VARIABLES) as ResolvedVariableContent
            return when (action) {
                is ResolveVariablesSucceeded -> current.copy(variables = action.variables)
                else -> current
            }
        }

    }

    private class ResolvedVariablesService : Service {
        override fun getKey(): String = ENGINE_OPENED_RESOLVED_VARIABLES

        override fun execute(store: Store, action: Action) {
            when (action) {
                is ResolveVariables -> resolveVariables(store, action)
            }
        }

        private fun resolveVariables(store: Store, action: ResolveVariables) {
            val variables = mutableListOf<Variable>()
            action.environments.forEach { environment ->
                environment.variables?.forEach { variable ->
                    if (variables.find { variable.name == it.name } == null) {
                        variables.add(variable)
                    }
                }
            }
            val newList = replaceVariableInsideValue(variables)
            store.dispatch(ResolveVariablesSucceeded(newList))
        }

        private fun replaceVariableInsideValue(variables: List<Variable>): List<Variable> {
            val oldList = variables.toMutableList()
            val newList = mutableListOf<Variable>()
            var i = 0
            val maxAttempts = variables.size * 10 // Just to avoid infinite loops
            while (oldList.isNotEmpty() && i < maxAttempts) {
                val first = oldList.first()
                if (!first.value.contains("\${")) {
                    newList.add(oldList.removeFirst())
                } else {
                    val newVal = VariableReplacerUtils.replaceVariable(first.value, newList)
                    if (!newVal.contains("\${")) {
                        newList.add(oldList.removeFirst().copy(value = newVal))
                    }
                }
                i++
            }
            newList.addAll(oldList)
            return newList
        }

    }
}

data class ResolvedVariableContent(val variables: List<Variable>) {
    companion object {
        fun default() = ResolvedVariableContent(emptyList())
    }
}