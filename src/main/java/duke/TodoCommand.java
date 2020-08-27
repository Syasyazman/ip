package duke;

public class TodoCommand extends Command {
    protected String input;

    public TodoCommand(String input) {
        this.input = input;
    }

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
