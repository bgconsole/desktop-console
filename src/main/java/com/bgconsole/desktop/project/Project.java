package com.bgconsole.desktop.project;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.workspace.Workspace;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private String id;
    private String name;
    private String description;

    private Workspace workspace;

    private List<ProjectCommand> commands = new ArrayList<>();
    private List<ProjectVariable> variables = new ArrayList<>();

    public Project() {
    }

    public Project(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @JsonIgnore
    public String getPath() {
        return workspace != null ? Paths.get(workspace.getPath(), "projects", id).toString() : null;
    }

    public Location getWorkspaceLocation() {
        return workspace != null ? new Location(workspace.getName(), workspace.getPath()) : null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
