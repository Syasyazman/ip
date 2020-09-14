package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * Handles input from user interactions for GUI
 */
public class GuiUi {
    public GuiUi() { }

    /**
     * returns introduction message from deku
     *
     * @return introduction message in String
     */
    public String printIntro() {
        return "Hi I'm Deku (=^.^=) How are you doing today ?\n" +
                "Is there anything I can help you with ?";
    }

    /**
     * returns exit message for deku
     *
     * @return exit message in String
     */
    public String printBye() {
        return "oh man ... bye ~~ o.o";
    }

    /**
     * Provides user with task list
     *
     * @param index an integer representing length of task list
     * @param ls a List containing user's tasks
     */
    public String getTaskList(int index, List<Task> ls) {
        String results = "Here's your amazing task list : \n\n";
        for (int i = 0; i < index; i++) {
            Task task = ls.get(i);
            String item = task.getItem();
            String status = task.getStatus();
            String taskType = task.getSign();
            int num = i + 1;

            if (task instanceof Todo) { // if task is to-do
                results += String.valueOf(num) + ". " + taskType + " " + status + " " + item + "\n";
            } else if (task instanceof Deadline) { // if task is deadline
                Deadline actualTask = (Deadline) task;

                results += String.valueOf(num) + ". " + taskType + " " +  status + " " + item +
                        " -> by : " + actualTask.getDeadline() + "\n";
            } else { // if task type if event
                Event actualTask = (Event) task;

                results += String.valueOf(num) + ". " + taskType + " " + status + " " + item +
                        " -> at : " + actualTask.getTime() + "\n";
            }
        }

        return results;
    }

    /**
     * Provides user with confirmation of task completion
     *
     * @param index an integer representing completed task's number
     * @param ls a List containing user's tasks
     */
    public String printDone(int index, List<Task> ls) {
        Task task = ls.get(index);
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();
        int num = index + 1;

        if (task instanceof Todo) { // if task is to-do
            return "Yayyyy !! Letsgedditt\n\n" +
                    num + ". " + taskType + " " + status + " " + item;
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;

            return "Yayyyy !! Letsgedditt\n\n" +
                    num + ". " + taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline();
        } else { // if task is event
            Event actualTask = (Event) task;

            return "Yayyyy !! Letsgedditt\n\n" +
                    num + ". " + taskType + " " + status + " " + item + " -> at : " + actualTask.getTime();
        }
    }

    /**
     * Prints the task that has just been added to task list
     *
     * @param task user's task
     * @param index an integer representing number of total tasks
     */
    public String confirmAddToTaskList(Task task, int index) {
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();

        if (task instanceof Todo) { // if task is to-do
            return "Okie ! I've added this task for you : \n" +
                    taskType + " " + status + " " + item + "\n\n" +
                    "You have a total of " + index + " task(s) in your list !";
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;

            return "Okie ! I've added this task for you : \n" +
                    taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline() + "\n\n" +
                    "You have a total of " + index + " task(s) in your list !";
        } else { // if task is event
            Event actualTask = (Event) task;

            return "Okie ! I've added this task for you : \n" +
                    taskType + " " + status + " " + item + " -> at : " + actualTask.getTime() + "\n\n" +
                    "You have a total of " + index + " task(s) in your list !";

        }
    }

    /**
     * Returns date in local date format
     *
     * @param strDate a String of date given by user input
     * @return a date in local date format
     */
    public LocalDate convertToLocalDate(String strDate) {
        List<String> dateFormats = Arrays.asList("yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-M-d", "yyyy-M-dd",
                "yyyy-MM-d", "yyyy/MM/dd", "yyyy/M/d", "yyyy/M/dd", "yyyy/MM/d");

        // loop through each possible format and check if strDate can be parsed
        for(String format: dateFormats){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try{
                return LocalDate.parse(strDate, formatter);
            } catch (Exception e) {
                // intentionally empty
                // to catch unsuccessful date parsing
            }
        }

        throw new IllegalArgumentException("Invalid input for date. Given '"+strDate+"', " +
                "expecting format yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd.");
    }

    /**
     * returns list of task that matches search
     *
     * @param ls a list of tasks from TaskList
     * @return a string of tasks
     */
    public String showSearch(List<Task> ls) {
        String results = "Here's your search result(s) :\n\n";
        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            String item = task.getItem();
            String status = task.getStatus();
            String taskType = task.getSign();
            int num = i + 1;

            if (task instanceof Todo) { // if task is to-do
                results += String.valueOf(num) + ". " + taskType + " " + status + " " + item + "\n";
            } else if (task instanceof Deadline) { // if task is deadline
                Deadline actualTask = (Deadline) task;

                results +=  String.valueOf(num) + ". " + taskType + " " +  status + " " + item +
                        " -> by : " + actualTask.getDeadline() + "\n";
            } else { // if task type if event
                Event actualTask = (Event) task;

                results += String.valueOf(num) + ". " + taskType + " " + status + " " + item +
                        " -> at : " + actualTask.getTime() + "\n";
            }
        }

        return results;
    }

    public String showEmptySearch() {
        return "Sorry there are no results that match your search :((";
    }

    public String showFileLoading() {
        return "File cannot be loaded !!";
    }

    public String showInvalidInput() {
        return "Sorry i don't mean to be offensive but i don't get what you're saying :(";
    }

    public String showInvalidEventFormat() {
        return "Oh no !! Your format should be \"event <task> /at <date>\"";
    }

    public String showInvalidDeadlineFormat() {
        return "Oh no !! Your format should be \"deadline <task> /by <date>\"";
    }

    public String showInvalidTodoFormat() {
        return "Oh no !! I think you forgot to add your todo description :O";
    }

    public String showInvalidTaskNumber() {
        return "Oops ... you should provide a valid task number to complete ~";
    }

    public String showInvalidDeleteTask() {
        return "Oops ... you should provide a valid task number to delete ~";
    }

    public String showUnsuccessfulFileWrite() {
        return "Unsuccessful file writing :(";
    }

    public String showInvalidEditDateOfTask() {
        return "Oh no !! Your format should be \"edit <task number> <new date>\"";
    }

    public String showInvalidTaskTypeEdit() {
        return "Im so sorry but I only edit dates of deadlines or events !";
    }

    public String showInvalidIndexOfEditTask() {
        return "Oops ... you should provide a valid task number to edit ~";
    }
}
