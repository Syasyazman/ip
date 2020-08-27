package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String time;
    protected LocalDate date;

    public Event(String item, String sign, String time, LocalDate date) {
        super(item, sign);
        this.time = time;
        this.date = date;
    }

    // returns deadline of task
    public String getTime() {
        return this.date != null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.time;
    }
}