package com.bgconsole.core.aggregate

import com.bgconsole.domain.Aggregate
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.service.common.CRUDService

interface AggregateService: CRUDService<Aggregate> {

    fun findByLocation(location: Location): Aggregate
}