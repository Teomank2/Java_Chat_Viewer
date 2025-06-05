package chatviewer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Chat_Viewer_Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Chat_Controller controller = new Chat_Controller(primaryStage);
        primaryStage.setTitle("Chat Viewer");
        primaryStage.setScene(new javafx.scene.Scene(controller.getRoot(), 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); }
}
