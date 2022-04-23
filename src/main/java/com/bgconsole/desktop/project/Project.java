package com.bgconsole.desktop.project;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.workspace.Workspace;

import java.nio.file.Paths;
import java.util.List;

public class Project {

    private String id;
    private String name;
    private Workspace workspace;

    private List<ProjectCommand> commands;
    private List<ProjectVariable> variables;

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public List<ProjectCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ProjectCommand> commands) {
        this.commands = commands;
    }

    public List<ProjectVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<ProjectVariable> variables) {
        this.variables = variables;
    }

    public String getPath() {
        return Paths.get(workspace.getPath(), "projects", id).toString();
    }

    public Location getWorkspaceLocation() {
        return new Location(workspace.getName(), workspace.getPath());
    }

}
