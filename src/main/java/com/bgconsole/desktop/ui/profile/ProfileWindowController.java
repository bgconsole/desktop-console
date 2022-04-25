package com.bgconsole.desktop.ui.profile;

import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.profile.ProfileService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProfileWindowController {

    @FXML
    private ListView<Profile> profileList;

    private ProfileService profileService;

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setProfiles(List<Profile> profiles) {
        ObservableList<Profile> observableList = FXCollections.observableArrayList(profiles);
        profileList.setCellFactory(profileListView -> new ListCell<>() {
            @Override
            protected void updateItem(Profile item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        profileList.setItems(observableList);
    }

    @FXML
    public void add() {
        TextInputDialog inputDialog = new TextInputDialog("New profile");
        inputDialog.setContentText("Name: ");
        inputDialog.setHeaderText("Set the name of the new profile");
        inputDialog.showAndWait().ifPresent(newName -> {
            Profile profile = new Profile(UUID.randomUUID().toString(), newName, Collections.emptyList());
            profileService.create(profile);
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

            }
        });
    }
}
