package duke;

public abstract class Task {
    protected String item;
    protected boolean isCompleted;
    protected String sign;

    public Task(String item, String sign) {
        this.item = item;
        this.isCompleted = false;
        this.sign = "<" + sign + ">";
    }

    // marks task as completed
    public void markAsDone() {
        this.isCompleted = true;
    }

    // returns task item
    public String getItem() {
        return this.item;
    }

    // returns status of task
    public String getStatus() {
        return this.isCompleted ? "[\u2713]" : "[\u2718]";
    }

    // returns sign of task
    public String getSign() {
        return this.sign;
    }
}
