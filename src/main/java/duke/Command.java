package duke;

public abstract class Command {

    /**
     * Executes a command
     *
     * @param tasks a TaskList that contains a list of tasks
     * @param ui a Ui that interacts with user
     * @param storage a Storage that deals with hard disk file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {};
}
