package com.bgconsole.desktop_ui.main_window

import com.bgconsole.domain.Profile
import com.bgconsole.domain.Workspace

data class MainWindowContent(val selectedProfile: Profile?, val selectWorkspace: Workspace?) {
    companion object {
        fun default(): MainWindowContent {
            return MainWindowContent(null, null)
        }
    }
}
