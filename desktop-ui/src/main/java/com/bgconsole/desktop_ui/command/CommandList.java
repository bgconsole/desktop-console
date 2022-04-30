package com.bgconsole.desktop_ui.command;

import java.util.ArrayList;
import java.util.List;

public class CommandList {

    private String absolutePath;

    private String name;

    private List<Command> commands;

    public CommandList(String name, String absolutePath) {
        this.name = name;
        this.absolutePath = absolutePath;
        commands = new ArrayList<>();
    }

    public void setNewList(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getName() {
        return name;
    }
}
