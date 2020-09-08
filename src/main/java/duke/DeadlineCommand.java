package duke;

import java.time.LocalDate;

/**
 * Command that handles instantiation of Deadline tasks in TaskList
 */
public class DeadlineCommand extends Command {
    protected String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a task of deadline type and adds it to task list
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ") && input.contains("/by")) {
                String[] inputArr = input.split("/by ", 2); // split to get deadline of task
                String item = inputArr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                LocalDate date = null;

                try {
                    date = ui.convertToLocalDate(inputArr[1]);
                } catch (IllegalArgumentException e) {
                    // intentionally empty
                    // to catch unsuccessful date conversion
                    // actual date will be given when creating task to compensate for it
                }

                Task task = new Deadline(item, "D", inputArr[1], date);

                // add item to list
                tasks.addToList(task); // add to-do task to ls

                // print confirmation of adding task
                ui.confirmAddTask(task, tasks.getIndex());
            } else { // if format of deadline task is wrong
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            ui.showInvalidDeadlineFormatError();
        }
    }

    /**
     * Creates a task of deadline type and adds it to task list. Then returns response to user
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param guiui a GuiUi that interacts with user
     * @param storage a Storage that deals with hard disk file
     * @return a message response to user in String
     */
    @Override
    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        try {
            if (input.contains(" ") && input.contains("/by")) {
                String[] inputArr = input.split("/by ", 2); // split to get deadline of task
                String item = inputArr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                LocalDate date = null;

                try {
                    date = guiui.convertToLocalDate(inputArr[1]);
                } catch (IllegalArgumentException e) {
                    // intentionally empty
                    // to catch unsuccessful date conversion
                    // actual date will be given when creating task to compensate for it
                }

                Task task = new Deadline(item, "D", inputArr[1], date);

                // add item to list
                tasks.addToList(task); // add to-do task to ls

                // print confirmation of adding task
                return guiui.confirmAddToTaskList(task, tasks.getIndex());
            } else { // if format of deadline task is wrong
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            return guiui.showInvalidDeadlineFormat();
        }
    }
}
