package com.bgconsole.desktop.workspace;

import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.utils.ParseYAMLFile;

import java.io.IOException;

public class WorkspaceServiceImpl implements WorkspaceService {


    @Override
    public Workspace createWorkspace(String name, String path) {
        return null;
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
