package src.main.java;

import java.util.List;

public class TaskList {
    protected List<Task> ls;
    protected int index;

    public TaskList(List<Task> ls) {
        this.ls = ls;
        this.index = ls.size();
    }

    public int getIndex() {
        return this.index;
    }

    public List<Task> getls() {
        return this.ls;
    }

    // adds task to list
    public void addToList(Task task) {
        this.ls.add(task);
        this.index++; // increment index
    }

    // mark task as done
    public void markDone(int index) {
        this.ls.get(index).markAsDone();
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
}
