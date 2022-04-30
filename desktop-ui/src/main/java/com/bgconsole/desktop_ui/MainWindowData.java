package com.bgconsole.desktop_ui;

import com.bgconsole.desktop_ui.location.Location;
import com.bgconsole.desktop_ui.profile.Profile;
import com.bgconsole.desktop_ui.project.Project;
import com.bgconsole.desktop_ui.workspace.Workspace;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainWindowData {

    public static final MainWindowData instance = new MainWindowData();

    public static final String VERSION = "1.5.0";

    private List<Profile> profiles;

    private MainWindowData() {

//        if (!Files.exists(ProjectData.DEFAULT_PROFILE_DIR)) {
//            try {
//                Files.createDirectories(ProjectData.DEFAULT_PROFILE_DIR.getParent());
//                WriteYAMLFile.writeLocations(new ArrayList<>(), ProjectData.DEFAULT_PROFILE_DIR.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        reloadLocations();
    }

    public void reloadLocations() {
//        try {
//            profiles = ParseYAMLFile.readProfiles(ProjectData.DEFAULT_PROFILE_DIR.toString());
//            AppData.instance.setProfiles(profiles);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public List<Profile> getProfiles() {
        return new ArrayList<>();
    }

    public Workspace loadWorkspace(Location location) {
        return AppData.instance.getWorkspaceService().loadWorkspace(Paths.get(location.getPath(), "workspace.yaml").toString());
    }

    public List<Project> loadProjects(String workspaceDir) {
        return AppData.instance.getProjectService().loadProjectByWorkspaceDir(workspaceDir);
    }
}
