package student;

/**
 * A class SalaryEmployee extends Employee.
 */
public class SalaryEmployee extends Employee {

    /**
     * Constructor for SalaryEmployee.
     * @param name  the employee's name
     * @param ID    the employee's ID
     * @param payRate   the employee's pay rate
     * @param pretaxDeductions  the employee's pretax deductions
     * @param YTDEarnings   the employee's YTD earnings
     * @param YTDTaxesPaid  the employee's YTD Paid Taxes
     */
    public SalaryEmployee(String name, String ID, double payRate, double pretaxDeductions,
                          double YTDEarnings, double YTDTaxesPaid) {
        super(name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
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
        // pay = payRate / salaryWorkWeeks - pretaxDeductions
        double pay = decimalRoundUp((this.payRate / salaryWorkWeeks) - this.pretaxDeductions);
        double taxes = decimalRoundUp(pay * taxRate);
        double netPay = decimalRoundUp(pay - taxes);
        // Update the Employee's YTDEarnings
        this.setYTDEarnings(decimalRoundUp(this.ytdEarnings + netPay));
        // Update the Employee's YTDTaxesPaid
        this.setYTDTaxesPaid(decimalRoundUp(this.ytdTaxesPaid + taxes));

        return new PayStub(this.name, netPay, taxes, this.getYTDEarnings(), this.getYTDTaxesPaid());
    }

}
