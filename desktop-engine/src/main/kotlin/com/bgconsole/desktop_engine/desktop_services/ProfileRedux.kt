package com.bgconsole.desktop_engine.desktop_services

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Profile

const val ENGINE_CRUD_PROFILE = "engine.crud.profile"

class ProfileRedux {

    class LoadProfiles : Action()
    class LoadProfilesSucceeded(val profiles: List<Profile>) : Action()

    class SaveProfile(val profile: Profile) : Action()
    class SaveProfileSucceeded() : Action()
}

typealias LoadProfile = ProfileRedux.LoadProfiles
typealias LoadProfileSucceeded = ProfileRedux.LoadProfilesSucceeded

typealias SaveProfile = ProfileRedux.SaveProfile
typealias SaveProfileSucceeded = ProfileRedux.SaveProfileSucceeded