package com.bgconsole.desktop_engine.desktop_services.opened.project

import com.bgconsole.desktop_engine.core_impl.version.VersionRedux
import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Service
import com.bgconsole.platform.store.Store

class OpenedProjectService : Service {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_OPENED_PROJECTS
    }

    override fun execute(store: Store, action: Action) {
        when (action) {
            is OpenedProjectRedux.OpenProject -> openProject(store, action)
        }
    }

    private fun openProject(store: Store, action: OpenedProjectRedux.OpenProject) {
//        action.project.location?.let { store.dispatch(VersionRedux.LoadVersionsByProject(action.project)) }
    }
}