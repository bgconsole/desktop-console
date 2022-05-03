package com.bgconsole.desktop_engine.core_impl.profile

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Profile

const val ENGINE_USER_SESSION_PROFILE = "engine.user.session.profile"

class ProfileRedux {

    class LoadProfiles : Action(ENGINE_USER_SESSION_PROFILE)
    class LoadProfilesSucceeded(val profiles: List<Profile>) : Action(ENGINE_USER_SESSION_PROFILE)

    class SaveProfile(val profile: Profile) : Action(ENGINE_USER_SESSION_PROFILE)
    class SaveProfileSucceeded() : Action(ENGINE_USER_SESSION_PROFILE)
}

typealias LoadProfile = ProfileRedux.LoadProfiles
typealias LoadProfileSucceeded = ProfileRedux.LoadProfilesSucceeded

typealias SaveProfile = ProfileRedux.SaveProfile
typealias SaveProfileSucceeded = ProfileRedux.SaveProfileSucceeded