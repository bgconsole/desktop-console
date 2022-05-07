package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_engine.core_impl.version.VersionRedux
import com.bgconsole.desktop_ui.AppData
import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Store
import com.bgconsole.platform.ui.project.ProjectPerspective
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Menu

class BGCProjectPerspective : ProjectPerspective() {

    private lateinit var store: Store
    private lateinit var node: Node
    private lateinit var project: Project

    override fun getProject(): Project = project

    override fun getProjectId(): String = project.id

    override fun setProject(project: Project) {
        this.project = project
    }

    override fun getProjectType(): String = BGC_PROJECT_TYPE
    override fun getName(): String = project.name
    override fun getId() = project.id

    override fun getNode(): Node = node

    override fun setStore(store: Store) {
        this.store = store
    }

    override fun onInit() {
        val resource = javaClass.getResource("/com/bgconsole/desktop_ui/project_window.fxml")
        val loader = FXMLLoader(resource)
        val root = loader.load<Parent>()
        val controller = loader.getController<BGCProjectPerspectiveController>()
        controller.setProject(project)
        root.stylesheets.add(javaClass.getResource("/com/bgconsole/desktop_ui/styles.css")?.toExternalForm())
        node = root

        val store = AppData.instance.store
        store.dispatch(VersionRedux.LoadVersionsByProject(project))
    }

    override fun onClose() {
    }

    override fun onGetVisibility() {
    }

    override fun onLoseVisibility() {
    }

    override fun getMenus(): List<Menu> {
        return emptyList()
    }
}