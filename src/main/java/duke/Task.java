package duke;

/**
 * Stores information of tasks created by user
 */
public abstract class Task {
    protected String item;
    protected boolean isCompleted;
    protected String sign;

    public Task(String item, String sign) {
        this.item = item;
        this.isCompleted = false;
        this.sign = "<" + sign + ">";
    }

    /**
     * marks task as completed
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * returns task item
     *
     * @return item of task
     */
    public String getItem() {
        return this.item;
    }

    /**
     * returns status of task
     *
     * @return status of task in String
     */
    public String getStatus() {
        return this.isCompleted ? "[\u2713]" : "[\u2718]";
    }

    /**
     * returns sign of task
     *
     * @return sign of task in String
     */
    public String getSign() {
        return this.sign;
    }
}
