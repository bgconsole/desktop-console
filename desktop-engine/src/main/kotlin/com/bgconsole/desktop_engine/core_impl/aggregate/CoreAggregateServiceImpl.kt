package com.bgconsole.desktop_engine.core_impl.aggregate

import com.bgconsole.core.aggregate.AggregateService
import com.bgconsole.desktop_engine.common.YAMLParser
import com.bgconsole.domain.Aggregate
import com.bgconsole.domain.Location
import com.bgconsole.domain.Profile

class CoreAggregateServiceImpl : AggregateService {

    private val yamlParser = YAMLParser(Aggregate.empty(), emptyList())

    override fun findByLocation(location: Location): Aggregate {
        return yamlParser.readOne(location.location).copy(location = location)
    }

    override fun findById(id: String): Profile? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Aggregate> {
        TODO("Not yet implemented")
    }

    override fun add(t: Aggregate) {
        TODO("Not yet implemented")
    }

    override fun update(id: String, newT: Aggregate): Aggregate {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }
}