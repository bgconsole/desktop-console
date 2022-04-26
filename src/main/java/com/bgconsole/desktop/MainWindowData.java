package com.bgconsole.desktop;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;
import com.bgconsole.desktop.workspace.Workspace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.bgconsole.desktop.ProjectData.DEFAULT_PROFILE_DIR;

public class MainWindowData {

    public static final MainWindowData instance = new MainWindowData();

    public static final String VERSION = "1.5.0";

    private List<Profile> profiles;

    private MainWindowData() {
        if (!Files.exists(DEFAULT_PROFILE_DIR)) {
            try {
                Files.createDirectories(DEFAULT_PROFILE_DIR.getParent());
                WriteYAMLFile.writeLocations(new ArrayList<>(), DEFAULT_PROFILE_DIR.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reloadLocations();
    }

    public void reloadLocations() {
        try {
            profiles = ParseYAMLFile.readProfiles(DEFAULT_PROFILE_DIR.toString());
            AppData.instance.setProfiles(profiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Workspace loadWorkspace(Location location) {
        return AppData.instance.getWorkspaceService().loadWorkspace(Paths.get(location.getPath(), "workspace.yaml").toString());
    }

    public List<Project> loadProjects(String workspaceDir) {
        return AppData.instance.getProjectService().loadProjectByWorkspaceDir(workspaceDir);
    }
}
