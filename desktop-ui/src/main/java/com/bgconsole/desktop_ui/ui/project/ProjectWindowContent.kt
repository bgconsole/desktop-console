package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_ui.ui.terminal_window.TerminalWindow
import com.bgconsole.domain.Instruction
import com.bgconsole.domain.Project
import com.bgconsole.domain.Version

data class ProjectWindowContent(
    val title: String,
    val project: Project,
    val instructions: List<Instruction>,
    val terminalWindows: List<TerminalWindow>,
    val selectedVersion: Version?,
    val showEditInstructions: Boolean,
    val showEditVariables: Boolean,
) {

    companion object {
        fun default(title: String, project: Project): ProjectWindowContent {
            return ProjectWindowContent(
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