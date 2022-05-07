package com.bgconsole.desktop_ui

import com.bgconsole.desktop_ui.global_input.GlobalKeyListener
import com.bgconsole.platform.ui.perspective.workspace.WorkspacePerspectiveRedux
import com.bgconsole.platform.domain.Location
import com.bgconsole.platform.domain.LocationType
import com.bgconsole.platform.store.Store
import com.bgconsole.platform.ui.BGPlatform
import javafx.application.Application
import javafx.stage.Stage
import java.nio.file.Paths
import java.util.*

class MainWindow : Application() {
    @Throws(Exception::class)
    override fun start(stage: Stage) {

        val path = Paths.get(System.getProperty("user.home"), ".com.bgconsole", "profiles").toString()
        val location = Location(UUID.randomUUID().toString(), LocationType.FILE, "ROOT_DIR", path)
        val store = Store()
        BGPlatform(location, store, stage)


//        controller.setStage(stage);
//        controller.setProfileList(MainWindowData.instance.getProfiles());
        loadComponents(store)
    }

    private fun loadComponents(store: Store) {
        WorkspacePerspectiveRedux(store)
        GlobalKeyListener()
    }


    fun main(args: Array<String>) {
        launch(*args)
    }
}