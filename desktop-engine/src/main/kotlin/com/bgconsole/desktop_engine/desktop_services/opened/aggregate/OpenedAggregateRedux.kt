package com.bgconsole.desktop_engine.desktop_services.opened.aggregate

import com.bgconsole.core.aggregate.AggregateService
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Aggregate
import com.bgconsole.domain.Location
import com.bgconsole.domain.Version
import java.nio.file.Paths

const val ENGINE_OPENED_AGGREGATE = "engine.opened.aggregate"

class OpenedAggregateRedux(
    store: Store,
    aggregateService: AggregateService
) {

    class LoadAggregates(val version: Version) : Action(ENGINE_OPENED_AGGREGATE)
    class LoadAggregatesSucceeded(val versionId: String, val aggregates: List<Aggregate>) :
        Action(ENGINE_OPENED_AGGREGATE)

    class CloseProject(val versionId: String) : Action(ENGINE_OPENED_AGGREGATE)

    init {
        store.addToStore(ENGINE_OPENED_AGGREGATE, OpenedAggregateContent.default())
        store.registerReducer(OpenedAggregationReducer())
        store.registerService(OpenedAggregationService(aggregateService))
    }

    private class OpenedAggregationReducer : Reducer<OpenedAggregateContent> {
        override fun getKey(): String = ENGINE_OPENED_AGGREGATE

        override fun reduce(store: Store, action: Action): OpenedAggregateContent {
            val current = store.get(ENGINE_OPENED_AGGREGATE) as OpenedAggregateContent
            return when (action) {
                is LoadAggregatesSucceeded -> current.copy(aggregates = current.aggregates + (action.versionId to action.aggregates))
                is CloseProject -> current.copy(aggregates = current.aggregates.filter { it.key != action.versionId })
                else -> current
            }
        }
    }

    private class OpenedAggregationService(val aggregateService: AggregateService) : Service {
        override fun getKey(): String = ENGINE_OPENED_AGGREGATE

        override fun execute(store: Store, action: Action) {
            when (action) {
                is LoadAggregates -> loadAggregates(store, action)
            }
        }

        private fun loadAggregates(store: Store, action: LoadAggregates) {
            val aggregates: List<Aggregate> = action.version.aggregates?.let { aggregates ->
                aggregates
                    .mapNotNull {
                        it.location?.let { location ->
                            aggregateService.findByLocation(
                                absoluteLocation(
                                    action.version,
                                    location
                                )
                            )
                        }
                    }
            }.orEmpty()
            store.dispatch(LoadAggregatesSucceeded(action.version.id, aggregates))
        }

        private fun absoluteLocation(version: Version, location: Location): Location {
            val newLocation = Paths.get(version.location?.location.orEmpty(), location.location).toString()
            return location.copy(location = newLocation)
        }

    }
}

data class OpenedAggregateContent(val aggregates: Map<String, List<Aggregate>>) {
    companion object {
        fun default(): OpenedAggregateContent = OpenedAggregateContent(emptyMap())
    }
}