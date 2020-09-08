import duke.*;

import java.io.File;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

/**
 * Main class for Duke
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private GuiUi guiui;
    private File file;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // images of users
    private final Image dekuDuke = new Image(this.getClass().getResourceAsStream("/images/deku.jpg"));
    private final Image bakugoUser = new Image(this.getClass().getResourceAsStream("/images/bakugo.jpg"));

    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        this.guiui = new GuiUi();
        this.file = new File("data/duke.txt");



        try {
            if (storage.hasFile()) {
                this.tasks = new TaskList(storage.loadDataFromFile(this.ui));
            } else {
                throw new DukeException("file does not exist");
            }
        } catch (DukeException e) {
            this.ui.showFileLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        this.guiui = new GuiUi();

        try {
            if (storage.hasFile()) {
                this.tasks = new TaskList(storage.loadDataFromFile(this.ui));
            } else {
                throw new DukeException("file does not exist");
            }
        } catch (DukeException e) {
            this.ui.showFileLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        // introduce duke
        this.ui.introDuke();

        String input = this.ui.getInput();

        // checks for next line of input
        while (input != null) {
            try {
                Command c = Parser.parse(input);
                if (c != null) { // input is valid
                    if (c instanceof ExitCommand) {
                        c.execute(this.tasks, this.ui, this.storage);
                        break; // terminate program
                    } else {
                        c.execute(this.tasks, this.ui, this.storage);
                        input = this.ui.getInput();
                    }
                } else {
                    input = this.ui.getInput();
                    throw new DukeException("invalid input");
                }
            } catch (Exception e) {
                this.ui.showInvalidInputError();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Step 1. setting up required components
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // Step 2. Formatting the window to look as expected
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 3. Formatting the window to look as expected
        stage.setTitle("Deku");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // call introduction of duke
        introDeku();

        //Step 4. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Introduces Deku when Gui application launches
     */
    public void introDeku() {
        dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog(guiui.printIntro(), dekuDuke));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, bakugoUser),
                DialogBox.getDukeDialog(response, dekuDuke)
        );
        userInput.clear();
    }

    /**
     * Responses to user
     */
    protected String getResponse(String input) {
        Command c = Parser.parse(input);

        if (c != null) { // input is valid
            if (c instanceof ExitCommand) {
                return c.guiExecute(this.tasks, this.guiui, this.storage);
            } else {
                return c.guiExecute(this.tasks, this.guiui, this.storage);
            }
        } else {
            return  guiui.showInvalidInput();
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke deku = new Duke("data/duke.txt");
        deku.run();
    }
}