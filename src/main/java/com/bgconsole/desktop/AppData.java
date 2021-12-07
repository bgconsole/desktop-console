package com.bgconsole.desktop;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.workspace.WorkspaceService;
import com.bgconsole.desktop.workspace.WorkspaceServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {

    public static final AppData instance = new AppData();

    private final WorkspaceService workspaceService;

    private Map<String, LocationData> locationList;

    private AppData() {
        locationList = new HashMap<>();
        workspaceService = new WorkspaceServiceImpl();
    }

    public LocationData get(String id) {
        return locationList.get(id);
    }

    public void setLocationList(List<Location> locations) {
        locationList.clear();
        locations.forEach(location -> {
            locationList.put(location.getId(), new LocationData(location, workspaceService));
        });
    }

    public List<LocationData> getLocations() {
        return new ArrayList<>(locationList.values());
    }

    public WorkspaceService getWorkspaceService() {
        return workspaceService;
    }
}
