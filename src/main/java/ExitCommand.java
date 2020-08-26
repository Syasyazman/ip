package src.main.java;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Stores task list in hard disk and prints exit for Duke
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showUnsuccessfulFileWriteError();
        }
    }
}
