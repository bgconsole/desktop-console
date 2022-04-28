package com.bgconsole.desktop.workspace;

import com.bgconsole.desktop.location.Location;

public interface WorkspaceService {

    Workspace createWorkspace(String profileId, String name, String path);

    Workspace createLocalWorkspace(String workspaceId);

    Workspace loadWorkspace(String path);

    Workspace createCommandList(Location location, String name);

    Workspace createCommandListLocally(Location location, String name);

}
