package com.bgconsole.desktop_engine.desktop_services.opened.project

import com.bgconsole.domain.Instruction
import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.domain.Workspace

data class OpenedProjectContent(
    val projects: List<Project>,
    val availableInstructions: List<AvailableInstruction>,
) {
    companion object {
        fun default(): OpenedProjectContent {
            return OpenedProjectContent(emptyList(), emptyList())
        }
    }
}

data class AvailableInstruction(
    val workspace: Workspace,
    val project: Project,
    val instruction: Instruction
)