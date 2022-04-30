package com.bgconsole.desktop_engine.core_impl.project

import com.bgconsole.desktop_engine.desktop_services.ENGINE_CRUD_PROJECT
import com.bgconsole.desktop_engine.desktop_services.LoadProjectsSucceeded
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Project

class ProjectReducer : Reducer<List<Project>> {
    override fun getKey(): String {
        return ENGINE_CRUD_PROJECT
    }

    override fun reduce(store: Store, action: Action): List<Project> {
        return when (action) {
            is LoadProjectsSucceeded -> loadProjectsSucceeded(store, action)
            else -> store.get(ENGINE_CRUD_PROJECT) as List<Project>
        }
    }

    private fun loadProjectsSucceeded(store: Store, action: LoadProjectsSucceeded): List<Project> {
        return action.projects
    }
}