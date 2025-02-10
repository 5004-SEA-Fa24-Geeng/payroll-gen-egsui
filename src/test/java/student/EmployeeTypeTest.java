package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTypeTest {
    @Test
    void testEnumValues() {
        // Verify the constants are defined correctly
        assertEquals(2, EmployeeType.values().length);
        assertEquals(EmployeeType.HOURLY, EmployeeType.valueOf("HOURLY"));
        assertEquals(EmployeeType.SALARY, EmployeeType.valueOf("SALARY"));
    }

    @Test
    void testEnumToString() {
        // Verify toString() behavior if overridden
        assertEquals("HOURLY", EmployeeType.HOURLY.toString());
        assertEquals("SALARY", EmployeeType.SALARY.toString());
    }
}