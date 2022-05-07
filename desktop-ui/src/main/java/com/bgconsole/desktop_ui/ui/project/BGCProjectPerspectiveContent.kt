package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_ui.ui.terminal_window.TerminalWindow
import com.bgconsole.domain.Instruction
import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Project

data class BGCProjectPerspectiveContent(
    val title: String,
    val project: Project,
    val instructions: List<Instruction>,
    val terminalWindows: List<TerminalWindow>,
    val selectedVersion: Version?,
    val showEditInstructions: Boolean,
    val showEditVariables: Boolean,
) {

    companion object {
        fun default(title: String, project: Project): BGCProjectPerspectiveContent {
            return BGCProjectPerspectiveContent(
                title,
                project,
                emptyList(),
                emptyList(),
                null,
                showEditInstructions = false,
                showEditVariables = false
            )
        }
    }
}