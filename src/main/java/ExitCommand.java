package src.main.java;

import java.io.IOException;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showUnsuccessfulFileWriteError();
        }
    }
}
