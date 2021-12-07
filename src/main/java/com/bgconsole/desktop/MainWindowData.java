package com.bgconsole.desktop;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.bgconsole.desktop.LocationData.DEFAULT_LOCATION;

public class MainWindowData {

    public static final MainWindowData instance = new MainWindowData();

    private List<Location> locations;

    private MainWindowData() {
        if (!Files.exists(DEFAULT_LOCATION)) {
            try {
                Files.createDirectories(DEFAULT_LOCATION.getParent());
                WriteYAMLFile.writeLocations(new ArrayList<>(), DEFAULT_LOCATION.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reloadLocations();
    }

    public void reloadLocations() {
        try {
            locations = ParseYAMLFile.readLocations(DEFAULT_LOCATION.toString());
            AppData.instance.setLocationList(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getLocations() {
        return locations;
    }
}
