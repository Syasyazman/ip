package duke;

import java.util.List;

/**
 * Command that handles retrieval of task list in TaskList
 */
public class ListCommand extends Command {

    /**
     * Retrieves task list for user
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> ls = tasks.getls();
        int index = tasks.getIndex();
        ui.getList(index, ls);
    }

    @Override
    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        List<Task> ls = tasks.getls();
        int index = tasks.getIndex();
        return guiui.getTaskList(index, ls);
    }
}
