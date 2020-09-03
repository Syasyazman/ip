package duke;

import static java.lang.Integer.parseInt;

/**
 * Command that handles completion of tasks in TaskList
 */
public class DoneCommand extends Command {
    protected String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a task as completed and reflects it in the task list
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= tasks.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                int index = parseInt(input.split(" ", 2)[1]) - 1;
                tasks.markDone(index);
                ui.printMarkDone(index, tasks.getls());
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            ui.showInvalidTaskNumberError();
        }
    }

    @Override
    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        try {
            if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= tasks.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                int index = parseInt(input.split(" ", 2)[1]) - 1;
                tasks.markDone(index);

                return guiui.printDone(index, tasks.getls());
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            return guiui.showInvalidTaskNumber();
        }
    }
}