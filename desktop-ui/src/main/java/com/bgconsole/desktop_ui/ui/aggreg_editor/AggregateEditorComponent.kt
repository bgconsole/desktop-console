package com.bgconsole.desktop_ui.ui.aggreg_editor

import com.bgconsole.platform.store.Store
import com.bgconsole.platform.ui.component.Component
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Menu

class AggregateEditorComponent : Component() {

    private lateinit var store: Store
    private lateinit var node: Node

    override fun getId(): String = "bgconsole.ui.aggregate_editor"

    override fun getName(): String = "Aggregate editor"

    override fun getNode(): Node = node

    override fun setStore(store: Store) {
        this.store = store
    }

    override fun onInit() {
        val resource = javaClass.getResource("/com/bgconsole/desktop_ui/command_editor.fxml")
        val loader = FXMLLoader(resource)
        val root = loader.load<Parent>()
        val controller = loader.getController<CommandEditorController>()
        root.stylesheets.add(javaClass.getResource("/com/bgconsole/desktop_ui/styles.css")?.toExternalForm())
        node = root
    }

    override fun onClose() {
    }

    override fun onGetVisibility() {
    }

    override fun onLoseVisibility() {
    }

    override fun getMenus(): List<Menu> = emptyList()

}