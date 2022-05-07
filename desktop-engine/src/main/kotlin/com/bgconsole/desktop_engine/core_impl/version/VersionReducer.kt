package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.domain.Version
import com.bgconsole.platform.store.Action
import com.bgconsole.platform.store.Reducer
import com.bgconsole.platform.store.Store

class VersionReducer : Reducer<List<Version>> {
    override fun getKey(): String = ENGINE_USER_SESSION_VERSION

    override fun reduce(store: Store, action: Action): List<Version> {
        return when (action) {
            is VersionRedux.LoadVersionsSucceeded -> action.versions
            else -> store.get(ENGINE_USER_SESSION_VERSION) as List<Version>
        }
    }
}