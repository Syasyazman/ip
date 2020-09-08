package duke;

import java.io.IOException;

/**
 * Command that handles termination of Duke
 */
public class ExitCommand extends Command {

    /**
     * Stores task list in hard disk and prints exit for Duke
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.writeToFile(tasks);
            ui.sayBye();
        } catch (IOException e) {
            ui.showUnsuccessfulFileWriteError();
        }
    }

    /**
     * Stores task list in hard disk and prints exit for Duke. Then returns response to user
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param guiui a GuiUi that interacts with user
     * @param storage a Storage that deals with hard disk file
     * @return a message response to user in String
     */
    @Override
    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        try {
            storage.writeToFile(tasks);
            return guiui.printBye();
        } catch (IOException e) {
            return guiui.showUnsuccessfulFileWrite();
        }
    }
}
