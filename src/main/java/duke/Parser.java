package duke;

/**
 * Handles and makes sense of user input
 */
public class Parser {

    /**
     * Makes sense of user input
     *
     * @param input a String that represents user's input
     * @return user's input as a specific command
     */
    public static Command parse(String input) {
        if (input.equals("bye")) { // if user wants to exit task list
            return new ExitCommand();
        } else if (input.equals("list")) { // if user calls for list
            return new ListCommand();
        } else if (input.split(" ")[0].equals("done")) { // if user wants to complete a task
            return new DoneCommand(input);
        } else if (input.split(" ")[0].equals("todo")) { // if task type is to-do
            return new TodoCommand(input);
        } else if (input.split(" ")[0].equals("deadline")) { // if task type is deadline
            return new DeadlineCommand(input);
        } else if (input.split(" ")[0].equals("event")) { // if task type is deadline
            return new EventCommand(input);
        } else if (input.split(" ")[0].equals("delete")) { // if user wants to delete a task
            return new DeleteCommand(input);
        } else if (input.split(" ")[0].equals("find")) { // if user wants to search a keyword
            return new FindCommand(input.split(" ")[1]);
        } else { // if input is not a task
            return null;
        }
    }
}
