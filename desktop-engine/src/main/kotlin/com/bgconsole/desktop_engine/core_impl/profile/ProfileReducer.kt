package com.bgconsole.desktop_engine.core_impl.profile

import com.bgconsole.desktop_engine.desktop_services.ENGINE_CRUD_PROFILE
import com.bgconsole.desktop_engine.desktop_services.LoadProfileSucceeded
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Profile

class ProfileReducer : Reducer<List<Profile>> {
    override fun getKey(): String {
        return ENGINE_CRUD_PROFILE
    }

    override fun reduce(store: Store, action: Action): List<Profile> {
        return when (action) {
            is LoadProfileSucceeded -> loadProfileSucceededReducer(store, action)
            else -> emptyList()
        }
    }

    private fun loadProfileSucceededReducer(store: Store, action: LoadProfileSucceeded): List<Profile> {
        return action.profiles
    }
}