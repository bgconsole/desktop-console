package com.bgconsole.desktop_engine.core_impl.version

import com.bgconsole.core.version.VersionService
import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.domain.LocationType
import com.bgconsole.platform.engine.common.YAMLParser
import java.nio.file.Paths

class CoreVersionServiceImpl : VersionService {

    private val yamlParser = YAMLParser(Version.empty(), emptyList())

    override fun findByProjectLocation(location: Location): List<Version> {
        return Paths.get(location.location, "versions").toFile().listFiles { file -> file.isDirectory }
            ?.map { findByLocation(Location.technical(LocationType.FILE, it.absolutePath)) }.orEmpty()
    }

    override fun findByLocation(location: Location): Version {
        return yamlParser.readOne(Paths.get(location.location, "version.yaml").toString()).copy(location = location)
    }

    override fun findById(id: String): Version? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Version> {
        TODO("Not yet implemented")
    }

    override fun add(t: Version) {
        TODO("Not yet implemented")
    }

    override fun update(id: String, newT: Version): Version {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }
}