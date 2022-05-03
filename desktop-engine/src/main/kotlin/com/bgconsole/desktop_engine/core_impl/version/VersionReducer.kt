package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Reducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Version

class VersionReducer : Reducer<List<Version>> {
    override fun getKey(): String = ENGINE_USER_SESSION_VERSION

    override fun reduce(store: Store, action: Action): List<Version> {
        return when (action) {
            is VersionRedux.LoadVersionsSucceeded -> action.versions
            else -> store.get(ENGINE_USER_SESSION_VERSION) as List<Version>
        }
    }
}