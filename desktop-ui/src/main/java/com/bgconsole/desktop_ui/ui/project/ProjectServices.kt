package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_engine.core_impl.aggregate.CoreAggregateServiceImpl
import com.bgconsole.desktop_engine.core_impl.environment.CoreEnvironmentServiceImpl
import com.bgconsole.desktop_engine.core_impl.version.CoreVersionServiceImpl
import com.bgconsole.desktop_engine.core_impl.version.EngineVersionService
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateRedux
import com.bgconsole.desktop_engine.desktop_services.opened.environment.OpenedEnvironmentRedux
import com.bgconsole.desktop_engine.desktop_services.opened.variable.ResolvedVariableRedux
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionRedux
import com.bgconsole.platform.store.Store

class ProjectServices(store: Store) {

    private val aggregateServices = CoreAggregateServiceImpl()
    private val environmentServices = CoreEnvironmentServiceImpl()

    private val coreVersionService = CoreVersionServiceImpl()
    private val versionService = EngineVersionService(coreVersionService)

    init {
        OpenedVersionRedux(store)
        OpenedAggregateRedux(store, aggregateServices)
        OpenedEnvironmentRedux(store, environmentServices)
        ResolvedVariableRedux(store)

        store.registerService(versionService)
    }
}