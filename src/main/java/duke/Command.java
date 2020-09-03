package duke;

/**
 * Abstract class for all commands made by Parser
 */
public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {};

    public String guiExecute(TaskList tasks, GuiUi guiui, Storage storage) {
        return null;
    };
}
