package com.bgconsole.core.version

import com.bgconsole.core.common.CRUDService
import com.bgconsole.domain.Location
import com.bgconsole.domain.Version

interface VersionService : CRUDService<Version> {

    fun findByProjectLocation(location: Location): List<Version>

    fun findByLocation(location: Location): Version
}