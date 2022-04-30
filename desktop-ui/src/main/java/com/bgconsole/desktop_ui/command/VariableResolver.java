package com.bgconsole.desktop_ui.command;

import java.util.Optional;

public interface VariableResolver {
    Optional<String> resolve(String variable);
}
