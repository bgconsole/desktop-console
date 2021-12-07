package com.bgconsole.desktop.environment;

import com.bgconsole.desktop.Config;
import com.bgconsole.desktop.command.CommandList;
import com.bgconsole.desktop.terminal.TerminalList;
import com.bgconsole.desktop.variable.VariableList;
import com.bgconsole.desktop.workspace.Workspace;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Environment {

    private String id;
    private Path dir;

    private Workspace workspace;
    private TerminalList terminalList;
    private List<Config> configs;
    private List<VariableList> variableLists;
    private List<CommandList> commandLists;

    public Environment(String id, Path dir, Workspace workspace) {
        this.id = id;
        this.dir = dir;
        this.workspace = workspace;
        this.terminalList = new TerminalList();
        configs = new ArrayList<>();
        variableLists = new ArrayList<>();
        commandLists = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Path getDir() {
        return dir;
    }

    public void setDir(Path dir) {
        this.dir = dir;
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

    public Workspace getWorkspace() {
        return workspace;
    }
}
