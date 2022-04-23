package com.bgconsole.desktop.environment;

import com.bgconsole.desktop.Config;
import com.bgconsole.desktop.command.CommandList;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.terminal.TerminalList;
import com.bgconsole.desktop.variable.VariableList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Environment {

    private Project project;
    private TerminalList terminalList;
    private List<Config> configs;
    private List<VariableList> variableLists;
    private List<CommandList> commandLists;

    public Environment(Project project) {
        this.project = project;
        this.terminalList = new TerminalList();
        configs = new ArrayList<>();
        variableLists = new ArrayList<>();
        commandLists = new ArrayList<>();
    }

    public String getId() {
        return project.getId();
    }

    public Path getDir() {
        return Paths.get(project.getPath());
    }

    public TerminalList getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(TerminalList terminalList) {
        this.terminalList = terminalList;
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }

    public List<VariableList> getVariableLists() {
        return variableLists;
    }

    public void setVariableLists(List<VariableList> variableLists) {
        this.variableLists = variableLists;
    }

    public List<CommandList> getCommandLists() {
        return commandLists;
    }

    public void setCommandLists(List<CommandList> commandLists) {
        this.commandLists = commandLists;
    }

    public Project getProject() {
        return project;
    }
}
