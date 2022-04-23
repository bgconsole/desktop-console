package com.bgconsole.desktop.project;

import com.bgconsole.desktop.workspace.Workspace;

import java.util.List;

public interface ProjectService {

    Project createProject(Workspace workspace, String name);

    Project loadProject(String dir);

    List<Project> loadProjectByWorkspaceDir(String path);
}
