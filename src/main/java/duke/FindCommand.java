package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Command that handles searching of keywords in task list of TaskList
 */
public class FindCommand extends Command {
    String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Searches the task list to find tasks that contains the keyword
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> ls = tasks.getls();
        List<Task> output = new ArrayList<>();

       // adds tasks which contains user input as substring
        for (Task task : ls) {
            if (task.getItem().contains(this.input)) {
                output.add(task);
            }
        }

        if (!output.isEmpty()) {
            ui.showSearchResults(output);
        } else {
            ui.showEmptySearchResults();
        }
    }
}
