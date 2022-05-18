package com.bgconsole.desktop_engine

import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.store.Store

class Engine(val location: Location) {

    private val store: Store = Store()

    init {
        CoreServices(store)
    }

    fun getStore(): Store {
        return store
    }

}