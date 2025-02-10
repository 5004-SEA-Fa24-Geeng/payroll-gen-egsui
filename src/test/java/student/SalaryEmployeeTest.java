package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {
    SalaryEmployee salaryEmployee1;
    SalaryEmployee salaryEmployee2;

    @BeforeEach
    void setUp() {
        salaryEmployee1 = new SalaryEmployee("TestNameOne", "ID123456",
                60000,12000,4000, 300);
        salaryEmployee2 = new SalaryEmployee("TestNameTwo", "ID000",
                200000,5000,3000, 400);
    }

    @Test
    void getName() {
        assertEquals("TestNameOne", salaryEmployee1.getName());
        assertEquals("TestNameTwo", salaryEmployee2.getName());
    }

    @Test
    void getID() {
        assertEquals("ID123456", salaryEmployee1.getID());
        assertEquals("ID000", salaryEmployee2.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(60000, salaryEmployee1.getPayRate());
        assertEquals(200000, salaryEmployee2.getPayRate());
    }

    @Test
    void getEmployeeType() {
        assertEquals("SALARY", salaryEmployee1.getEmployeeType());
        assertEquals("SALARY", salaryEmployee2.getEmployeeType());
    }

    @Test
    void setEmployeeType() {
        assertThrows(IllegalArgumentException.class, () -> salaryEmployee1.setEmployeeType("TEST"));
        assertThrows(IllegalArgumentException.class, () -> salaryEmployee2.setEmployeeType("INVALID"));
    }

    @Test
    void getYTDEarnings() {
        assertEquals(12000, salaryEmployee1.getYTDEarnings());
        assertEquals(5000, salaryEmployee2.getYTDEarnings());
    }

    @Test
    void setYTDEarnings() {
        salaryEmployee1.setYTDEarnings(15000);
        assertEquals(15000, salaryEmployee1.getYTDEarnings());
        salaryEmployee2.setYTDEarnings(6000);
        assertEquals(6000, salaryEmployee2.getYTDEarnings());

    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4000, salaryEmployee1.getYTDTaxesPaid());
        assertEquals(3000, salaryEmployee2.getYTDTaxesPaid());
    }

    @Test
    void setYTDTaxesPaid() {
        salaryEmployee1.setYTDTaxesPaid(6000);
        assertEquals(6000, salaryEmployee1.getYTDTaxesPaid());
        salaryEmployee2.setYTDTaxesPaid(4000);
        assertEquals(4000, salaryEmployee2.getYTDTaxesPaid());
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(300, salaryEmployee1.getPretaxDeductions());
        assertEquals(400, salaryEmployee2.getPretaxDeductions());
    }

    @Test
    void toCSV() {
        assertEquals("SALARY,TestNameOne,ID123456,60000.0,300.0,12000.0,4000.0", salaryEmployee1.toCSV());
        assertEquals("SALARY,TestNameTwo,ID000,200000.0,400.0,5000.0,3000.0", salaryEmployee2.toCSV());
    }

    @Test
    void runPayroll() {
        IPayStub payStub1 = salaryEmployee1.runPayroll(30);
        assertEquals(1701.7, payStub1.getPay());
        assertEquals(498.3, payStub1.getTaxesPaid());
        assertEquals(13701.7, payStub1.getYtdEarnings());
        assertEquals(4498.3, payStub1.getYtdTaxesPaid());
        assertEquals("TestNameOne,1701.70,498.30,13701.70,4498.30", payStub1.toCSV());
        IPayStub payStub2 = salaryEmployee2.runPayroll(30);
        assertEquals(6136.43, payStub2.getPay());
        assertEquals(1796.9, payStub2.getTaxesPaid());
        assertEquals(11136.43, payStub2.getYtdEarnings());
        assertEquals(4796.9, payStub2.getYtdTaxesPaid());
        assertEquals("TestNameTwo,6136.43,1796.90,11136.43,4796.90", payStub2.toCSV());
    }
}