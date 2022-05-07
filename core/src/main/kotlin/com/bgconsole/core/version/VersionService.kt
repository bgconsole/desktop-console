package com.bgconsole.core.version

import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.service.common.CRUDService

interface VersionService : CRUDService<Version> {

    fun findByProjectLocation(location: Location): List<Version>

    fun findByLocation(location: Location): Version
}