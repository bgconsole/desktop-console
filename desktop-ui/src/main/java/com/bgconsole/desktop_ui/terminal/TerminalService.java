package com.bgconsole.desktop_ui.terminal;

import com.bgconsole.desktop_ui.command.VariableResolver;
import com.bgconsole.desktop_ui.environment.Environment;

public interface TerminalService {

    void openTerminal(Environment environment, String id, String name, OpenerCallBack callBack, VariableResolver resolver);

    void closeTerminal(Environment environment, Terminal terminal);
}
