package com.bgconsole.desktop_engine.core_impl.profile

import com.bgconsole.core.profile.ProfileService
import com.bgconsole.desktop_engine.desktop_services.*
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store

class EngineProfileService(private val profileService: ProfileService) : Service {

    override fun getKey(): String {
        return ENGINE_CRUD_PROFILE
    }

    override fun execute(store: Store, action: Action) {
        when (action) {
            is LoadProfile -> loadProfiles(store, action)
            is SaveProfile -> saveProfile(store, action)
        }
    }

    private fun saveProfile(store: Store, action: SaveProfile) {
        profileService.add(action.profile)
        store.dispatch(SaveProfileSucceeded())
        store.dispatch(LoadProfile())
    }

    private fun loadProfiles(store: Store, action: LoadProfile) {
        val profiles = profileService.findAll()
        store.dispatch(LoadProfileSucceeded(profiles))
    }
}