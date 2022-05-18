package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.desktop_services.opened.project.OpenedProjectRedux
import com.bgconsole.platform.store.Store

class CoreServices(store: Store) {

//    private val coreVersionService = CoreVersionServiceImpl()


    init {
        store.registerServices(
            listOf(
//                versionService
            )
        )
        store.registerReducers(
            listOf(
//                VersionReducer()
            )
        )
        OpenedProjectRedux(store)
//        store.addToStore(ENGINE_USER_SESSION_VERSION, emptyList<>())
    }
}