package com.bgconsole.core.environment

import com.bgconsole.domain.Environment
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.service.common.CRUDService

interface EnvironmentService : CRUDService<Environment> {

    fun findByLocation(location: Location): Environment
}