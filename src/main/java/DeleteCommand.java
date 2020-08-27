package src.main.java;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {
    protected String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

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
