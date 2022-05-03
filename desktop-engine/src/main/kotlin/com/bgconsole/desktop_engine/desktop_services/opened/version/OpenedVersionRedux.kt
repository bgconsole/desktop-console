package com.bgconsole.desktop_engine.desktop_services.opened.version

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Version

const val ENGINE_OPENED_VERSION = "engine.opened.version"

class OpenedVersionRedux(store: Store) {

    class LoadVersionsSucceeded(val projectId: String, val versions: List<Version>) : Action(ENGINE_OPENED_VERSION)
    class CloseProject(val projectId: String) : Action(ENGINE_OPENED_VERSION)

    init {
        store.registerReducer(OpenedVersionReducer())
        store.addToStore(ENGINE_OPENED_VERSION, OpenedVersionContent.default())
    }

    private class OpenedVersionReducer : Reducer<OpenedVersionContent> {
        override fun getKey(): String {
            return ENGINE_OPENED_VERSION
        }

        override fun reduce(store: Store, action: Action): OpenedVersionContent {
            val current = store.get(ENGINE_OPENED_VERSION) as OpenedVersionContent
            return when (action) {
                is LoadVersionsSucceeded -> current.copy(versions = current.versions + (action.projectId to action.versions))
                is CloseProject -> current.copy(versions = current.versions.filter { it.key != action.projectId })
                else -> current
            }
        }
    }

}