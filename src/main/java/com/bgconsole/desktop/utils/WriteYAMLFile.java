package com.bgconsole.desktop.utils;

import com.bgconsole.desktop.command.Command;
import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.variable.Variable;
import com.bgconsole.desktop.workspace.Workspace;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WriteYAMLFile {

    private WriteYAMLFile() {
    }

    public static void writeVariables(List<Variable> variables, String absoluteFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(absoluteFile), variables);
    }

    public static void writeCommands(List<Command> commands, String absoluteFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(absoluteFile), commands);
    }

    public static void writeWorkspace(Workspace workspace, String absoluteFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(absoluteFile), workspace);
    }

    public static void writeLocations(List<Location> locations, String absoluteFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(absoluteFile), locations);
    }

}
