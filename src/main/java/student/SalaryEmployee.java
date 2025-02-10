package student;

/**
 * A class SalaryEmployee extends Employee.
 */
public class SalaryEmployee extends Employee {

    /** The pay for SalaryEmployee is the payRate divided by 24 weeks. */
    private double salaryWorkWeeks = 24;

    /**
     * Constructor for SalaryEmployee.
     * @param name  the employee's name
     * @param id    the employee's ID
     * @param payRate   the employee's pay rate
     * @param ytdEarnings   the employee's YTD earnings
     * @param ytdTaxesPaid  the employee's YTD Paid Taxes
     * @param pretaxDeductions  the employee's pretax deductions
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid, EmployeeType.SALARY);
    }

    /**
     * A protected calculateGrossPay method to calculate the gross pay for the pay period, as runPayroll is exactly
     * the same for both SalaryEmployee and HourlyEmployee, but implementation is different.
     * For salary employees, it is pay rate divided by 24 for two payments every month.
     * All numbers (across all methods) are rounded to the nearest cent. (2 decimal places)
     * @param hoursWorked   the hours worked for the pay period
     * @return  the gross pay for the current pay period
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        // grossPay = payRate / salaryWorkWeeks - pretaxDeductions
        return  (this.getPayRate() / salaryWorkWeeks);
    }

}
