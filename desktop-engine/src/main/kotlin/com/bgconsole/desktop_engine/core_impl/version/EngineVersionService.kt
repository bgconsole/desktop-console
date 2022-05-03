package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.core.version.VersionService
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionRedux
import com.bgconsole.desktop_engine.store.Action
import com.bgconsole.desktop_engine.store.Service
import com.bgconsole.desktop_engine.store.Store

class EngineVersionService(private val versionService: VersionService) : Service {
    override fun getKey(): String = ENGINE_USER_SESSION_VERSION

    override fun execute(store: Store, action: Action) {
        when (action) {
            is VersionRedux.LoadVersionsByProject -> action.project.location?.let {
                versionService.findByProjectLocation(it).let { list ->
                    store.dispatch(OpenedVersionRedux.LoadVersionsSucceeded(action.project.id, list))
                }
            }
        }
    }
}