package com.bgconsole.core.project

import com.bgconsole.core.common.CRUDService
import com.bgconsole.domain.Location
import com.bgconsole.domain.Project

interface ProjectService : CRUDService<Project> {

    fun findByWorkspaceLocation(location: Location): List<Project>

    fun findByLocation(location: Location): Project
}