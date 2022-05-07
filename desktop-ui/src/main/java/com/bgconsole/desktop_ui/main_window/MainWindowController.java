package com.bgconsole.desktop_ui.main_window;

import com.bgconsole.desktop_engine.desktop_services.opened.project.OpenedProjectRedux;
import com.bgconsole.desktop_ui.AppData;
import com.bgconsole.desktop_ui.MainWindowData;
import com.bgconsole.desktop_ui.ui.new_location.NewLocation;
import com.bgconsole.desktop_ui.ui.profile.ProfileWindow;
import com.bgconsole.desktop_ui.ui.project.ProjectWindow;
import com.bgconsole.platform.ui.perspective.workspace.ProfileObservableConverter;
import com.bgconsole.platform.domain.Profile;
import com.bgconsole.platform.domain.Project;
import com.bgconsole.platform.domain.Workspace;
import com.bgconsole.platform.store.Store;
import com.bgconsole.platform.ui.perspective.workspace.WorkspacePerspectiveContent;
import com.bgconsole.platform.ui.perspective.workspace.WorkspacePerspectiveRedux;
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
import java.util.List;

import static com.bgconsole.platform.ui.perspective.workspace.WorkspacePerspectiveReduxKt.UI_MAIN_WINDOW;
import static com.bgconsole.platform.engine.profile.ProfileReduxKt.ENGINE_USER_SESSION_PROFILE;
import static com.bgconsole.platform.engine.project.ProjectReduxKt.ENGINE_USER_SESSION_PROJECT;
import static com.bgconsole.platform.engine.workspace.WorkspaceReduxKt.ENGINE_USER_SESSION_WORKSPACE;

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

    public void initialize() {
        projectObservableList = FXCollections.observableArrayList();
        var name = new TableColumn<Project, String>("Project");
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
//                        AppData.instance.addProject(project);
                        new ProjectWindow(project);
                        store.dispatch(new OpenedProjectRedux.OpenProject(project));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        projectTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        projectTable.setItems(projectObservableList);

        ObservableList<Profile> profileList = FXCollections.observableArrayList();
//        profileSelector.getSelectionModel().selectFirst();
        profileSelector.setConverter(new ProfileObservableConverter(profileList));
        profileSelector.setItems(profileList);
        store.subscribe(ENGINE_USER_SESSION_PROFILE, entity -> profileList.setAll((List<Profile>) entity));
        profileList.setAll((List<Profile>) store.get(ENGINE_USER_SESSION_PROFILE));

        ObservableList<Workspace> workspaceObservableList = FXCollections.observableArrayList();
        workspaceList.setCellFactory(profileListView -> new ListCell<>() {
            @Override
            protected void updateItem(Workspace item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    item.getName();
                    setText(item.getName());
                }
            }
        });
        workspaceList.setOnMouseClicked(click -> {
            store.dispatch(new WorkspacePerspectiveRedux.SelectWorkspace(workspaceList.getSelectionModel().getSelectedItem()));
        });
        workspaceList.setItems(workspaceObservableList);
        store.subscribe(ENGINE_USER_SESSION_WORKSPACE, workspaces -> workspaceObservableList.setAll((List<Workspace>) workspaces));

        store.subscribe(ENGINE_USER_SESSION_PROJECT, projects -> projectObservableList.setAll((List<Project>) projects));

        mainWindow = (WorkspacePerspectiveContent) store.get(UI_MAIN_WINDOW);
        store.subscribe(UI_MAIN_WINDOW, mainWindow -> this.mainWindow = (WorkspacePerspectiveContent) mainWindow);
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

    public void changeProfile(ActionEvent event) {
        if (profileSelector.getValue() != null) {
            setCurrentProfile(profileSelector.getValue());
        }
    }

    private void setCurrentProfile(Profile profile) {
        store.dispatch(new WorkspacePerspectiveRedux.SelectProfile(profile));
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
    }

    private void changeProjectInPane(List<Project> projects) {
        projectObservableList.clear();
        projectObservableList.setAll(projects);
    }

    public void openProfileManager(ActionEvent event) {
        if (profileWindow == null) {
            profileWindow = new ProfileWindow(() -> {
                profileWindow = null;
//                setProfileList(profileService.loadProfiles(ProjectData.DEFAULT_PROFILE_DIR.toString()));
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
        if (mainWindow.getSelectedWorkspace() != null) {
//            new NewProject(this, mainWindow.getSelectedWorkspace());
        }
    }
}
