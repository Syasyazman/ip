package src.main.java;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    String input;

    public FindCommand(String input) {
        this.input = input;
    }

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
