package duke;

import java.util.List;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> ls = tasks.getls();
        int index = tasks.getIndex();
        ui.getList(index, ls);
    }
}
