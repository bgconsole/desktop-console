package com.bgconsole.desktop.utils;

import com.bgconsole.desktop.profile.Profile;
import javafx.util.StringConverter;

import java.util.List;

public class ProfileObservableConverter extends StringConverter<Profile> {

    private final List<Profile> profiles;

    public ProfileObservableConverter(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString(Profile profile) {
        return profile != null ? profile.getName() : null;
    }

    @Override
    public Profile fromString(String s) {
        return profiles.stream().filter(profile -> profile.getName().equals(s)).findFirst().orElse(null);
    }
}
