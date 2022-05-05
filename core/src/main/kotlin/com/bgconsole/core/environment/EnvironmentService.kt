package com.bgconsole.core.environment

import com.bgconsole.core.common.CRUDService
import com.bgconsole.domain.Aggregate
import com.bgconsole.domain.Environment
import com.bgconsole.domain.Location

interface EnvironmentService : CRUDService<Environment> {

    fun findByLocation(location: Location): Environment
}