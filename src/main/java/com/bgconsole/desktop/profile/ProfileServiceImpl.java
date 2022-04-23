package com.bgconsole.desktop.profile;

import com.bgconsole.desktop.utils.ParseYAMLFile;

import java.io.IOException;
import java.util.List;

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
    public Profile save(Profile profile) {
        return null;
    }

    @Override
    public void delete(Profile profile) {

    }

    @Override
    public Profile create(Profile profile) {
        return null;
    }
}
