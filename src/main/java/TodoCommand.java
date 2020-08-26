package src.main.java;

public class TodoCommand extends Command {
    protected String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a task of command type and adds it to task list
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.contains(" ")) {
                String[] arr = input.split(" ", 2); // split input to get task item

                Task task = new Todo(arr[1], "T");

                // add item to list
                tasks.addToList(task); // add to-do task to ls

                // print confirmation of adding task
                ui.confirmAddTask(task, tasks.getIndex());
            } else {
                throw new DukeException("invalid input: " + input);
            }
        } catch (Exception e) {
            ui.showInvalidTodoFormatError();
        }
    }
}
