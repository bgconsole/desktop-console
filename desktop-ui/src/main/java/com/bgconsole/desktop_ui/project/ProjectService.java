package com.bgconsole.desktop_ui.project;

import com.bgconsole.desktop_ui.workspace.Workspace;

import java.util.List;

public interface ProjectService {

    Project createProject(Workspace workspace, String name);

    Project createProjectWithDefaultVarAndInstr(Workspace workspace, String name);

    Project loadProject(String dir);

    List<Project> loadProjectByWorkspaceDir(String path);
}
