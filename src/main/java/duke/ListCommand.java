package duke;

import java.util.List;

public class ListCommand extends Command {

    /**
     * Retrieves task list for user
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> ls = tasks.getls();
        int index = tasks.getIndex();
        ui.getList(index, ls);
    }
}
