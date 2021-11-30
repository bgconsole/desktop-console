package com.bgconsole.desktop.global_input;

import com.bgconsole.desktop.AppData;
import com.bgconsole.desktop.command.Command;
import com.bgconsole.desktop.ui.global_window.GlobalWindowController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.simplenativehooks.NativeHookInitializer;
import org.simplenativehooks.NativeKeyHook;
import org.simplenativehooks.events.NativeKeyEvent;
import org.simplenativehooks.staticResources.BootStrapResources;
import org.simplenativehooks.utilities.Function;

import java.io.IOException;
import java.net.URL;


public class GlobalKeyListener implements ExecCommand {

    private boolean ctrl = false;
    private boolean alt = false;
    private boolean x = false;

    private static final int CTRL = 17;
    private static final int ALT = 18;
    private static final int X = 88;

    private Stage newWindow;

    public GlobalKeyListener() {
        final GlobalKeyListener keyListener = this;
        // Thread thread = new Thread(() -> {

        try {
            BootStrapResources.extractResources();
        } catch (IOException e) {
            System.out.println("Cannot extract bootstrap resources.");
            e.printStackTrace();
            System.exit(2);
        }
        /* Initializing global hooks */
        NativeHookInitializer.of().start();


        /* Set up callbacks */
        NativeKeyHook key = NativeKeyHook.of();
        key.setKeyPressed(new Function<>() {
            @Override
            public Boolean apply(NativeKeyEvent d) {
                if (d.getKey() == CTRL) {
                    ctrl = true;
                }
                if (d.getKey() == ALT) {
                    alt = true;
                }
                if (d.getKey() == X) {
                    x = true;
                }
                if (ctrl && alt && x) {
                    Platform.runLater(() -> {
//                        newWindow = new Stage(StageStyle.UTILITY);
//                        newWindow.setTitle("Exec command");
//                        newWindow.initModality(Modality.APPLICATION_MODAL);

//                        AutoCompletionTextField text = new AutoCompletionTextField();


//                        for (CommandList commandList : ) {
//                            //                                commands.add(command.getName() + "(" + command.getAlias() + ")");
//                            commands.addAll(commandList.getCommands());
//                        }

//                        ComboBox<Command> text = new ComboBox<>();
//                        text.getItems().addAll(commands);
////                        text.s
//                        new AutoCompleteBox(text, keyListener);

//                        AutoCompleteTextField text = new AutoCompleteTextField(commands);
////                        TextField text = new TextField();
//                        text.setPromptText("Type a command");
//                        text.setMinHeight(60);
//                        text.setMinWidth(400);
//                        text.setStyle("-fx-text-box-border: #242424;-fx-border-width: 5px; -fx-focus-color: #242424;");
////                        text.setFont(new Font(24));
//                        text.requestFocus();
//                        text.setOnAction((event) -> {
////                            int selectedIndex = text.getSelectionModel().getSelectedIndex();
//                            Command command = text.getSelectionModel().getSelectedItem();
//                            newWindow.close();
//                            AppData.instance.runCommand(command.getAlias());
//                        });


//                        StackPane secondaryLayout = new StackPane();
//                        secondaryLayout.getChildren().add(text);

//                        Scene secondScene = new Scene(secondaryLayout);
//                        secondScene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());
//
//                        newWindow.setAlwaysOnTop(true);
//                        newWindow.setResizable(false);
//                        newWindow.requestFocus();
//                        newWindow.setScene(secondScene);
//                        newWindow.toFront();
//                        newWindow.show();

                        try {
                            openWindow();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                }
                return true;
            }
        });
        key.setKeyReleased(new Function<>() {
            @Override
            public Boolean apply(NativeKeyEvent d) {
                if (d.getKey() == CTRL) {
                    ctrl = false;
                }
                if (d.getKey() == ALT) {
                    alt = false;
                }
                if (d.getKey() == X) {
                    x = false;
                }
                return true;
            }
        });
        key.startListening();
        // });
        // thread.start();
    }

    private void openWindow() throws IOException {
        newWindow = new Stage(StageStyle.UTILITY);
        newWindow.setTitle("Exec command");
        newWindow.initModality(Modality.APPLICATION_MODAL);

        URL resource = getClass().getResource("/com/bgconsole/desktop/global_window.fxml");
        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        GlobalWindowController controller = loader.getController();
        controller.setStage(newWindow);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

        newWindow.setAlwaysOnTop(true);
        newWindow.setResizable(false);
        newWindow.requestFocus();
        newWindow.setScene(scene);
        newWindow.toFront();
        newWindow.show();
    }

    @Override
    public void exec(Command command) {
        newWindow.close();
        AppData.instance.get(null).runCommand(command.getAlias());
    }
}

//public class GlobalKeyListener implements NativeKeyListener {
//
//    private boolean ctrl = false;
//    private boolean alt = false;
//    private boolean x = false;
//
//    public void nativeKeyPressed(NativeKeyEvent e) {
//        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
//            ctrl = true;
//        }
//        if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
//            alt = true;
//        }
//        if (e.getKeyCode() == NativeKeyEvent.VC_X) {
//            x = true;
//        }
//
//        if (ctrl && alt && x) {
//            Platform.runLater(() -> {
//                Stage newWindow = new Stage(StageStyle.UTILITY);
//                newWindow.setTitle("Exec command");
//                newWindow.initModality(Modality.APPLICATION_MODAL);
//
//                TextField text = new TextField();
//                text.setPromptText("Type a command");
//                text.setMinHeight(60);
//                text.setMinWidth(400);
//                text.setStyle("-fx-text-box-border: #242424;-fx-border-width: 5px; -fx-focus-color: #242424;");
//                text.setFont(new Font(24));
//                text.requestFocus();
//                text.setOnKeyPressed(ke -> {
//                    if (ke.getCode().equals(KeyCode.ESCAPE)) {
//                        newWindow.close();
//                    }
//                    if (ke.getCode().equals(KeyCode.ENTER)) {
//                        String command = text.getText();
//                        newWindow.close();
//                        AppData.instance.runCommand(command);
//                    }
//                });
//
//                StackPane secondaryLayout = new StackPane();
//                secondaryLayout.getChildren().add(text);
//
//                Scene secondScene = new Scene(secondaryLayout);
//
//                newWindow.setAlwaysOnTop(true);
//                newWindow.setResizable(false);
//                newWindow.requestFocus();
//                newWindow.setScene(secondScene);
//                newWindow.toFront();
//                newWindow.show();
//
//            });
//        }
//    }
//
//    public void nativeKeyReleased(NativeKeyEvent e) {
//
//        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
//            ctrl = false;
//        }
//        if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
//            alt = false;
//        }
//        if (e.getKeyCode() == NativeKeyEvent.VC_X) {
//            x = false;
//        }
//    }
//
//    public void nativeKeyTyped(NativeKeyEvent e) {
//    }
//
//}
