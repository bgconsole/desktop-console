package com.bgconsole.desktop_engine

import com.bgconsole.desktop_engine.core_impl.profile.CoreProfileServiceImpl
import com.bgconsole.desktop_engine.core_impl.profile.ENGINE_USER_SESSION_PROFILE
import com.bgconsole.desktop_engine.core_impl.profile.EngineProfileService
import com.bgconsole.desktop_engine.core_impl.profile.ProfileReducer
import com.bgconsole.desktop_engine.core_impl.project.CoreProjectServiceImpl
import com.bgconsole.desktop_engine.core_impl.project.ENGINE_USER_SESSION_PROJECT
import com.bgconsole.desktop_engine.core_impl.project.EngineProjectService
import com.bgconsole.desktop_engine.core_impl.project.ProjectReducer
import com.bgconsole.desktop_engine.core_impl.version.CoreVersionServiceImpl
import com.bgconsole.desktop_engine.core_impl.version.ENGINE_USER_SESSION_VERSION
import com.bgconsole.desktop_engine.core_impl.version.EngineVersionService
import com.bgconsole.desktop_engine.core_impl.version.VersionReducer
import com.bgconsole.desktop_engine.core_impl.workspace.CoreWorkspaceServiceImpl
import com.bgconsole.desktop_engine.core_impl.workspace.ENGINE_USER_SESSION_WORKSPACE
import com.bgconsole.desktop_engine.core_impl.workspace.EngineWorkspaceService
import com.bgconsole.desktop_engine.core_impl.workspace.WorkspaceReducer
import com.bgconsole.desktop_engine.store.Store
import com.bgconsole.domain.Profile
import com.bgconsole.domain.Project
import com.bgconsole.domain.Workspace

class CoreServices(store: Store) {

    private val coreProfileService = CoreProfileServiceImpl()
    private val profileService = EngineProfileService(coreProfileService)

    private val coreWorkspaceService = CoreWorkspaceServiceImpl()
    private val workspaceService = EngineWorkspaceService(coreProfileService, coreWorkspaceService)

    private val coreProjectService = CoreProjectServiceImpl()
    private val projectService = EngineProjectService(coreProjectService)

    private val coreVersionService = CoreVersionServiceImpl()
    private val versionService = EngineVersionService(coreVersionService)

    init {
        store.registerServices(
            listOf(
                profileService,
                workspaceService,
                projectService,
                versionService
            )
        )
        store.registerReducers(
            listOf(
                ProfileReducer(),
                WorkspaceReducer(),
                ProjectReducer(),
//                VersionReducer()
            )
        )
        store.addToStore(ENGINE_USER_SESSION_PROFILE, emptyList<Profile>())
        store.addToStore(ENGINE_USER_SESSION_WORKSPACE, emptyList<Workspace>())
        store.addToStore(ENGINE_USER_SESSION_PROJECT, emptyList<Project>())
//        store.addToStore(ENGINE_USER_SESSION_VERSION, emptyList<>())
    }
}