package com.bgconsole.desktop_ui;

import com.bgconsole.desktop_ui.global_input.GlobalKeyListener;
import com.bgconsole.desktop_ui.main_window.MainWindowRedux;
import com.bgconsole.platform.ui.PlatformWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        new PlatformWindow(stage);


//        controller.setStage(stage);
//        controller.setProfileList(MainWindowData.instance.getProfiles());

        loadComponents();
    }

    private void loadComponents() {
        new MainWindowRedux();
        new GlobalKeyListener();
    }

    public static void main(String[] args) {
        launch(args);
    }

}