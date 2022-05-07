package com.bgconsole.desktop_ui.main_window;

import com.bgconsole.desktop_ui.AppData;
import com.bgconsole.desktop_ui.ui.new_location.NewLocation;
import com.bgconsole.platform.ui.perspective.desk.profile.ProfileWindow;
import com.bgconsole.platform.domain.Profile;
import com.bgconsole.platform.domain.Project;
import com.bgconsole.platform.domain.Workspace;
import com.bgconsole.platform.store.Store;
import com.bgconsole.platform.ui.perspective.desk.WorkspacePerspectiveContent;
import com.bgconsole.platform.ui.perspective.desk.WorkspacePerspectiveRedux;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainWindowController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private ListView<Workspace> workspaceList;

    @FXML
    private ChoiceBox<Profile> profileSelector;

    @FXML
    private TableView<Project> projectTable;

    private ObservableList<Project> projectObservableList;

    private Stage stage;

    private ProfileWindow profileWindow;

//    private final ProfileService profileService;

    private WorkspacePerspectiveContent mainWindow;

    private HostServices hostServices;

    public MainWindowController() {
//        profileService = AppData.instance.getProfileService();
    }

    private final Store store = AppData.instance.getStore();



    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void newWorkspace(ActionEvent event) {
        if (mainWindow.getSelectedProfile() != null) {
            new NewLocation(this, mainWindow.getSelectedProfile());
        }
    }

    @FXML
    public void openWorkspace(ActionEvent event) {
        final DirectoryChooser directoryChooser =
                new DirectoryChooser();
        final File selectedDirectory =
                directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            String strPath = selectedDirectory.getAbsolutePath();
            Path path = Paths.get(strPath);
            if (Files.isDirectory(path)) {
//                try {
//                    Workspace workspace = ParseYAMLFile.readWorkspace(Paths.get(strPath, "workspace.yaml").toString());
//
//                    Location newLocation = new Location(UUID.randomUUID().toString(), workspace.getName(), strPath);
//                    List<Location> locations = new ArrayList<>(selectedProfile.getLocations());
//                    locations.add(newLocation);
//
//                    WriteYAMLFile.writeLocations(locations, ProjectData.DEFAULT_PROFILE_DIR.toString());
//
//                    MainWindowData.instance.reloadLocations();
//                    setProfileList(MainWindowData.instance.getProfiles());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    public void changeProfile(ActionEvent event) {
//        if (profileSelector.getValue() != null) {
//            setCurrentProfile(profileSelector.getValue());
//        }
//    }

//    private void setCurrentProfile(Profile profile) {
//        store.dispatch(new WorkspacePerspectiveRedux.SelectProfile(profile));
//        selectedProfile = profile;
////        locationCache = new ArrayList<>(profile.getLocations());
//        locationCache = new ArrayList<>();
//        locationList.getItems().clear();
//        locationCache.forEach(location -> {
//            locationList.getItems().add(location.getName());
//            locationList.setOnMouseClicked(click -> {
//                Location loc = locationCache.get(locationList.getSelectionModel().getSelectedIndex());
//                Workspace workspace = MainWindowData.instance.loadWorkspace(loc);
//                workspace.setPath(loc.getPath());
//                selectedWorkspace = workspace;
//                List<Project> projects = MainWindowData.instance.loadProjects(loc.getPath());
//                projects.forEach(project -> project.setWorkspace(workspace));
//                changeProjectInPane(projects);
////                if (click.getClickCount() == 1) {
////                    Location loc = locationCache.get(locationList.getSelectionModel().getSelectedIndex());
////                    try {
////                        new TerminalWindow(loc);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
//
//            });
//        });
//    }


//    public void openProfileManager(ActionEvent event) {
//        if (profileWindow == null) {
//            profileWindow = new ProfileWindow(, () -> {
//                profileWindow = null;
////                setProfileList(profileService.loadProfiles(ProjectData.DEFAULT_PROFILE_DIR.toString()));
//            });
//        } else {
//            profileWindow.popUp();
//        }
//    }
}
