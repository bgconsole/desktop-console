package com.bgconsole.desktop_ui.config;

import com.bgconsole.desktop_ui.environment.Environment;
import com.bgconsole.desktop_ui.Config;

public interface ConfigService {
    Environment loadConfig(Environment environment, Config config);
}
