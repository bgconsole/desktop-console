package com.bgconsole.desktop_engine.desktop_services.opened.aggregate

import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionContent
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Aggregate

const val ENGINE_OPENED_AGGREGATE = "engine.opened.aggregate"

class OpenedAggregateRedux(store: Store) {

    class LoadAggregate(val versionId: String, val aggregates: List<List<Aggregate>>) : Action(ENGINE_OPENED_AGGREGATE)
    class CloseProject(val versionId: String) : Action(ENGINE_OPENED_AGGREGATE)

    init {
        store.addToStore(ENGINE_OPENED_AGGREGATE, OpenedAggregateContent.default())
        store.registerReducer(OpenedAggregationReducer())
    }

    private class OpenedAggregationReducer : Reducer<OpenedAggregateContent> {
        override fun getKey(): String = ENGINE_OPENED_AGGREGATE

        override fun reduce(store: Store, action: Action): OpenedAggregateContent {
            val current = store.get(ENGINE_OPENED_AGGREGATE) as OpenedAggregateContent
            return when (action) {
                is LoadAggregate -> current.copy(aggregates = current.aggregates + (action.versionId to action.aggregates))
                is CloseProject -> current.copy(aggregates = current.aggregates.filter { it.key != action.versionId })
                else -> current
            }
        }

    }
}

data class OpenedAggregateContent(val aggregates: Map<String, List<List<Aggregate>>>) {
    companion object {
        fun default(): OpenedVersionContent = OpenedVersionContent(emptyMap())
    }
}