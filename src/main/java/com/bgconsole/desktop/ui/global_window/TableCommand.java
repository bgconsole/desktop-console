package com.bgconsole.desktop.ui.global_window;

import com.bgconsole.desktop.LocationData;
import com.bgconsole.desktop.command.Command;

public class TableCommand {

    private Command command;
    private LocationData locationData;

    public TableCommand(Command command, LocationData locationData) {
        this.command = command;
        this.locationData = locationData;
    }

    public String getName() {
        return command.getName();
    }

    public String getCommand() {
        return command.getCommand();
    }

    public String getWorkspace() {
        return locationData.getWorkspaceName();
    }

    public String getAlias() {
        return command.getAlias();
    }

    public LocationData getLocationData() {
        return locationData;
    }
}
