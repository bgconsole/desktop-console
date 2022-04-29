package com.bgconsole.desktop.workspace;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class WorkspaceServiceImpl implements WorkspaceService {


    @Override
    public Workspace createWorkspace(String profileId, String name, String path) {
        String uuid = UUID.randomUUID().toString();
        Workspace workspace = new Workspace(uuid, name);
        try {
            Path toPath = Paths.get(path);
            Files.createDirectories(toPath);
            String workspaceFile = Paths.get(path, "workspace.yaml").toString();
            WriteYAMLFile.writeWorkspace(workspace, workspaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    @Override
    public Workspace createLocalWorkspace(String workspaceId) {
        return null;
    }

    @Override
    public Workspace loadWorkspace(String path) {
        try {
            return ParseYAMLFile.readWorkspace(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Workspace createCommandList(Location location, String name) {
        return null;
    }

    @Override
    public Workspace createCommandListLocally(Location location, String name) {
        return null;
    }
}
