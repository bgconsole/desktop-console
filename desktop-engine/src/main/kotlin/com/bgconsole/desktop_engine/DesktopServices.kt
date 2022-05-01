package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.desktop_services.opened.project.OpenedProjectRedux
import com.bgconsole.desktop_engine.store.Store

class DesktopServices(store: Store) {

    init {
        OpenedProjectRedux(store)
    }
}