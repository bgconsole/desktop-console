package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.core_impl.aggregate.CoreAggregateServiceImpl
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateRedux
import com.bgconsole.desktop_engine.desktop_services.opened.environment.OpenedEnvironmentRedux
import com.bgconsole.desktop_engine.desktop_services.opened.project.OpenedProjectRedux
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionRedux
import com.bgconsole.desktop_engine.store.Store

class DesktopServices(store: Store) {

    private val aggregateServices = CoreAggregateServiceImpl()

    init {
        OpenedProjectRedux(store)
        OpenedVersionRedux(store)
        OpenedAggregateRedux(store, aggregateServices)
        OpenedEnvironmentRedux(store)
    }
}