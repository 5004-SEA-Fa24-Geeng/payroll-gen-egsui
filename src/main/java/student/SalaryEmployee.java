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
     * @param pretaxDeductions  the employee's pretax deductions
     * @param ytdEarnings   the employee's YTD earnings
     * @param ytdTaxesPaid  the employee's YTD Paid Taxes
     */
    public SalaryEmployee(String name, String id, double payRate, double pretaxDeductions,
                          double ytdEarnings, double ytdTaxesPaid) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
        // set employeeType for our new SalaryEmployee
        this.setEmployeeType(EmployeeType.SALARY.name());
    }

    /**
     * A protected calculateGrossPay method to calculate the gross pay for the pay period, as runPayroll is exactly
     * the same for both SalaryEmployee and HourlyEmployee, but implementation is different.
     * For salary employees, it is pay rate divided by 24 for two payments every month.
     * All numbers (across all methods) are rounded to the nearest cent. (2 decimal places)
     * @param hoursWorked   the hours worked for the pay period
     * @return  the pay stub for the current pay period
     */
    @Override
    protected IPayStub calculateGrossPay(double hoursWorked) {
        // taxRate are calculated as 1.45% for medicare, 6.2% for social security,
        // and 15% for withholding. or 22.65% total.*/
        double taxRate = 0.2265;
        // pay = payRate / salaryWorkWeeks - pretaxDeductions
        double pay = decimalRoundUp((this.getPayRate() / salaryWorkWeeks) - this.getPretaxDeductions());
        double taxes = decimalRoundUp(pay * taxRate);
        double netPay = decimalRoundUp(pay - taxes);
        // Update the Employee's YTDEarnings
        this.setYTDEarnings(decimalRoundUp(this.getYTDEarnings() + netPay));
        // Update the Employee's YTDTaxesPaid
        this.setYTDTaxesPaid(decimalRoundUp(this.getYTDTaxesPaid() + taxes));

        return new PayStub(this.getName(), netPay, taxes, this.getYTDEarnings(), this.getYTDTaxesPaid());
    }

}
