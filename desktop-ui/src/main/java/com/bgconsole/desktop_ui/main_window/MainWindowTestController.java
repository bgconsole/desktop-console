package com.bgconsole.desktop_ui.main_window;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainWindowTestController {

    @FXML
    private StackPane appContent;

    public void initialize() {
    }

    public void setContent(Node node) {
        appContent.getChildren().add(node);
    }

}


//    URL resource = getClass().getResource("/com/bgconsole/platform/ui/main_window_test.fxml");
//    FXMLLoader loader = new FXMLLoader(resource);
//
//    Parent root = loader.load();
//
//    MainWindowTestController controller = loader.getController();
////        controller.setHostServices(getHostServices());
//
//    Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop_ui/styles.css").toExternalForm());
//
//                stage.setTitle("BG Console");
//                stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop_ui/img/logo.png"))));
//        stage.setScene(scene);
//        stage.show();
//
//final String os = System.getProperty("os.name");
//        if (os != null && os.startsWith("Mac")) {
//        Platform.runLater(() -> {
//        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Terminal");
////                controller.getMenuBar().setUseSystemMenuBar(true);
//
//        });
//        }