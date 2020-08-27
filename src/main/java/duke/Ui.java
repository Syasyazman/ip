package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // prints introduction of klaun
    public void introDuke() {
        String logo = "     .\"\".    .\"\",\n" +
                "     |  |   /  /\n" +
                "     |  |  /  /\n" +
                "     |  | /  /\n" +
                "     |  |/  ;-._\n" +
                "     }  ` _/  / ;\n" +
                "     |  /` ) /  /\n" +
                "     | /  /_/\\_/\\\n" +
                "     |/  /      |\n" +
                "     (  ' \\ '-  |\n" +
                "      \\    `.  /\n" +
                "       |      |\n" +
                "       |      |";

        System.out.println("HIIIIII\n" + logo + "\n");

        System.out.println("I'm Klaun (=^.^=) How are you doing today ?\n");
        System.out.println("Is there anything I can help you with ?\n");
        System.out.println("<------------------------------------------------------------>\n");
    }

    // prints exit message for klaun
    public void sayBye() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("oh man ... bye ~~ o.o \n");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    // returns next line of user input
    public String getInput() {
        if (this.sc.hasNextLine()) {
            return this.sc.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Provides user with task list
     *
     * @param index an integer representing length of task list
     * @param ls a List containing user's tasks
     */
    public void getList(int index, List<Task> ls) {
        System.out.println("##############################################################\n");
        System.out.println("Here's your amazing task list :");
        for (int i = 0; i < index; i++) {
            Task task = ls.get(i);
            String item = task.getItem();
            String status = task.getStatus();
            String taskType = task.getSign();
            int num = i + 1;

            if (task instanceof Todo) { // if task is to-do
                System.out.println(num + ". " + taskType + " " + status + " " + item);
            } else if (task instanceof Deadline) { // if task is deadline
                Deadline actualTask = (Deadline) task;
                System.out.println(num + ". " + taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline());
            } else { // if task type if event
                Event actualTask = (Event) task;
                System.out.println(num + ". " + taskType + " " + status + " " + item + " -> at : " + actualTask.getTime());
            }

        }

        System.out.println(" ");
        System.out.println("##############################################################\n");
    }

    /**
     * Provides user with confirmation of task completion
     *
     * @param index an integer representing completed task's number
     * @param ls a List containing user's tasks
     */
    public void printMarkDone(int index, List<Task> ls) {
        Task task = ls.get(index);
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();
        int num = index + 1;

        System.out.println("==============================================================\n");
        System.out.println("Yayyyy !! Letsgedditt");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(num + ". " + taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(num + ". " + taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(num + ". " + taskType + " " + status + " " + item + " -> at : " + actualTask.getTime() + "\n");
        }
        System.out.println("==============================================================\n");
    }

    /**
     * Prints the task that has just been added to task list
     *
     * @param task user's task
     * @param index an integer representing number of total tasks
     */
    public void confirmAddTask(Task task, int index) {
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        System.out.println("Okie ! I've added this task for you : ");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(taskType + " " + status + " " + item + " -> at : " + actualTask.getTime() + "\n");
        }

        System.out.println("You have a total of " + index + " task(s) in your list !\n");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
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
            }
        }

        throw new IllegalArgumentException("Invalid input for date. Given '"+strDate+"', expecting format yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd.");
    }

    public void showFileLoadingError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("File cannot be loaded !! \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidInputError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("invalid input :(\n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidEventFormatError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Oh no !! Your format should be \"event ____ /at ____\" \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidDeadlineFormatError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Oh no !! Your format should be \"deadline ____ /by ____\" \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidTodoFormatError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Oh no !! I think you forgot to add your todo description :O\n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidTaskNumberError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Oops ... you should provide a valid task number to complete ~ \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showInvalidDeleteTaskError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Oops ... you should provide a valid task number to delete ~ \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }

    public void showUnsuccessfulFileWriteError() {
        System.out.println("??????????????????????????????????????????????????????????????\n");
        System.out.println("Unsuccessful file writing :( \n");
        System.out.println("??????????????????????????????????????????????????????????????\n");
    }
}
