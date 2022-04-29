package com.bgconsole.desktop;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.profile.ProfileService;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.ui.ProjectWindow;
import com.bgconsole.desktop.ui.new_location.NewLocation;
import com.bgconsole.desktop.ui.new_project.NewProject;
import com.bgconsole.desktop.ui.profile.ProfileWindow;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.ProfileObservableConverter;
import com.bgconsole.desktop.utils.WriteYAMLFile;
import com.bgconsole.desktop.workspace.Workspace;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bgconsole.desktop.ProjectData.DEFAULT_PROFILE_DIR;

public class MainWindowController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private ListView<String> locationList;

    @FXML
    private ChoiceBox<Profile> profileSelector;

    @FXML
    private TableView<Project> projectTable;

    private ObservableList<Project> projectObservableList;

    private List<Location> locationCache;

    private Profile selectedProfile;

    private Workspace selectedWorkspace;

    private Stage stage;

    private ProfileWindow profileWindow;

    private final ProfileService profileService;

    private HostServices hostServices;

    public MainWindowController() {
        profileService = AppData.instance.getProfileService();
    }

    public void initialize() {
        projectObservableList = FXCollections.observableArrayList();
        var name = new TableColumn<Project, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        var description = new TableColumn<Project, String>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));

        projectTable.getColumns().add(name);
        projectTable.getColumns().add(description);

        projectTable.setRowFactory(tableView -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Project project = row.getItem();
                    try {
                        AppData.instance.addProject(project);
                        new ProjectWindow(project);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        projectTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        projectTable.setItems(projectObservableList);
    }


    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void newWorkspace(ActionEvent event) {
        if (selectedProfile != null) {
            new NewLocation(this, selectedProfile);
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
                try {
                    Workspace workspace = ParseYAMLFile.readWorkspace(Paths.get(strPath, "workspace.yaml").toString());

                    Location newLocation = new Location(UUID.randomUUID().toString(), workspace.getName(), strPath);
                    List<Location> locations = new ArrayList<>(selectedProfile.getLocations());
                    locations.add(newLocation);

                    WriteYAMLFile.writeLocations(locations, DEFAULT_PROFILE_DIR.toString());

                    MainWindowData.instance.reloadLocations();
                    setProfileList(MainWindowData.instance.getProfiles());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProfileList(List<Profile> profiles) {
        ObservableList<Profile> profileList = FXCollections.observableArrayList(profiles);
        profileSelector.getSelectionModel().selectFirst();
        profileSelector.setConverter(new ProfileObservableConverter(profiles));
        profileSelector.setItems(profileList);
    }

    public void changeProfile(ActionEvent event) {
        if (profileSelector.getValue() != null) {
            setCurrentProfile(profileSelector.getValue());
        }
    }

    private void setCurrentProfile(Profile profile) {
        selectedProfile = profile;
        locationCache = new ArrayList<>(profile.getLocations());
        locationList.getItems().clear();
        locationCache.forEach(location -> {
            locationList.getItems().add(location.getName());
            locationList.setOnMouseClicked(click -> {
                Location loc = locationCache.get(locationList.getSelectionModel().getSelectedIndex());
                Workspace workspace = MainWindowData.instance.loadWorkspace(loc);
                workspace.setPath(loc.getPath());
                selectedWorkspace = workspace;
                List<Project> projects = MainWindowData.instance.loadProjects(loc.getPath());
                projects.forEach(project -> project.setWorkspace(workspace));
                changeProjectInPane(projects);
//                if (click.getClickCount() == 1) {
//                    Location loc = locationCache.get(locationList.getSelectionModel().getSelectedIndex());
//                    try {
//                        new TerminalWindow(loc);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

            });
        });
    }

    private void changeProjectInPane(List<Project> projects) {
        projectObservableList.clear();
        projectObservableList.setAll(projects);
    }

    public void openProfileManager(ActionEvent event) {
        if (profileWindow == null) {
            profileWindow = new ProfileWindow(profileService, () -> {
                profileWindow = null;
                setProfileList(profileService.loadProfiles(DEFAULT_PROFILE_DIR.toString()));
            });
        } else {
            profileWindow.popUp();
        }
    }

    public void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About BG Console");
        alert.setHeaderText("BG Console version:" + MainWindowData.VERSION);
        alert.setContentText("More info: https://bgconsole.com");
        alert.showAndWait();
    }

    public void help(ActionEvent event) {
        hostServices.showDocument(new Hyperlink("https://bgconsole.com/docs/").getText());
    }

    public void removeWorkspace(ActionEvent event) {

    }

    public void createProject(ActionEvent event) {
        if (selectedWorkspace != null) {
            new NewProject(this, selectedWorkspace);
        }
    }
}
