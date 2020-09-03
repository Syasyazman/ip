import duke.GuiUi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    private final Image dekuDuke = new Image(this.getClass().getResourceAsStream("/images/deku.jpg"));
    private GuiUi guiui = new GuiUi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().getContainer().getChildren()
                    .add(DialogBox.getDukeDialog(guiui.printIntro(), dekuDuke));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}