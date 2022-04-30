package com.bgconsole.desktop_engine.core_impl.workspace

import com.bgconsole.core.workspace.WorkspaceService
import com.bgconsole.desktop_engine.common.YAMLParser
import com.bgconsole.domain.Location
import com.bgconsole.domain.Profile
import com.bgconsole.domain.Workspace
import java.nio.file.Paths

class CoreWorkspaceServiceImpl : WorkspaceService {

    private val yamlParser = YAMLParser(Workspace.empty(), emptyList())

    override fun findByLocation(location: Location): Workspace {
        return yamlParser.readOne(Paths.get(location.location, "workspace.yaml").toString()).copy(location = location)
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