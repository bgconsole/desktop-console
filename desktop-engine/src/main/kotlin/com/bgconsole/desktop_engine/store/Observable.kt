package com.bgconsole.desktop_engine.store

interface Observable<T> {

    fun complete(t: T)

    fun error(code: String, message: String, trace: String)
}