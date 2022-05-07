package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.core_impl.version.CoreVersionServiceImpl
import com.bgconsole.desktop_engine.core_impl.version.EngineVersionService
import com.bgconsole.platform.store.Store

class CoreServices(store: Store) {

    private val coreVersionService = CoreVersionServiceImpl()
    private val versionService = EngineVersionService(coreVersionService)

    init {
        store.registerServices(
            listOf(
                versionService
            )
        )
        store.registerReducers(
            listOf(
//                VersionReducer()
            )
        )
//        store.addToStore(ENGINE_USER_SESSION_VERSION, emptyList<>())
    }
}