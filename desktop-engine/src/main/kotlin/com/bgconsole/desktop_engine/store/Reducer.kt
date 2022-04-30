package com.bgconsole.desktop_engine.store

interface Reducer<T> {

    fun getKey(): String

    fun reduce(store: Store, action: Action): T
}