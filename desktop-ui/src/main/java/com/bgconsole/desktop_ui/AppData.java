package com.bgconsole.desktop_ui;

import com.bgconsole.desktop_engine.Engine;
import com.bgconsole.platform.domain.Location;
import com.bgconsole.platform.domain.LocationType;
import com.bgconsole.platform.domain.Profile;
import com.bgconsole.platform.engine.profile.ProfileRedux;
import com.bgconsole.platform.store.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {

    public static final AppData instance = new AppData();

    private final Map<String, ProjectData> projectList;

    private List<Profile> profiles;

    private Store store;

    private AppData() {
        Engine engine = new Engine(new Location("", LocationType.FILE, "", "", null));
        store = engine.getStore();
        store.dispatch(new ProfileRedux.LoadProfiles());

        projectList = new HashMap<>();
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


    public List<ProjectData> getProjects() {
        return new ArrayList<>(projectList.values());
    }

    public Store getStore() {
        return store;
    }
}
