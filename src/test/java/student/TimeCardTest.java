package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {
    ITimeCard timeCard1;
    ITimeCard timeCard2;

    @BeforeEach
    void setUp() {
        timeCard1 = new TimeCard("ID2025", 50);
        timeCard2 = new TimeCard("ID202502", 10);
    }

    @Test
    void getEmployeeID() {
        assertEquals("ID2025", timeCard1.getEmployeeID());
        assertEquals("ID202502", timeCard2.getEmployeeID());
    }

    @Test
    void getHoursWorked() {
        assertEquals(50, timeCard1.getHoursWorked());
        assertEquals(10, timeCard2.getHoursWorked());
    }
}