package src.main.java;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {
    protected String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes task from task list
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= tasks.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                tasks.deleteTask(parseInt(input.split(" ", 2)[1]) - 1);
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            ui.showInvalidDeleteTaskError();
        }
    }

}
