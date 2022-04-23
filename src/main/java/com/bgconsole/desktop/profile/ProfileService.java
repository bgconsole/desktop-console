package com.bgconsole.desktop.profile;

import java.util.List;

public interface ProfileService {

    List<Profile> loadProfiles(String path);

    Profile save(Profile profile);

    void delete(Profile profile);

    Profile create(Profile profile);
}
