package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HourlyEmployeeTest {
    HourlyEmployee hourlyEmployee1;
    HourlyEmployee hourlyEmployee2;

    @BeforeEach
    void setUp() {
        hourlyEmployee1 = new HourlyEmployee("TestNameOne", "ID123456",
                60.34, 300,12000,4000);
        hourlyEmployee2 = new HourlyEmployee("TestNameTwo", "ID000",
                20, 400,5000,3000);
    }

    @Test
    void getName() {
        assertEquals("TestNameOne", hourlyEmployee1.getName());
        assertEquals("TestNameTwo", hourlyEmployee2.getName());
    }

    @Test
    void getID() {
        assertEquals("ID123456", hourlyEmployee1.getID());
        assertEquals("ID000", hourlyEmployee2.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(60.34, hourlyEmployee1.getPayRate());
        assertEquals(20.0, hourlyEmployee2.getPayRate());
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", hourlyEmployee1.getEmployeeType());
        assertEquals("HOURLY", hourlyEmployee2.getEmployeeType());
    }

    @Test
    void setEmployeeType() {
        assertThrows(IllegalArgumentException.class, () -> hourlyEmployee1.setEmployeeType("TEST"));
        assertThrows(IllegalArgumentException.class, () -> hourlyEmployee2.setEmployeeType("INVALID"));
    }

    @Test
    void getYTDEarnings() {
        assertEquals(12000, hourlyEmployee1.getYTDEarnings());
        assertEquals(5000, hourlyEmployee2.getYTDEarnings());
    }

    @Test
    void setYTDEarnings() {
        hourlyEmployee1.setYTDEarnings(15000);
        assertEquals(15000, hourlyEmployee1.getYTDEarnings());
        hourlyEmployee2.setYTDEarnings(6000);
        assertEquals(6000, hourlyEmployee2.getYTDEarnings());

    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4000, hourlyEmployee1.getYTDTaxesPaid());
        assertEquals(3000, hourlyEmployee2.getYTDTaxesPaid());
    }

    @Test
    void setYTDTaxesPaid() {
        hourlyEmployee1.setYTDTaxesPaid(6000);
        assertEquals(6000, hourlyEmployee1.getYTDTaxesPaid());
        hourlyEmployee2.setYTDTaxesPaid(4000);
        assertEquals(4000, hourlyEmployee2.getYTDTaxesPaid());
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(300, hourlyEmployee1.getPretaxDeductions());
        assertEquals(400, hourlyEmployee2.getPretaxDeductions());
    }

    @Test
    void toCSV() {
        assertEquals("HOURLY,TestNameOne,ID123456,60.34,300.0,12000.0,4000.0", hourlyEmployee1.toCSV());
        assertEquals("HOURLY,TestNameTwo,ID000,20.0,400.0,5000.0,3000.0", hourlyEmployee2.toCSV());
    }

    @Test
    void runPayroll() {
        IPayStub payStub1 = hourlyEmployee1.runPayroll(30);
        assertEquals(1168.14, payStub1.getPay());
        assertEquals(342.06, payStub1.getTaxesPaid());
        assertEquals(13168.14, payStub1.getYtdEarnings());
        assertEquals(4342.06, payStub1.getYtdTaxesPaid());
        assertEquals("TestNameOne,1168.14,342.06,13168.14,4342.06", payStub1.toCSV());
        IPayStub payStub2 = hourlyEmployee2.runPayroll(30);
        assertEquals(154.7, payStub2.getPay());
        assertEquals(45.3, payStub2.getTaxesPaid());
        assertEquals(5154.7, payStub2.getYtdEarnings());
        assertEquals(3045.3, payStub2.getYtdTaxesPaid());
        assertEquals("TestNameTwo,154.70,45.30,5154.70,3045.30", payStub2.toCSV());
    }

    @Test
    void decimalRoundUp() {
        assertEquals(12.55, hourlyEmployee1.decimalRoundUp(12.546));
        assertEquals(12.54, hourlyEmployee2.decimalRoundUp(12.541));
    }
}