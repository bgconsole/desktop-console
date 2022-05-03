package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateRedux
import com.bgconsole.desktop_engine.desktop_services.opened.environment.OpenedEnvironmentRedux
import com.bgconsole.desktop_engine.desktop_services.opened.project.OpenedProjectRedux
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionRedux
import com.bgconsole.desktop_engine.store.Store

class DesktopServices(store: Store) {

    init {
        OpenedProjectRedux(store)
        OpenedVersionRedux(store)
        OpenedAggregateRedux(store)
        OpenedEnvironmentRedux(store)
    }
}