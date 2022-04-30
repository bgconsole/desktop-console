package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Location

class Engine(val location: Location) {

    private val store: Store = Store()

    init {
        CoreServices(store)
    }

    fun getStore(): Store {
        return store
    }

}