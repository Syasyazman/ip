package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles information regarding Deadline tasks
 */
public class Deadline extends Task {
    protected String deadline;
    protected LocalDate date;

    public Deadline(String item, String sign, String deadline, LocalDate date) {
        super(item, sign);
        this.deadline = deadline;
        this.date = date;
    }

    /**
     * returns deadline of task
     *
     * @return deadline of task in String
     */
    public String getDeadline() {
        return date != null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.deadline;
    }
}
