import org.junit.jupiter.api.Test;
import src.main.java.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    // tests if Parser returns correct command type
    @Test
    public void byeCommandTypeTest() {
        Command actual = Parser.parse("bye");

        assertTrue(actual instanceof ExitCommand);
    }

    // tests if Ui converts to LocalDate accurately
    @Test
    public void convertLocalDateTest() {
        Ui ui = new Ui();

        LocalDate expected = LocalDate.parse("2019-02-01");
        LocalDate actual = ui.convertToLocalDate("2019-02-01");

        assertEquals(expected, actual);
    }

    // tests if TaskList deletes task successfully
    @Test
    public void deleteTest() {
        TaskList tasks = new TaskList(new ArrayList<>());

        tasks.addToList(new Todo("be happy", "T"));
        tasks.deleteTask(0);

        assertEquals(new ArrayList<>(), tasks.getls());
    }
}
