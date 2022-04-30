package com.bgconsole.desktop_ui.profile;

import com.bgconsole.desktop_ui.location.Location;

import java.util.List;

public class Profile {

    private String id;
    private String name;
    private List<Location> locations;

    public Profile() {
    }

    public Profile(String id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
