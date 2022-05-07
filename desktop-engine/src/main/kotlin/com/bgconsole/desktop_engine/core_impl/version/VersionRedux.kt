package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Action

const val ENGINE_USER_SESSION_VERSION = "engine.user.session.version"

class VersionRedux {

    class LoadVersionsByProject(val project: Project) : Action(ENGINE_USER_SESSION_VERSION)
    class LoadVersionsSucceeded(val versions: List<Version>) : Action(ENGINE_USER_SESSION_VERSION)
}
