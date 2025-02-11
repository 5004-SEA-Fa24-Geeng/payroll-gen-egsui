package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An abstract class for implementing general methods for Employee.
 */
public abstract class Employee implements IEmployee {

    /** Employee's name. */
    private final String name;
    /** Employee's ID. */
    private final String id;
    /** Employee's pay rate. */
    private final double payRate;
    /** Employee's pretax deductions. */
    private final double pretaxDeductions;
    /** Employee's YTD earnings. */
    private double ytdEarnings;
    /** Employee's YTD paid taxes. */
    private double ytdTaxesPaid;
    /** Employee's type. */
    private final EmployeeType employeeType;
    /** taxRate are calculated as 1.45% for medicare, 6.2% for social security,
        and 15% for withholding. or 22.65% total. */
    private static double taxRate = 0.2265;

    /**
     * Constructor for Employee.
     * @param name  the employee's name
     * @param id    the employee's ID
     * @param payRate   pay rate for the employee
     * @param pretaxDeductions  pretax deductions for the employee
     * @param ytdEarnings   the employee's YTD earnings
     * @param ytdTaxesPaid  the employee's YTD paid taxes
     * @param employeeType  the employee's Type
     */
    public Employee(String name, String id, double payRate, double pretaxDeductions,
                       double ytdEarnings, double ytdTaxesPaid, EmployeeType employeeType) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.employeeType = employeeType;
    }

    /**
     * Gets the employee's name.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the ID of the employee
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return the pay rate of the employee
     */
    @Override
    public double getPayRate() {
        return this.payRate;
    }

    /**
     * Gets the employee's Type as a string.
     * Either "HOURLY" or "SALARY" depending on the type of employee.
     *
     * @return the type of the employee as a string
     */
    @Override
    public String getEmployeeType() {
        return this.employeeType.toString();
    }

    /**
     * Gets the YTD earnings of the employee.
     *
     * @return the YTD earnings of the employee
     */
    @Override
    public double getYTDEarnings() {
        return this.ytdEarnings;
    }

    /**
     * Gets the YTD taxes paid by the employee.
     *
     * @return the YTD taxes paid by the employee
     */
    @Override
    public double getYTDTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    /**
     * Gets pretax deductions for the employee. Yes, on a normal paycheck this varies as either set
     * amounts or percents, and often more than one type of deduction.
     * For now, you can just assume a single pretax deduction as a whole dollar amount.
     *
     * @return the pretax deductions for the employee
     */
    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    /**
     * Converts the employee to a CSV string.
     * Format of the String s as follows:
     * employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     * employee_type has the options for HOURLY or SALARY.
     * You do not have to worry about commas in the name or any other field.
     *
     * @return the employee as a CSV string
     */
    @Override
    public String toCSV() {
        return this.getEmployeeType() + "," + this.getName() + "," + this.getID() + ","
                + this.getPayRate() + "," + this.getPretaxDeductions() + ","
                + this.getYTDEarnings() + "," + this.getYTDTaxesPaid();
    }

    /**
     * Calculates the gross pay of the employee.
     * @param hoursWorked   the hours worked for the pay period
     * @return  the pay stub for the current pay period
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Runs the employee's payroll.
     * This will calculate the pay for the current pay, update the YTD earnings, and update the
     * taxes paid YTD. They are calculated on the net pay (after pretax deductions).
     * If employee has < 0 hours, they are skipped this payroll period.
     * @param hoursWorked the hours worked for the pay period
     *
     * @return the pay stub for the current pay period
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) throws IllegalArgumentException {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Worked hours must be greater than 0");
        } else {
            double netPay;
            double taxes;
            double grossPay;
            double finalNetPay;

            grossPay = calculateGrossPay(hoursWorked);
            // Final net pay is calculated as pay - pretaxDeductions - taxes
            netPay = grossPay - this.getPretaxDeductions();
            taxes = decimalRoundUp(netPay * taxRate);
            finalNetPay = decimalRoundUp(netPay - taxes);

            // Update the Employee's YTDEarnings
            this.setYTDEarnings(decimalRoundUp(this.getYTDEarnings() + finalNetPay));
            // Update the Employee's YTDTaxesPaid
            this.setYTDTaxesPaid(decimalRoundUp(this.getYTDTaxesPaid() + taxes));

            return new PayStub(this.getName(), finalNetPay, taxes, this.getYTDEarnings(), this.getYTDTaxesPaid());
        }
    }

    /**
     * A method to round up the double value by BigDecimal.
     * @param val  the double value needed to be set scale
     * @return  the rounded double value
     */
    public double decimalRoundUp(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Set the employee's YTD earnings.
     * @param earnings  the employee's YTD earnings
     */
    public void setYTDEarnings(double earnings) {
        this.ytdEarnings = earnings;
    }

    /**
     * Set the employee's YTD paid taxes.
     * @param ytdTaxesPaid  the employee's YTD paid taxes
     */
    public void setYTDTaxesPaid(double ytdTaxesPaid) {
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

}
