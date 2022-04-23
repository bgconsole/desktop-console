package com.bgconsole.desktop.project;

import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.workspace.Workspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public Project createProject(Workspace workspace, String name) {
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
