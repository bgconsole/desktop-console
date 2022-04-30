package com.bgconsole.desktop_ui.ui.profile;

import com.bgconsole.desktop_engine.desktop_services.ProfileRedux;
import com.bgconsole.desktop_engine.store.Store;
import com.bgconsole.desktop_ui.AppData;
import com.bgconsole.domain.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import static com.bgconsole.desktop_engine.desktop_services.ProfileReduxKt.ENGINE_CRUD_PROFILE;

public class ProfileWindowController {

    @FXML
    private ListView<Profile> profileList;

    private final Store store = AppData.instance.getStore();

    public void initialize() {
        ObservableList<Profile> observableList = FXCollections.observableArrayList();
        profileList.setCellFactory(profileListView -> new ListCell<>() {
            @Override
            protected void updateItem(Profile item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    item.getName();
                    setText(item.getName());
                }
            }
        });
        profileList.setItems(observableList);
        store.subscribe(ENGINE_CRUD_PROFILE, profiles -> observableList.setAll((List<Profile>) profiles));
        store.dispatch(new ProfileRedux.LoadProfiles());
    }

    @FXML
    public void add() {
        TextInputDialog inputDialog = new TextInputDialog("New profile");
        inputDialog.setContentText("Name: ");
        inputDialog.setHeaderText("Set the name of the new profile");
        inputDialog.showAndWait().ifPresent(newName -> {
            Profile profile = Profile.Factory.profile(newName, "");
            store.dispatch(new ProfileRedux.SaveProfile(profile));
//            Profile profile = new Profile(UUID.randomUUID().toString(), newName, Collections.emptyList());
//            profileService.create(profile);
        });
    }

    @FXML
    public void update() {
        Profile profile = profileList.getSelectionModel().getSelectedItem();
        TextInputDialog inputDialog = new TextInputDialog("Update profile name");
        inputDialog.setContentText("Name: ");
        inputDialog.setHeaderText("Set the new name of the profile");
        inputDialog.getEditor().setText(profile.getName());
        inputDialog.showAndWait().ifPresent(newName -> {
            if (!newName.equals(profile.getName())) {
//                profile.setName(newName);
//                profileService.save(profile.getId(), profile);
            }
        });
    }

    @FXML
    public void delete() {
        Profile profile = profileList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete profile");
        alert.setHeaderText("The profile \"" + profile.getName() + "\" is about to be deleted.");
        alert.setContentText("Are you sure you would like to delete it ?");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
//                profileService.delete(profile.getId());
            }
        });
    }

}
