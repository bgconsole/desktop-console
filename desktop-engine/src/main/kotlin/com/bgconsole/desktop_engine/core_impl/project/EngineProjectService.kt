package com.bgconsole.desktop_engine.core_impl.project

import com.bgconsole.core.project.ProjectService
import com.bgconsole.core.workspace.WorkspaceService
import com.bgconsole.desktop_engine.desktop_services.ENGINE_USER_SESSION_PROJECT
import com.bgconsole.desktop_engine.desktop_services.LoadProjectsByWorkspace
import com.bgconsole.desktop_engine.desktop_services.LoadProjectsSucceeded
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store

class EngineProjectService(
    private val workspaceService: WorkspaceService,
    private val projectService: ProjectService
) : Service {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_PROJECT
    }

    override fun execute(store: Store, action: Action) {
        when (action) {
            is LoadProjectsByWorkspace -> loadProjectByWorkspace(store, action)
        }
    }

    private fun loadProjectByWorkspace(store: Store, action: LoadProjectsByWorkspace) {
        projectService.findByWorkspaceLocation(action.workspaceLocation)
            .let { store.dispatch(LoadProjectsSucceeded(it)) }
    }
}