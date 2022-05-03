package com.bgconsole.desktop_engine.core_impl.project

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Location
import com.bgconsole.domain.Project

const val ENGINE_USER_SESSION_PROJECT = "engine.user.session.project"

class ProjectRedux {

    class LoadProjectsByWorkspace(val workspaceLocation: Location) : Action(ENGINE_USER_SESSION_PROJECT)
    class LoadProjectsSucceeded(val projects: List<Project>) : Action(ENGINE_USER_SESSION_PROJECT)
}

typealias LoadProjectsByWorkspace = ProjectRedux.LoadProjectsByWorkspace
typealias LoadProjectsSucceeded = ProjectRedux.LoadProjectsSucceeded