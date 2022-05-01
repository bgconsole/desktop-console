package com.bgconsole.desktop_ui.ui.terminal_window

import com.bgconsole.desktop_ui.MainWindow
import com.bgconsole.desktop_ui.terminal.Terminal
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.io.IOException
import java.util.*

class TerminalWindow(terminal: Terminal) {
    private val terminals: MutableList<Terminal>
    private var controller: TerminalWindowController? = null
    private var stage: Stage? = null
    fun addTerminal(terminal: Terminal) {
        terminal.window = this
        terminals.add(terminal)
    }

    fun getTerminals(): List<Terminal> {
        return terminals
    }

    fun popUp() {
        if (stage!!.isIconified) stage!!.isIconified = false
        stage!!.show()
        stage!!.requestFocus()
        stage!!.toFront()
    }

    fun selectTerminal(terminal: Terminal) {
        controller!!.selectTab(terminal.terminalTab)
    }

    init {
        terminal.window = this
        terminals = ArrayList()
        terminals.add(terminal)
        try {
            val resource = javaClass.getResource("/com/bgconsole/desktop_ui/terminal_window.fxml")
            val loader = FXMLLoader(resource)
            val root = loader.load<Parent>()
            controller = loader.getController()
            controller?.addTerminalTab(terminal.terminalTab)
            stage = Stage()
            //            stage.initModality(Modality.WINDOW_MODAL);
            val scene = Scene(root)
            stage!!.scene = scene
            scene.stylesheets.add(javaClass.getResource("/com/bgconsole/desktop_ui/styles.css")?.toExternalForm())
            stage!!.title = terminal.name
            stage!!.icons.add(
                Image(
                    Objects.requireNonNull(
                        MainWindow::class.java.getResourceAsStream("/com/bgconsole/desktop_ui/img/logo.png")
                    )
                )
            )
            stage!!.scene = scene
            stage!!.show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}