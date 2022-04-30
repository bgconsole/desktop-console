package com.bgconsole.desktop_engine.desktop_services

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Location
import com.bgconsole.domain.Project

const val ENGINE_CRUD_PROJECT = "engine.crud.project"

class ProjectRedux {

    class LoadProjectsByWorkspace(val workspaceLocation: Location) : Action()
    class LoadProjectsSucceeded(val projects: List<Project>) : Action()
}

typealias LoadProjectsByWorkspace = ProjectRedux.LoadProjectsByWorkspace
typealias LoadProjectsSucceeded = ProjectRedux.LoadProjectsSucceeded