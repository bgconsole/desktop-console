package com.bgconsole.desktop_ui.terminal;

public interface TerminalService {

    void openTerminal(String id, String name, OpenerCallBack callBack);

    void closeTerminal(Terminal terminal);
}
