package com.bgconsole.desktop_ui.command;

import com.bgconsole.desktop_engine.utils.VariableReplacerUtils;
import com.bgconsole.desktop_ui.environment.Environment;
import com.bgconsole.desktop_ui.variable.VariableList;
import com.bgconsole.domain.Variable;
import com.kodedu.terminalfx.TerminalTab;

import java.util.List;
import java.util.Optional;

public class CommandServiceImpl implements CommandService {

    private static final String LINE_RETURN = System.getProperty("line.separator");

    @Override
    public CommandList loadCommands(String name, String path) {
        CommandList commands = new CommandList(name, path);
//        try {
//            commands.setNewList(ParseYAMLFile.readCommands(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return commands;
    }

    @Override
    public Optional<Command> findAlias(Environment environment, String alias) {
        for (CommandList commandList : environment.getCommandLists()) {
            for (Command command : commandList.getCommands()) {
                if (command.getAlias().equals(alias)) {
                    return Optional.of(command);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void sendCommand(TerminalTab terminalTab, String command, List<Variable> variables) {
        String[] commands = command.split("\n");
        for (String cmd : commands) {
            sendSplitCommand(terminalTab, cmd, variables);
        }
    }

    @Override
    public List<Command> replaceVars(List<Variable> vars, List<Command> commands) {
        for (Command command : commands) {
            for (Variable var : vars) {
                command.setCommand(command.getCommand().replace("${" + var.getName() + "}", var.getValue()));
            }
        }
        return commands;
    }

    @Override
    public List<Command> replaceAllVars(List<VariableList> vars, List<Command> commands) {
//        for (VariableList variableList : vars) {
//            commands = replaceVars(variableList.getVariables(), commands);
//        }
        return commands;
    }

    private void sendSplitCommand(TerminalTab terminalTab, String command, List<Variable> variables) {
        String finalCommand = VariableReplacerUtils.INSTANCE.replaceVariable(command, variables);
        terminalTab.onTerminalFxReady(() -> terminalTab.getTerminal().command(finalCommand + LINE_RETURN));
    }

}
