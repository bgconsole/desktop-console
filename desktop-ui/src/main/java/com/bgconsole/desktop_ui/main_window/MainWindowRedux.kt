package com.bgconsole.desktop_ui.main_window

import com.bgconsole.desktop_engine.core_impl.project.LoadProjectsByWorkspace
import com.bgconsole.desktop_engine.core_impl.workspace.LoadWorkspacesByProfile
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.desktop_ui.AppData
import com.bgconsole.domain.Profile
import com.bgconsole.domain.Workspace

const val UI_MAIN_WINDOW = "ui.main_window"

class MainWindowRedux {

    private val store: Store = AppData.instance.store

    class SelectProfile(val profile: Profile) : Action(UI_MAIN_WINDOW)
    class SelectWorkspace(val workspace: Workspace) : Action(UI_MAIN_WINDOW)

    init {
        store.registerReducer(MainWindowReducer())
        store.registerService(MainWindowService())
        store.addToStore(UI_MAIN_WINDOW, MainWindowContent.default())
    }

    private class MainWindowReducer : Reducer<MainWindowContent> {
        override fun getKey(): String {
            return UI_MAIN_WINDOW
        }

        override fun reduce(store: Store, action: Action): MainWindowContent {
            val current = store.get(UI_MAIN_WINDOW) as MainWindowContent
            return when (action) {
                is SelectProfile -> selectProfile(store, action, current)
                is SelectWorkspace -> selectWorkspace(store, action, current)
                else -> current
            }
        }

        private fun selectProfile(store: Store, action: SelectProfile, current: MainWindowContent): MainWindowContent {
            return current.copy(selectedProfile = action.profile)
        }

        private fun selectWorkspace(
            store: Store,
            action: SelectWorkspace,
            current: MainWindowContent
        ): MainWindowContent {
            return current.copy(selectedWorkspace = action.workspace)
        }

    }

    private class MainWindowService : Service {
        override fun getKey(): String {
            return UI_MAIN_WINDOW
        }

        override fun execute(store: Store, action: Action) {
            when (action) {
                is SelectProfile -> selectProfile(store, action)
                is SelectWorkspace -> selectWorkspace(store, action)
            }
        }

        private fun selectWorkspace(store: Store, action: SelectWorkspace) {
            action.workspace.location?.let { store.dispatch(LoadProjectsByWorkspace(it)) }
        }


        private fun selectProfile(store: Store, action: SelectProfile) {
            store.dispatch(LoadWorkspacesByProfile(action.profile.id))
        }

    }
}