package src.main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String deadline;
    protected LocalDate date;

    public Deadline(String item, String sign, String deadline, LocalDate date) {
        super(item, sign);
        this.deadline = deadline;
        this.date = date;
    }

    // get deadline of task
    public String getDeadline() {
        return date != null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.deadline;
    }
}
