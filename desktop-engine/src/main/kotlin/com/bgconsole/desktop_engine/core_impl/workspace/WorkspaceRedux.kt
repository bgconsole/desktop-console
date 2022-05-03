package com.bgconsole.desktop_engine.core_impl.workspace

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Workspace

const val ENGINE_USER_SESSION_WORKSPACE = "engine.user.session.workspace"

class WorkspaceRedux {

    class LoadWorkspacesByProfile(val profileId: String) : Action(ENGINE_USER_SESSION_WORKSPACE)
    class LoadWorkspacesSucceeded(val workspaces: List<Workspace>) : Action(ENGINE_USER_SESSION_WORKSPACE)
}

typealias LoadWorkspacesByProfile = WorkspaceRedux.LoadWorkspacesByProfile
typealias LoadWorkspacesSucceeded = WorkspaceRedux.LoadWorkspacesSucceeded