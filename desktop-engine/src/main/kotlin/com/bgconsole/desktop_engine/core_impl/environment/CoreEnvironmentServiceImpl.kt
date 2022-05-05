package com.bgconsole.desktop_engine.core_impl.environment

import com.bgconsole.core.environment.EnvironmentService
import com.bgconsole.desktop_engine.common.YAMLParser
import com.bgconsole.domain.Environment
import com.bgconsole.domain.Location

class CoreEnvironmentServiceImpl : EnvironmentService {

    private val yamlParser = YAMLParser(Environment.empty(), emptyList())
    override fun findByLocation(location: Location): Environment {
        return yamlParser.readOne(location.location).copy(location = location)
    }

    override fun findById(id: String): Environment? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Environment> {
        TODO("Not yet implemented")
    }

    override fun add(t: Environment) {
        TODO("Not yet implemented")
    }

    override fun update(id: String, newT: Environment): Environment {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }
}