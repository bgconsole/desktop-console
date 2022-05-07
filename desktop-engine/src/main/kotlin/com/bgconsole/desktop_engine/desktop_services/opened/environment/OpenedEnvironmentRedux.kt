package com.bgconsole.desktop_engine.desktop_services.opened.environment

import com.bgconsole.core.environment.EnvironmentService
import com.bgconsole.desktop_engine.desktop_services.opened.variable.ResolvedVariableRedux
import com.bgconsole.domain.Environment
import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Reducer
import com.bgconsole.platform.store.Service
import com.bgconsole.platform.store.Store
import java.nio.file.Paths

const val ENGINE_OPENED_ENVIRONMENT = "engine.opened.environment"

class OpenedEnvironmentRedux(
    store: Store,
    environmentService: EnvironmentService
) {

    class LoadEnvironments(val version: Version) : Action(ENGINE_OPENED_ENVIRONMENT)
    class LoadEnvironmentsSucceeded(val versionId: String, val environments: List<Environment>) :
        Action(ENGINE_OPENED_ENVIRONMENT)

    class CloseProject(val versionId: String) : Action(ENGINE_OPENED_ENVIRONMENT)

    init {
        store.addToStore(ENGINE_OPENED_ENVIRONMENT, OpenedEnvironmentContent.default())
        store.registerReducer(OpenedEnvironmentReducer())
        store.registerService(OpenedEnvironmentService(environmentService))
    }

    private class OpenedEnvironmentReducer : Reducer<OpenedEnvironmentContent> {
        override fun getKey(): String = ENGINE_OPENED_ENVIRONMENT

        override fun reduce(store: Store, action: Action): OpenedEnvironmentContent {
            val current = store.get(ENGINE_OPENED_ENVIRONMENT) as OpenedEnvironmentContent
            return when (action) {
                is LoadEnvironmentsSucceeded -> current.copy(environments = current.environments + (action.versionId to action.environments))
                is CloseProject -> current.copy(environments = current.environments.filter { it.key != action.versionId })
                else -> current
            }
        }

    }

    private class OpenedEnvironmentService(val environmentService: EnvironmentService) : Service {
        override fun getKey(): String = ENGINE_OPENED_ENVIRONMENT

        override fun execute(store: Store, action: Action) {
            when (action) {
                is LoadEnvironments -> loadEnvironments(store, action)
                is LoadEnvironmentsSucceeded -> store.dispatch(ResolvedVariableRedux.ResolveVariables(action.environments))
            }
        }

        private fun loadEnvironments(store: Store, action: LoadEnvironments) {
            val environments: List<Environment> = action.version.environments?.let { environments ->
                environments.mapNotNull {
                    it.location?.let { location ->
                        environmentService.findByLocation(absoluteLocation(action.version, location))
                    }
                }
            }.orEmpty()
            store.dispatch(LoadEnvironmentsSucceeded(action.version.id, environments))
        }

        private fun absoluteLocation(version: Version, location: Location): Location {
            val newLocation = Paths.get(version.location?.location.orEmpty(), location.location).toString()
            return location.copy(location = newLocation)
        }
    }
}

data class OpenedEnvironmentContent(
    val environments: Map<String, List<Environment>>
) {
    companion object {
        fun default(): OpenedEnvironmentContent = OpenedEnvironmentContent(emptyMap())
    }
}