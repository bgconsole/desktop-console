package com.bgconsole.desktop_ui.terminal;

import com.bgconsole.desktop_ui.command.CommandService;
import com.bgconsole.desktop_ui.command.VariableResolver;
import com.bgconsole.desktop_ui.environment.Environment;
import com.bgconsole.desktop_ui.variable.Variable;
import com.bgconsole.desktop_ui.variable.VariableService;

public class TerminalServiceImpl implements TerminalService {

    private final VariableService variableService;

    private final CommandService commandService;

    public TerminalServiceImpl(VariableService variableService, CommandService commandService) {
        this.variableService = variableService;
        this.commandService = commandService;
    }

    @Override
    public void openTerminal(Environment environment, String id, String name, OpenerCallBack callBack, VariableResolver resolver) {
        environment.getTerminalList().getOrOpen(id, name, (terminal, isNew) -> {
            if (isNew) {
                for (Variable variable : variableService.findVars(environment, "ON_INIT:" + id)) {
                    commandService.sendCommand(terminal.getTerminalTab(), variable.getValue(), resolver);
                }
            }
            callBack.openerCallBack(terminal, isNew);
        });
    }

    @Override
    public void closeTerminal(Environment environment, Terminal terminal) {
        environment.getTerminalList().closeTerminal(terminal);
    }
}
