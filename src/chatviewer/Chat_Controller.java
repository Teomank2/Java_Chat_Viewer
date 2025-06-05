package chatviewer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Chat_Controller {
    private final Stage primaryStage;
    private final BorderPane root;
    private final Label fileLabel;
    private final TextFlow chatDisplay;

    private String lastNickName = "";

    public Chat_Controller(Stage stage) {
        this.primaryStage = stage;
        this.root = new BorderPane();
        this.fileLabel = new Label("No file selected");
        this.chatDisplay = new TextFlow();

        setupUI();
    }

    private void setupUI() {
        Button openButton = new Button("Open Chat File");
        openButton.setOnAction(e -> openFile());

        VBox topBox = new VBox(10, openButton, fileLabel);
        topBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(chatDisplay);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        chatDisplay.setLineSpacing(5);

        root.setTop(topBox);
        root.setCenter(scrollPane);
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Chat File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Message Files", "*.msg")
        );

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            fileLabel.setText(file.getAbsolutePath());
            loadMessages(file);
        }
    }

    private void loadMessages(File file) {
        chatDisplay.getChildren().clear();
        lastNickName = "";

        try {
            List<Message> messages = Chat_Parser.parse(file);
            for (Message msg : messages) {
                displayMessage(msg);
            }
        } catch (IOException e) {
            fileLabel.setText("Error: " + e.getMessage());
        }
    }

    private void displayMessage(Message msg) {
        String timestamp = msg.getTimeStamp();
        String nickname = msg.getNickName();
        String content = msg.getContent();

        String displayName = nickname.equals(lastNickName) ? "..." : nickname;
        lastNickName = nickname;

        Text timeText = new Text("[" + timestamp + "] ");
        timeText.setStyle("-fx-fill: gray;");

        Text nameText = new Text(displayName + ": ");
        nameText.setStyle("-fx-fill: blue;");

        // To process the text file and place the emoticons
        List<Object> contentParts = EmoticonParser.parseContentWithEmojis(content);

        chatDisplay.getChildren().addAll(timeText, nameText);
        for (Object part : contentParts) {
            if (part instanceof Text) {
                ((Text) part).setStyle("-fx-font-weight: bold; -fx-fill: black;");
                chatDisplay.getChildren().add((Text) part);
            } else if (part instanceof ImageView) {
                chatDisplay.getChildren().add((ImageView) part);
            }
        }
        chatDisplay.getChildren().add(new Text("\n")); // To add a new line between messages
    }

    public BorderPane getRoot() {
        return root;
    }
}
