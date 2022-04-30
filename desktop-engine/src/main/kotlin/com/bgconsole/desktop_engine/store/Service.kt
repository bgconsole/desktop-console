package com.bgconsole.desktop_engine.store

interface Service {

    fun getKey(): String

    fun execute(store: Store, action: Action)
}