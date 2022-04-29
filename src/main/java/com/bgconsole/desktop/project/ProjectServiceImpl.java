package com.bgconsole.desktop.project;

import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;
import com.bgconsole.desktop.workspace.Workspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public Project createProject(Workspace workspace, String name) {
        String uuid = UUID.randomUUID().toString();
        Project project = new Project(uuid, name, "");
        Path dir = Paths.get(workspace.getPath(), "projects", uuid);
        try {
            Files.createDirectories(dir);
            WriteYAMLFile.writeProject(project, Paths.get(dir.toString(), "project.yaml").toString());
            return project;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Project loadProject(String dir) {
        try {
            return ParseYAMLFile.readProject(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> loadProjectByWorkspaceDir(String path) {
        File file = Paths.get(path, "projects").toFile();
        List<Project> projects = new ArrayList<>();
        if (file.exists() && file.isDirectory()) {
            for (File projectDir : Objects.requireNonNull(file.listFiles())) {
                if (projectDir.isDirectory()) {
                    try {
                        projects.add(ParseYAMLFile.readProject(Paths.get(projectDir.getAbsolutePath(), "project.yaml").toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return projects;
    }
}
