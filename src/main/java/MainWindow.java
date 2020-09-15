import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image bakugoUser = new Image(this.getClass().getResourceAsStream("/images/bakugo.jpg"));
    private final Image dekuDuke = new Image(this.getClass().getResourceAsStream("/images/deku.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public VBox getContainer() {
        return dialogContainer;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, bakugoUser),
                DialogBox.getDukeDialog(response, dekuDuke)
        );
        userInput.clear();

        if (response.equals("oh man ... bye ~~ o.o")) {
            // terminate program
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}