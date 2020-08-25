package src.main.java;

public class Parser {
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) { // if user calls for list
            return new ListCommand();
        } else if (input.split(" ")[0].equals("done")) { // if its "done x"
            return new DoneCommand(input);
        } else if (input.split(" ")[0].equals("todo")) { // if task type is to-do
           return new TodoCommand(input);
        } else if (input.split(" ")[0].equals("deadline")) { // if task type is deadline
            return new DeadlineCommand(input);
        } else if (input.split(" ")[0].equals("event")) { // if task type is deadline
            return new EventCommand(input);
        } else if (input.split(" ")[0].equals("delete")) {
            return new DeleteCommand(input);
        } else { // if input is not a task
            return null;
        }
    }
}
