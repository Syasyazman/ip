package duke;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

/**
 * Command that handles editing of task dates in task list
 */
public class EditDateCommand extends Command {
    protected String input;

    public EditDateCommand(String input) {
        this.input = input;
    }

    /**
     * Updates the date of task in task list
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ")) {
                String[] inputArr = input.split(" ", 3);
                int indexOfTask = parseInt(inputArr[1]);
                if (indexOfTask < tasks.getIndex() && indexOfTask > 0) {
                    LocalDate date = null;

                    try {
                        date = ui.convertToLocalDate(inputArr[2]);
                    } catch (IllegalArgumentException e) {
                        // intentionally empty
                        // to catch unsuccessful date conversion
                        // actual date will be given when creating task to compensate for it
                    }

                    Task task = tasks.getTaskByIndex(indexOfTask);
                    tasks.deleteTask(indexOfTask);
                    String taskType = task.getSign();

                    switch (taskType) {
                        case "<T>":
                            ui.showInvalidTaskTypeEditError();
                            break;
                        case "<D>":
                            Deadline newTaskD = new Deadline(task.getItem(), "D", inputArr[2], date);
                            tasks.addToList(newTaskD);
                            break;
                        default:
                            assert taskType.equals("<E>");
                            Event newTaskE = new Event(task.getItem(), "D", inputArr[2], date);
                            tasks.addToList(newTaskE);
                            break;
                    }
                }
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            ui.showInvalidEditDateOfTaskError();
        }
    }

    /**
     * Updates the date of task in task list. Then returns response to user
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param guiui a GuiUi that interacts with user
     * @param storage a Storage that deals with hard disk file
     * @return a message response to user in String
     */
    @Override
    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        try {
            if (input.contains(" ")) {
                String[] inputArr = input.split(" ", 3);
                int indexOfTask = parseInt(inputArr[1]);
                if (indexOfTask < tasks.getIndex() && indexOfTask > 0) {
                    LocalDate date = null;

                    try {
                        date = guiui.convertToLocalDate(inputArr[2]);
                    } catch (IllegalArgumentException e) {
                        // intentionally empty
                        // to catch unsuccessful date conversion
                        // actual date will be given when creating task to compensate for it
                    }

                    Task task = tasks.getTaskByIndex(indexOfTask - 1);
                    String taskType = task.getSign();

                    switch (taskType) {
                        case "<T>":
                            return guiui.showInvalidTaskTypeEdit();
                        case "<D>":
                            tasks.deleteTask(indexOfTask - 1);
                            Deadline newTaskD = new Deadline(task.getItem(), "D", inputArr[2], date);
                            tasks.addToList(newTaskD);
                            return "Successfully updated !!";
                        default:
                            assert taskType.equals("<E>");
                            tasks.deleteTask(indexOfTask - 1);
                            Event newTaskE = new Event(task.getItem(), "D", inputArr[2], date);
                            tasks.addToList(newTaskE);
                            return "Successfully updated !!";
                    }
                } else {
                    return guiui.showInvalidIndexOfEditTask();
                }
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            return guiui.showInvalidEditDateOfTask();
        }
    }
}