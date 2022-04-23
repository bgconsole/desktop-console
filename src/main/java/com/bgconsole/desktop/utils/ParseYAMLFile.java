package com.bgconsole.desktop.utils;

import com.bgconsole.desktop.command.Command;
import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.variable.Variable;
import com.bgconsole.desktop.workspace.Workspace;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParseYAMLFile {

    private ParseYAMLFile() {
    }

    public static List<Command> readCommands(String absoluteFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        URL url = new URL("file:" + absoluteFile);
        return Arrays.asList(objectMapper.readValue(url, Command[].class).clone());
    }

    public static List<Variable> readVariables(String absoluteFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        URL url = new URL("file:" + absoluteFile);
        return Arrays.asList(objectMapper.readValue(url, Variable[].class).clone());
    }

    public static Workspace readWorkspace(String absoluteFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        URL url = new URL("file:" + absoluteFile);
        return objectMapper.readValue(url, Workspace.class);
    }

    public static Profile readProfile(String absoluteFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        URL url = new URL("file:" + absoluteFile);
        return objectMapper.readValue(url, Profile.class);
    }

    public static Project readProject(String absoluteFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        URL url = new URL("file:" + absoluteFile);
        return objectMapper.readValue(url, Project.class);
    }

    private static Profile readProfile(File file) {
        try {
            return readProfile(file.getAbsolutePath());
        } catch (IOException e) {
            return null;
        }
    }

//    public static List<Location> readLocations(String absoluteFile) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        URL url = new URL("file:" + absoluteFile);
//        return Arrays.asList(objectMapper.readValue(url, Location[].class).clone());
//    }

    public static List<Profile> readProfiles(String absoluteDir) throws IOException {
        Path path = Paths.get(absoluteDir);
        File pathToFile = path.toFile();
        if (pathToFile.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(pathToFile.listFiles(file -> !file.isDirectory() && file.getName()
                    .endsWith(".yaml")))).map(ParseYAMLFile::readProfile).filter(Objects::nonNull).toList();
        }
        return Collections.emptyList();
    }

}
