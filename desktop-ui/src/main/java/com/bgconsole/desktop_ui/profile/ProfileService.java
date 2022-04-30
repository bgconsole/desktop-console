package com.bgconsole.desktop_ui.profile;

import com.bgconsole.desktop_ui.workspace.Workspace;

import java.util.List;

public interface ProfileService {

    List<Profile> loadProfiles(String path);

    Profile save(String id, Profile profile);

    void delete(String id);

    Profile create(Profile profile);

    Profile addWorkspace(String profileId, Workspace workspace, String path);
}