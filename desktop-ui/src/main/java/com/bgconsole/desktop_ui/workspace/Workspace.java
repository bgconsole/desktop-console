package com.bgconsole.desktop_ui.workspace;

public class Workspace {

    private String id;
    private String name;
    private String path;

    public Workspace() {
    }

    public Workspace(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Workspace(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
