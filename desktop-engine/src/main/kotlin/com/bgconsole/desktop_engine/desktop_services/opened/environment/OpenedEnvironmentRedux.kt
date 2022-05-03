package com.bgconsole.desktop_engine.desktop_services.opened.environment

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Environment

const val ENGINE_OPENED_AGGREGATE = "engine.opened.aggregate"

class OpenedEnvironmentRedux(store: Store) {

    class LoadEnvironment(val versionId: String, val environments: List<List<Environment>>) : Action(ENGINE_OPENED_AGGREGATE)
    class CloseProject(val versionId: String) : Action(ENGINE_OPENED_AGGREGATE)

    init {
        store.addToStore(ENGINE_OPENED_AGGREGATE, OpenedEnvironmentContent.default())
        store.registerReducer(OpenedEnvironmentReducer())
    }

    private class OpenedEnvironmentReducer : Reducer<OpenedEnvironmentContent> {
        override fun getKey(): String = ENGINE_OPENED_AGGREGATE

        override fun reduce(store: Store, action: Action): OpenedEnvironmentContent {
            val current = store.get(ENGINE_OPENED_AGGREGATE) as OpenedEnvironmentContent
            return when (action) {
                is LoadEnvironment -> current.copy(environments = current.environments + (action.versionId to action.environments))
                is CloseProject -> current.copy(environments = current.environments.filter { it.key != action.versionId })
                else -> current
            }
        }

    }

}

data class OpenedEnvironmentContent(val environments: Map<String, List<List<Environment>>>) {
    companion object {
        fun default(): OpenedEnvironmentContent = OpenedEnvironmentContent(emptyMap())
    }
}