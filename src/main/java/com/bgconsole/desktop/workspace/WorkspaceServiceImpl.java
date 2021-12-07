package com.bgconsole.desktop.workspace;

import com.bgconsole.desktop.MainWindowData;
import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.utils.ParseYAMLFile;
import com.bgconsole.desktop.utils.WriteYAMLFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bgconsole.desktop.LocationData.DEFAULT_LOCATION;

public class WorkspaceServiceImpl implements WorkspaceService {

    @Override
    public Workspace createWorkspace(String name, String path) {
        Location newLocation = new Location(UUID.randomUUID().toString(), name, path);
        List<Location> locations = new ArrayList<>(MainWindowData.instance.getLocations());
        locations.add(newLocation);
        Workspace workspace = null;
        try {
            workspace = createAnyWorkspace(name, path);
            WriteYAMLFile.writeLocations(locations, DEFAULT_LOCATION.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    @Override
    public Workspace createLocalWorkspace(String workspaceId) {
        Workspace workspace = null;
        try {
            workspace = createAnyWorkspace(workspaceId, Paths.get(DEFAULT_LOCATION.toString(), workspaceId).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    @Override
    public Workspace loadWorkspace(String path) {
        Workspace workspace = null;
        try {
            workspace = ParseYAMLFile.readWorkspace(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    @Override
    public Workspace createCommandList(Location location, String name) {
        var workspace = loadWorkspace(Paths.get(location.getPath(), "workspace.yaml").toAbsolutePath().toString());
        String cleanName = name.replaceAll(" ", "-").replaceAll("[^\\p{Alpha}\\p{Digit}]+", "");
        WorkspaceCommand command = new WorkspaceCommand(UUID.randomUUID().toString(), name, "default", Paths.get("commands", cleanName + ".yaml").toString());
        workspace.getCommands().add(command);
        try {
            WriteYAMLFile.writeWorkspace(workspace, Paths.get(location.getPath(), "workspace.yaml").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    @Override
    public Workspace createCommandListLocally(Location location, String name) {
        var workspace = loadWorkspace(Paths.get(location.getPath(), "workspace.yaml").toAbsolutePath().toString());
        var localWorkspace = loadWorkspace(Paths.get(location.getPath(), workspace.getId(), "workspace.yaml").toAbsolutePath().toString());
        if (localWorkspace == null) {
            localWorkspace = createLocalWorkspace(workspace.getId());
        }
        String cleanName = name.replaceAll(" ", "-").replaceAll("[^\\p{Alpha}\\p{Digit}]+", "").toLowerCase();
        WorkspaceCommand command = new WorkspaceCommand(UUID.randomUUID().toString(), name, "default", Paths.get("commands", cleanName + ".yaml").toString());
        localWorkspace.getCommands().add(command);
        try {
            WriteYAMLFile.writeWorkspace(localWorkspace, Paths.get(location.getPath(), "workspace.yaml").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localWorkspace;
    }

    private Workspace createAnyWorkspace(String name, String path) throws IOException {
        Path location = Paths.get(path);
        if (!Files.exists(location)) {
            try {
                Files.createDirectories(location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.isDirectory(location)) {
            Workspace workspace = new Workspace();
            workspace.setId(UUID.randomUUID().toString());
            workspace.setName(name);
            workspace.setCommands(new ArrayList<>());
            workspace.setVariables(new ArrayList<>());

            Path commandPath = Paths.get(path, "commands");
            Files.createDirectories(commandPath);
            Path commandFilePath = Paths.get(commandPath.toString(), "commands.yaml");
            WriteYAMLFile.writeCommands(new ArrayList<>(), commandFilePath.toString());

            Path variablePath = Paths.get(path, "variables");
            Files.createDirectories(variablePath);
            Path variableFilePath = Paths.get(variablePath.toString(), "variables.yaml");
            WriteYAMLFile.writeVariables(new ArrayList<>(), variableFilePath.toString());

            workspace.setCommands(new ArrayList<>() {{
                add(new WorkspaceCommand(UUID.randomUUID().toString(), "Default commands", "default", commandFilePath.toString()));
            }});
            workspace.setVariables(new ArrayList<>() {{
                add(new WorkspaceVariable(UUID.randomUUID().toString(), "Default variables", "default", variableFilePath.toString()));
            }});
            WriteYAMLFile.writeWorkspace(workspace, Paths.get(path, "workspace.yaml").toString());

            return workspace;
        }
        return null;
    }

}
