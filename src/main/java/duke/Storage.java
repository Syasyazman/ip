package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

/**
 * Handles storage of tasks in a hard disk file
 */
public class Storage {
    protected File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * checks if hard disk file directory exists
     *
     * @return whether the hard disk file exists
     */
    public boolean hasFile() {
        String home = System.getProperty("user.home");

        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "CS2103", "Projects", "iP", "data",
                "src.main.java.duke.txt");
        return java.nio.file.Files.exists(path);
    }

    /**
     * Writes task to hard disk file
     *
     * @param writer a FileWriter to write content into hard disk file
     * @param ls a List that contains task list
     */
    public void addTasksToFile(FileWriter writer, List<Task> ls) {
        try {
            if (writer != null) {
                for (Task task : ls) {
                    // get details about task
                    String item = task.getItem();
                    String status = task.getStatus();
                    String taskType = task.getSign();

                    // check task type
                    if (task instanceof Todo) { // if task is to-do
                        writer.write(taskType + " / " + status + " / " + item + "\n");
                    } else if (task instanceof Deadline) { // if task is deadline
                        Deadline actualTask = (Deadline) task;
                        writer.write(taskType + " / " +  status + " / " + item + " / " +
                                actualTask.getDeadline() + "\n");
                    } else { // if task is event
                        Event actualTask = (Event) task;
                        writer.write(taskType + " / " + status + " / " + item + " / " +
                                actualTask.getTime() + "\n");
                    }
                }
            } else {
                throw new IOException("invalid writer");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: invalid writer");
        }
    }

    /**
     * Retrieves hard disk file's data into a task list
     *
     * @param ui a Ui that interacts with user
     * @return a list of tasks to be added to task list
     */
    public List<Task> loadDataFromFile(Ui ui) {
        Scanner sc = null;
        List<Task> ls = new ArrayList<>();

        try {
            if (this.file != null) {
                sc = new Scanner(this.file);

                while(sc.hasNextLine()) {
                    String line = sc.nextLine();

                    String[] arr = line.split(" / ", 3);
                    String taskType = arr[0];
                    String status = arr[1];
                    String restOfTask = arr[2];

                    switch (taskType) {
                        case "<T>": // if task is a to-do
                            Task taskTD = new Todo(restOfTask, "T");
                            if (status.equals("[\u2713]")) { // task is done
                                taskTD.markAsDone();
                            }

                            ls.add(taskTD);
                            break;
                        case "<D>": // if task is a deadline
                            String[] taskArrD = restOfTask.split(" / ");
                            String itemD = taskArrD[0];
                            String deadlineD = taskArrD[1];

                            LocalDate dateD = null;

                            try {
                                dateD = ui.convertToLocalDate(arr[1]);
                            } catch (IllegalArgumentException e) {
                                // intentionally empty
                            }

                            Task taskD = new Deadline(itemD, "D",  deadlineD, dateD);

                            if (status.equals("[\u2713]")) { // task is done
                                taskD.markAsDone();
                            }

                            ls.add(taskD);
                            break;
                        default: // if task is an event
                            String[] taskArrE = restOfTask.split(" / ");
                            String itemE = taskArrE[0];
                            String deadlineE = taskArrE[1];

                            LocalDate dateE = null;

                            try {
                                dateE = ui.convertToLocalDate(arr[1]);
                            } catch (IllegalArgumentException e) {
                                // intentionally empty
                            }

                            Task taskE = new Event(itemE, "E", deadlineE, dateE);

                            if (status.equals("[\u2713]")) {
                                taskE.markAsDone();
                            }

                            ls.add(taskE);
                            break;
                    }
                }
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }

        return ls;
    }

    /**
     * Adds tasks to hard disk file
     *
     * @param tasks a TaskList that contains a list of tasks
     * @throws IOException
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(file);
        this.addTasksToFile(writer, tasks.getls());
        writer.close();
    }

    // returns hard disk file
    public File getFile() {
        return this.file;
    }
}
