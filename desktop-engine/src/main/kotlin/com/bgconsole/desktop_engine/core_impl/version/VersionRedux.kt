package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.domain.Location
import com.bgconsole.domain.Project
import com.bgconsole.domain.Version

const val ENGINE_USER_SESSION_VERSION = "engine.user.session.version"

class VersionRedux {

    class LoadVersionsByProject(val project: Project) : Action(ENGINE_USER_SESSION_VERSION)
    class LoadVersionsSucceeded(val versions: List<Version>) : Action(ENGINE_USER_SESSION_VERSION)
}
