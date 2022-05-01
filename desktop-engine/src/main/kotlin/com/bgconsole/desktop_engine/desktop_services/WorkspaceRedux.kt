package com.bgconsole.desktop_engine.desktop_services

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Workspace

const val ENGINE_USER_SESSION_WORKSPACE = "engine.user.session.workspace"

class WorkspaceRedux {

    class LoadWorkspacesByProfile(val profileId: String) : Action()
    class LoadWorkspacesSucceeded(val workspaces: List<Workspace>) : Action()
}

typealias LoadWorkspacesByProfile = WorkspaceRedux.LoadWorkspacesByProfile
typealias LoadWorkspacesSucceeded = WorkspaceRedux.LoadWorkspacesSucceeded