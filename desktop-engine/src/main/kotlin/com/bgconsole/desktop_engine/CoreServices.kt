package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.core_impl.profile.CoreProfileServiceImpl
import com.bgconsole.desktop_engine.core_impl.profile.EngineProfileService
import com.bgconsole.desktop_engine.core_impl.profile.ProfileReducer
import com.bgconsole.desktop_engine.core_impl.workspace.CoreWorkspaceServiceImpl
import com.bgconsole.desktop_engine.core_impl.workspace.EngineWorkspaceService
import com.bgconsole.desktop_engine.core_impl.workspace.WorkspaceReducer
import com.bgconsole.desktop_engine.desktop_services.ENGINE_CRUD_PROFILE
import com.bgconsole.desktop_engine.desktop_services.ENGINE_CRUD_WORKSPACE
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Profile
import com.bgconsole.domain.Workspace

class CoreServices(store: Store) {

    private val coreProfileService = CoreProfileServiceImpl()
    private val profileService = EngineProfileService(coreProfileService)

    private val coreWorkspaceService = CoreWorkspaceServiceImpl()
    private val workspaceService = EngineWorkspaceService(coreProfileService, coreWorkspaceService)

    init {
        store.registerServices(
            listOf(
                profileService,
                workspaceService
            )
        )
        store.registerReducers(
            listOf(
                ProfileReducer(),
                WorkspaceReducer()
            )
        )
        store.addToStore(ENGINE_CRUD_PROFILE, emptyList<Profile>())
        store.addToStore(ENGINE_CRUD_WORKSPACE, emptyList<Workspace>())

    }
}