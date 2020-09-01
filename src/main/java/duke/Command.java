package duke;

/**
 * Abstract class for all commands made by Parser
 */
public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {};
}
