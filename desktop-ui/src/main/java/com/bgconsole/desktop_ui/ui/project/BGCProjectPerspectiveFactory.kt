package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Store
import com.bgconsole.platform.ui.project.ProjectPerspective
import com.bgconsole.platform.ui.project.ProjectType

const val BGC_PROJECT_TYPE = "com.bgconsole.console"

class BGCProjectPerspectiveFactory : ProjectType() {
    override fun getProjectType(): String = BGC_PROJECT_TYPE

    override fun factory(store: Store, project: Project): ProjectPerspective {
        val bgcProject = BGCProjectPerspective()
        bgcProject.setProject(project)
        bgcProject.setStore(store)
        bgcProject.onInit()
        return bgcProject
    }
}
