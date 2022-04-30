package com.bgconsole.core.workspace

import com.bgconsole.core.common.CRUDService
import com.bgconsole.domain.Location
import com.bgconsole.domain.Workspace

interface WorkspaceService : CRUDService<Workspace> {

    fun findByLocation(location: Location): Workspace
}