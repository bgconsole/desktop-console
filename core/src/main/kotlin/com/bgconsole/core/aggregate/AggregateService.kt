package com.bgconsole.core.aggregate

import com.bgconsole.core.common.CRUDService
import com.bgconsole.domain.Aggregate
import com.bgconsole.domain.Location

interface AggregateService: CRUDService<Aggregate> {

    fun findByLocation(location: Location): Aggregate
}