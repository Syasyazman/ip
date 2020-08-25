import src.main.java.Task;
import src.main.java.Todo;
import src.main.java.Deadline;
import src.main.java.Event;
import src.main.java.DukeException;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static java.lang.Integer.parseInt;

public class Duke {
    int index;
    List<Task> ls;

    public Duke() {
        this.index = 0;
        this.ls = new ArrayList<>();
    }

    // prints introduction of klaun
    public void introDuke() {
        System.out.println("I'm Klaun (=^.^=) How are you doing today ?\n");
        System.out.println("Is there anything I can help you with ?\n");
        System.out.println("<------------------------------------------------------------>\n");
    }

    public int getIndex() {
        return this.index;
    }

    // prints output based on command
    public void sayBye() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("oh man ... bye ~~ o.o \n");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    // prints list of tasks
    public void getList() {
        System.out.println("##############################################################\n");
        System.out.println("Here's your amazing task list :");
        for (int i = 0; i < this.index; i++) {
            Task task = this.ls.get(i);
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

    // adds task to list
    public void addToList(Task task) {
        this.ls.add(task);
        this.index++; // increment index
    }

    // mark task as done
    public void markDone(int index) {
        this.ls.get(index).markAsDone();

        Task task = this.ls.get(index);
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

    // print task that has just been added
    public void confirmAddTask(Task task) {
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

        System.out.println("You have a total of " + this.index + " task(s) in your list !\n");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }

    public void deleteTask(int index) {
        Task task = this.ls.get(index);
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();
        int num = index + 1;

        // remove task from array
        this.ls.remove(index);
        this.index--;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("I've removed this task for you : ");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(num + ". " + taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(taskType + " " +  status + " " + item + " -> by : " + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(taskType + " " + status + " " + item + " -> at : " + actualTask.getTime() + "\n");
        }
        System.out.println("You now have " + this.index + " task(s) in your list !\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    // checks if hard disk file directory exists
    public boolean checkIfFileExists() {
        String home = System.getProperty("user.home");

        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "CS2103", "Projects", "iP", "data", "duke.txt");
        return java.nio.file.Files.exists(path);
    }

    // add task to hard disk file
    public void addTasksToFile(FileWriter writer) {
        try {
            if (writer != null) {
                for (Task task : this.ls) {
                    // get details about task
                    String item = task.getItem();
                    String status = task.getStatus();
                    String taskType = task.getSign();

                    // check task type
                    if (task instanceof Todo) { // if task is to-do
                        writer.write(taskType + " / " + status + " / " + item + "\n");
                    } else if (task instanceof Deadline) { // if task is deadline
                        Deadline actualTask = (Deadline) task;
                        writer.write(taskType + " / " +  status + " / " + item + " / " + actualTask.getDeadline() + "\n");
                    } else { // if task is event
                        Event actualTask = (Event) task;
                        writer.write(taskType + " / " + status + " / " + item + " / " + actualTask.getTime() + "\n");
                    }
                }
            } else {
                throw new IOException("invalid writer");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: invalid writer");
//            e.printStackTrace();
        }
    }

    public void loadDataFromFile(File file) {
        Scanner sc = null;
        try {
            if (file != null) {
                sc = new Scanner(file);

                while(sc.hasNextLine()) {
                    String line = sc.nextLine();

                    String[] arr = line.split(" / ", 3);
                    String taskType = arr[0];
                    String status = arr[1];
                    String restOfTask = arr[2];

                    if (taskType.equals("<T>")) { // if task is a to-do
                        Task task = new Todo(restOfTask, "T");
                        if (status.equals("[\u2713]")) { // task is done
                            task.markAsDone();
                        }
                        this.addToList(task);
                    } else if (taskType.equals("<D>")) { // if task is a deadline
                        String[] taskArr = restOfTask.split(" / ");
                        String item = taskArr[0];
                        String deadline = taskArr[1];
                        Task task = new Deadline(item, "D",  deadline);

                        if (status.equals("[\u2713]")) { // task is done
                            task.markAsDone();
                        }

                        this.addToList(task);
                    } else { // if task is an event
                        String[] taskArr = restOfTask.split(" / ");
                        String item = taskArr[0];
                        String deadline = taskArr[1];
                        Task task = new Event(item, "E", deadline);

                        if (status.equals("[\u2713]")) {
                            task.markAsDone();
                        }

                        this.addToList(task);
                    }
                }
            } else {
                throw new FileNotFoundException("File not found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
//            e.printStackTrace();
        }

    }


    // returns date as LocalDate type
    public LocalDate convertToLocalDate(String strDate){
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

    public static void main(String[] args) throws DukeException {

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

        Scanner sc = new Scanner(System.in);

        // instantiate duke object
        Duke klaun = new Duke();

        try {
            if (klaun.checkIfFileExists()) {
                File file = new File("data/duke.txt");
                klaun.loadDataFromFile(file);

                // introduce duke
                klaun.introDuke();

                // get input
                String input = "";

                // checks for next line of input
                while (sc.hasNextLine()) {
                    input = sc.nextLine();

                    try {
                        if (input.equals("bye")) {
                            klaun.sayBye();
                            FileWriter writer = new FileWriter(file);
                            klaun.addTasksToFile(writer);
                            writer.close();
                            break;
                        } else if (input.equals("list")) { // if user calls for list
                            klaun.getList();
                        } else if (input.split(" ")[0].equals("done")) { // if its "done x"
                            try {
                                if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                                    klaun.markDone(parseInt(input.split(" ", 2)[1]) - 1);
                                } else {
                                    throw new DukeException("invalid input: " + input);
                                }
                            } catch (Exception e) {
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                                System.out.println("Oops ... you should provide a valid task number to complete ~ \n");
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                            }
                        } else if (input.split(" ")[0].equals("todo")) { // if task type is to-do
                            try {
                                if (input.contains(" ")) {
                                    String[] arr = input.split(" ", 2); // split input to get task item
                                    Task task = new Todo(arr[1], "T");

                                    // add item to list
                                    klaun.addToList(task); // add to-do task to ls

                                    // print confirmation of adding task
                                    klaun.confirmAddTask(task);
                                } else {
                                    throw new DukeException("invalid input: " + input);
                                }
                            } catch (Exception e) {
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                                System.out.println("Oh no !! I think you forgot to add your todo description :O\n");
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                            }
                        } else if (input.split(" ")[0].equals("deadline")) { // if task type is deadline
                            try {
                                if (input.contains(" ") && input.contains("/by")) {
                                    String[] arr = input.split("/by ", 2); // split to get deadline of task
                                    String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string
                                    LocalDate date = null;

                                    try {
                                        date = klaun.convertToLocalDate(arr[1]);
                                    } catch (IllegalArgumentException e) {
                                        // intentionally empty
                                    }

                                    Task task = new Deadline(item, "D", arr[1], date);

                                    // add item to list
                                    klaun.addToList(task);

                                    // print confirmation of adding task
                                    klaun.confirmAddTask(task);
                                } else { // if format of deadline task is wrong
                                    throw new DukeException("invalid input: " + input);
                                }
                            } catch (Exception e) {
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                                System.out.println("Oh no !! Your format should be \"deadline ____ /by ____\" \n");
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                            }
                        } else if (input.split(" ")[0].equals("event")) { // if task type is deadline
                            try {
                                if (input.contains(" ") && input.contains("/at")) {
                                    String[] arr = input.split("/at ", 2); // split to get time of task
                                    String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string
                                    LocalDate date = null;

                                    try {
                                        date = klaun.convertToLocalDate(arr[1]);
                                    } catch (IllegalArgumentException e) {
                                        // intentionally empty
                                    }

                                    Task task = new Event(item, "E", arr[1], date);

                                    // add item to list
                                    klaun.addToList(task);

                                    // print confirmation of adding task
                                    klaun.confirmAddTask(task);
                                } else { // if format of event task is wrong
                                    throw new DukeException("invalid input: " + input);
                                }
                            } catch (Exception e) {
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                                System.out.println("Oh no !! Your format should be \"event ____ /at ____\" \n");
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                            }
                        } else if (input.split(" ")[0].equals("delete")) {
                            try {
                                if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                                    klaun.deleteTask(parseInt(input.split(" ", 2)[1]) - 1);
                                } else {
                                    throw new DukeException("invalid input: " + input);
                                }
                            } catch (Exception e) {
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                                System.out.println("Oops ... you should provide a valid task number to delete ~ \n");
                                System.out.println("??????????????????????????????????????????????????????????????\n");
                            }
                        } else { // if input is not a task
                            throw new DukeException(input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("invalid input :(\n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                }
            } else {
                throw new IOException("File not found");
            }
        } catch(IOException e){
            System.out.println("File does not exist!!");
//              e.printStackTrace();
        }
    }
}
