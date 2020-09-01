import duke.*;

import java.util.ArrayList;

/**
 * Main class for Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
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
        // introduce src.main.java.duke
        this.ui.introDuke();

        // get input
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

    public static void main(String[] args) throws DukeException {
        // instantiate src.main.java.duke object
        Duke klaun = new Duke("data/duke.txt");
        klaun.run();
    }
}