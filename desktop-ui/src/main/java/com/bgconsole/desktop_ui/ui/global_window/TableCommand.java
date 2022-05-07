package com.bgconsole.desktop_ui.ui.global_window;

import com.bgconsole.desktop_ui.ProjectData;
import com.bgconsole.domain.Instruction;

public class TableCommand {

    private Instruction command;
    private ProjectData projectData;

    public TableCommand(Instruction command, ProjectData projectData) {
        this.command = command;
        this.projectData = projectData;
    }

    public String getName() {
        return command.getName();
    }

    public String getCommand() {
        return command.getInstruction();
    }

    public String getWorkspace() {
        return projectData.getWorkspaceName();
    }

    public String getAlias() {
        return command.getAlias();
    }

    public ProjectData getLocationData() {
        return projectData;
    }
}
