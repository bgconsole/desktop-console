package com.bgconsole.desktop;

import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.project.ProjectService;
import com.bgconsole.desktop.project.ProjectServiceImpl;
import com.bgconsole.desktop.workspace.WorkspaceService;
import com.bgconsole.desktop.workspace.WorkspaceServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {

    public static final AppData instance = new AppData();

    private final WorkspaceService workspaceService;

    private final ProjectService projectService;

    private final Map<String, ProjectData> projectList;

    private List<Profile> profiles;

    private AppData() {
        projectList = new HashMap<>();
        workspaceService = new WorkspaceServiceImpl();
        projectService = new ProjectServiceImpl();
        profiles = new ArrayList<>();
    }

    public ProjectData get(String id) {
        return projectList.get(id);
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void addProject(Project project) {
        projectList.put(project.getId(), new ProjectData(project));
    }

    public List<ProjectData> getProjects() {
        return new ArrayList<>(projectList.values());
    }

    public WorkspaceService getWorkspaceService() {
        return workspaceService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }
}
