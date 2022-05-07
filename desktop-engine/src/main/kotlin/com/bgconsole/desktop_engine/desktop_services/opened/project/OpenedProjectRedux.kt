package com.bgconsole.desktop_engine.desktop_services.opened.project

import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Store

const val ENGINE_USER_SESSION_OPENED_PROJECTS = "engine.user.session.opened.projects"

class OpenedProjectRedux(store: Store) {

    class OpenProject(val project: Project) : Action(ENGINE_USER_SESSION_OPENED_PROJECTS)
    class CloseProject(val project: Project) : Action(ENGINE_USER_SESSION_OPENED_PROJECTS)
    class AddAvailableInstructions(val instructions: List<AvailableInstruction>) :
        Action(ENGINE_USER_SESSION_OPENED_PROJECTS)

    init {
        store.registerReducer(OpenedProjectReducer())
        store.registerService(OpenedProjectService())
        store.addToStore(ENGINE_USER_SESSION_OPENED_PROJECTS, OpenedProjectContent.default())
    }

}