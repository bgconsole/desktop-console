package com.bgconsole.desktop_engine.store

interface Subscriber {

    fun update(entity: Any)
}