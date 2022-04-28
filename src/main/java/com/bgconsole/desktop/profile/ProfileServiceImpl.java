package com.bgconsole.desktop.profile;

import com.bgconsole.desktop.ProjectData;
import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;
import com.bgconsole.desktop.workspace.Workspace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class ProfileServiceImpl implements ProfileService {

    @Override
    public List<Profile> loadProfiles(String path) {
        try {
            return ParseYAMLFile.readProfiles(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Profile save(String id, Profile profile) {
        return saveProfile(profile, id);
    }

    @Override
    public void delete(String id) {
        var path = Paths.get(ProjectData.DEFAULT_PROFILE_DIR.toString(), id + ".yaml");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile create(Profile profile) {
        var id = UUID.randomUUID().toString();
        return saveProfile(profile, id);
    }

    @Override
    public Profile addWorkspace(String profileId, Workspace workspace, String workspacePath) {
        String path = Paths.get(ProjectData.DEFAULT_PROFILE_DIR.toString(), profileId + ".yaml").toString();
        try {
            Profile profile = ParseYAMLFile.readProfile(path);
            profile.getLocations().add(new Location(workspace.getId(), workspace.getName(), workspacePath));
            saveProfile(profile, profileId);
            return profile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Profile saveProfile(Profile profile, String id) {
        try {
            String path = Paths.get(ProjectData.DEFAULT_PROFILE_DIR.toString(), id + ".yaml").toString();
            profile.setId(id);
            WriteYAMLFile.writeProfile(profile, path);
            return profile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
