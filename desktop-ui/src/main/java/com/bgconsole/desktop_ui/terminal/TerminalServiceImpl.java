package com.bgconsole.desktop_ui.terminal;

import com.bgconsole.desktop_engine.desktop_services.opened.variable.ResolvedVariableContent;
import com.bgconsole.domain.Variable;
import com.bgconsole.platform.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bgconsole.desktop_engine.desktop_services.opened.variable.ResolvedVariableReduxKt.ENGINE_OPENED_RESOLVED_VARIABLES;

public class TerminalServiceImpl implements TerminalService {

    private final TerminalList terminalList;

    private List<Variable> variables;


    public TerminalServiceImpl(Store store) {
        terminalList = new TerminalList();
        variables = new ArrayList<>();
        store.subscribe(ENGINE_OPENED_RESOLVED_VARIABLES, entity -> variables = ((ResolvedVariableContent) entity).getVariables());
    }

    @Override
    public void openTerminal(String id, String name, OpenerCallBack callBack) {
        terminalList.getOrOpen(id, name, (terminal, isNew) -> {
            if (isNew) {
                List<Variable> filteredVars = variables.stream().filter(variable -> variable.getName().equals("ON_INIT" + id)).collect(Collectors.toList());
                for (Variable variable : filteredVars) {
//                    commandService.sendCommand(terminal.getTerminalTab(), variable.getValue(), variables);
                }
            }
            callBack.openerCallBack(terminal, isNew);
        });
    }

    @Override
    public void closeTerminal(Terminal terminal) {
        terminalList.closeTerminal(terminal);
    }

//    public CommandService getCommandService() {
//        return commandService;
//    }
}
