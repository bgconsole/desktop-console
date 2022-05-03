package com.bgconsole.desktop_ui;

import com.bgconsole.desktop_engine.Engine;
import com.bgconsole.desktop_engine.core_impl.profile.ProfileRedux;
import com.bgconsole.desktop_engine.store.Store;
import com.bgconsole.desktop_ui.profile.Profile;
import com.bgconsole.desktop_ui.profile.ProfileService;
import com.bgconsole.desktop_ui.profile.ProfileServiceImpl;
import com.bgconsole.desktop_ui.project.Project;
import com.bgconsole.desktop_ui.project.ProjectService;
import com.bgconsole.desktop_ui.project.ProjectServiceImpl;
import com.bgconsole.desktop_ui.workspace.WorkspaceService;
import com.bgconsole.desktop_ui.workspace.WorkspaceServiceImpl;
import com.bgconsole.domain.Location;
import com.bgconsole.domain.LocationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {

    public static final AppData instance = new AppData();

    private final WorkspaceService workspaceService;

    private final ProjectService projectService;

    private final ProfileService profileService;

    private final Map<String, ProjectData> projectList;

    private List<Profile> profiles;

    private Store store;

    private AppData() {
        Engine engine = new Engine(new Location("", LocationType.FILE, "", "", null));
        store = engine.getStore();
        store.dispatch(new ProfileRedux.LoadProfiles());

        projectList = new HashMap<>();
        workspaceService = new WorkspaceServiceImpl();
        projectService = new ProjectServiceImpl();
        profileService = new ProfileServiceImpl();
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

    public ProfileService getProfileService() {
        return profileService;
    }

    public Store getStore() {
        return store;
    }
}
