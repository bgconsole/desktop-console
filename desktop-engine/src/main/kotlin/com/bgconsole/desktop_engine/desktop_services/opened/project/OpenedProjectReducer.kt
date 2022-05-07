package com.bgconsole.desktop_engine.desktop_services.opened.project

import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Reducer
import com.bgconsole.platform.store.Store

class OpenedProjectReducer : Reducer<OpenedProjectContent> {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_OPENED_PROJECTS
    }

    override fun reduce(store: Store, action: Action): OpenedProjectContent {
        val current = store.get(ENGINE_USER_SESSION_OPENED_PROJECTS) as OpenedProjectContent
        return when (action) {
            is OpenedProjectRedux.OpenProject -> openProject(store, action, current)
            is OpenedProjectRedux.CloseProject -> closeProject(store, action, current)
            is OpenedProjectRedux.AddAvailableInstructions -> addInstructions(store, action, current)
            else -> current
        }
    }

    private fun addInstructions(
        store: Store,
        action: OpenedProjectRedux.AddAvailableInstructions,
        current: OpenedProjectContent
    ): OpenedProjectContent {
        return current.copy(availableInstructions = current.availableInstructions + action.instructions)
    }

    private fun closeProject(
        store: Store,
        action: OpenedProjectRedux.CloseProject,
        current: OpenedProjectContent
    ): OpenedProjectContent {
        return current.copy(
            projects = current.projects.filter { it.id != action.project.id },
            availableInstructions = current.availableInstructions.filter { it.project.id != action.project.id }
        )
    }

    private fun openProject(
        store: Store,
        action: OpenedProjectRedux.OpenProject,
        current: OpenedProjectContent
    ): OpenedProjectContent {
        return current.copy(projects = current.projects + action.project)
    }

}