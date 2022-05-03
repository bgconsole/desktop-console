package com.bgconsole.desktop_engine.core_impl.project

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Project

class ProjectReducer : Reducer<List<Project>> {
    override fun getKey(): String {
        return ENGINE_USER_SESSION_PROJECT
    }

    override fun reduce(store: Store, action: Action): List<Project> {
        return when (action) {
            is LoadProjectsSucceeded -> action.projects
            else -> store.get(ENGINE_USER_SESSION_PROJECT) as List<Project>
        }
    }

}