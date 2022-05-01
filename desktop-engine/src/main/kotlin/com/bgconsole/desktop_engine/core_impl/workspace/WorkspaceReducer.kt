package com.bgconsole.desktop_engine.core_impl.workspace

import com.bgconsole.desktop_engine.desktop_services.ENGINE_USER_SESSION_WORKSPACE
import com.bgconsole.desktop_engine.desktop_services.LoadWorkspacesSucceeded
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Workspace

class WorkspaceReducer : Reducer<List<Workspace>> {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_WORKSPACE
    }

    override fun reduce(store: Store, action: Action): List<Workspace> {
        return when (action) {
            is LoadWorkspacesSucceeded -> loadWorkspaceSucceeded(store, action)
            else -> store.get(ENGINE_USER_SESSION_WORKSPACE) as List<Workspace>
        }
    }

    private fun loadWorkspaceSucceeded(store: Store, action: LoadWorkspacesSucceeded): List<Workspace> {
        return action.workspaces
    }
}