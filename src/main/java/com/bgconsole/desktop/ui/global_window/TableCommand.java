package com.bgconsole.desktop.ui.global_window;

public class TableCommand {

    private String command;
    private String workspace;
    private String alias;

    public TableCommand(String command, String workspace, String alias) {
        this.command = command;
        this.workspace = workspace;
        this.alias = alias;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
