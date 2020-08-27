package duke;

import java.time.LocalDate;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ") && input.contains("/by")) {
                String[] arr = input.split("/by ", 2); // split to get deadline of task
                String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                LocalDate date = null;

                try {
                    date = ui.convertToLocalDate(arr[1]);
                } catch (IllegalArgumentException e) {
                    // intentionally empty
                }

                Task task = new Deadline(item, "D", arr[1], date);

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
}
