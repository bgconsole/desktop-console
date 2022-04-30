package com.bgconsole.desktop_engine.core_impl.workspace

import com.bgconsole.core.workspace.WorkspaceService
import com.bgconsole.domain.Location
import com.bgconsole.domain.Profile
import com.bgconsole.domain.Workspace

class CoreWorkspaceServiceImpl: WorkspaceService {
    override fun findByLocation(location: Location): Workspace {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Profile? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Workspace> {
        TODO("Not yet implemented")
    }

    override fun add(t: Workspace) {
        TODO("Not yet implemented")
    }

    override fun update(id: String, newT: Workspace): Workspace {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }
}