package com.bgconsole.desktop_engine.core_impl.workspace

import com.bgconsole.core.profile.ProfileService
import com.bgconsole.core.workspace.WorkspaceService
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store

class EngineWorkspaceService(
    private val profileService: ProfileService,
    private val workspaceService: WorkspaceService
) : Service {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_WORKSPACE
    }

    override fun execute(store: Store, action: Action) {
        when (action) {
            is LoadWorkspacesByProfile -> loadWorkspacesByProfile(store, action)
        }
    }

    private fun loadWorkspacesByProfile(store: Store, action: LoadWorkspacesByProfile) {
        profileService.findById(action.profileId)?.let {
            it.locations?.map { location -> workspaceService.findByLocation(location) }?.let { workspaces ->
                store.dispatch(LoadWorkspacesSucceeded(workspaces))
            }
        }
    }
}