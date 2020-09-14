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
        } else { // if user wants to complete a task
            String[] inputArr = input.split(" ");

            switch (inputArr[0]) {
                case "done": // if task type is to-do
                    assert inputArr[0].equals("done");
                    return new DoneCommand(input);
                case "todo": // if task type is deadline
                    assert inputArr[0].equals("todo");
                    return new TodoCommand(input);
                case "deadline": // if task type is deadline
                    assert inputArr[0].equals("deadline");
                    return new DeadlineCommand(input);
                case "event":  // if task type is event
                    assert inputArr[0].equals("event");
                    return new EventCommand(input);
                case "delete": // if user wants to delete a task
                    assert inputArr[0].equals("delete");
                    return new DeleteCommand(input);
                case "find": // if user wants to search a keyword
                    assert inputArr[0].equals("find");
                    return new FindCommand(inputArr[1]);
                case "edit": // if user wants to edit a task's date
                    assert inputArr[0].equals("edit");
                    return new EditDateCommand(input);
                default: // if input is not a task
                    return null;
            }

        }
    }
}
