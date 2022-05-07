package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_ui.AppData
import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Reducer
import com.bgconsole.platform.store.Service
import com.bgconsole.platform.store.Store

const val ENGINE_PERSPECTIVE_PROJECT = "engine.perspective.project"

class ProjectWindowRedux(title: String, project: Project) {

    private val store: Store = AppData.instance.store

    class RunCommand() : Action(ENGINE_PERSPECTIVE_PROJECT)
    class ChangeTitle(val title: String) : Action(ENGINE_PERSPECTIVE_PROJECT)
    class OpenTerminal() : Action(ENGINE_PERSPECTIVE_PROJECT)
    class CloseTerminal() : Action(ENGINE_PERSPECTIVE_PROJECT)
    class ChangeVersion(val version: Version) : Action(ENGINE_PERSPECTIVE_PROJECT)
    class OpenEditInstruction() : Action(ENGINE_PERSPECTIVE_PROJECT)
    class OpenEditVariable() : Action(ENGINE_PERSPECTIVE_PROJECT)

    init {
        store.addToStore(ENGINE_PERSPECTIVE_PROJECT, ProjectWindowContent.default(title, project))
        store.registerReducer(ProjectWindowReducer(ENGINE_PERSPECTIVE_PROJECT))
        store.registerService(ProjectWindowService(ENGINE_PERSPECTIVE_PROJECT))
    }

    private class ProjectWindowReducer(val storeKey: String) : Reducer<ProjectWindowContent> {
        override fun getKey(): String {
            return storeKey
        }

        override fun reduce(store: Store, action: Action): ProjectWindowContent {
            val current = store.get(storeKey) as ProjectWindowContent
            return when (action) {
                is RunCommand -> runCommand(store, action)
                is ChangeTitle -> changeTitle(store, action)
                is OpenTerminal -> openTerminal(store, action)
                is CloseTerminal -> closeTerminal(store, action)
                is ChangeVersion -> changeVersion(store, action)
                is OpenEditInstruction -> openEditInstruction(store, action)
                is OpenEditVariable -> openEditVariable(store, action)
                else -> current
            }
        }

        private fun openEditVariable(store: Store, action: OpenEditVariable): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun openEditInstruction(store: Store, action: OpenEditInstruction): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun changeVersion(store: Store, action: ChangeVersion): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun closeTerminal(store: Store, action: CloseTerminal): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun openTerminal(store: Store, action: OpenTerminal): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun changeTitle(store: Store, action: ChangeTitle): ProjectWindowContent {
            TODO("Not yet implemented")
        }

        private fun runCommand(store: Store, action: RunCommand): ProjectWindowContent {
            TODO("Not yet implemented")
        }

    }

    private class ProjectWindowService(val storeKey: String) : Service {
        override fun getKey(): String {
            return storeKey
        }

        override fun execute(store: Store, action: Action) {
            when (action) {
                is RunCommand -> runCommand(store, action)
            }
        }

        private fun runCommand(store: Store, action: RunCommand) {

        }

    }
}