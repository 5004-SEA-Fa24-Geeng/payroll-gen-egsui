package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An abstract class for implementing general methods for Employee.
 */
public abstract class Employee implements IEmployee {

    /** taxRate are calculated as 1.45% for medicare, 6.2% for social security,
     *  and 15% for withholding. or 22.65% total.*/
    protected static double taxRate = 0.2265;
    /** Maximum work hours are 40 */
    protected static double maxWorkHours = 40;
    /** overworked hours should be paid by PayRate * 1.5 */
    protected static double overWorkPayRate = 1.50;
    /** The pay for SalaryEmployee is the payRate divided by 24 weeks. */
    protected static double salaryWorkWeeks = 24;
    /** Employee's type */
    protected String employeeType;
    /** Employee's name */
    protected String name;
    /** Employee's ID */
    protected String id;
    /** Employee's pay rate */
    protected double payRate;
    /** Employee's pretax deductions */
    protected double pretaxDeductions;
    /** Employee's YTD earnings */
    protected double ytdEarnings;
    /** Employee's YTD paid taxes */
    protected double ytdTaxesPaid;

    /**
     * Constructor for Employee
     * @param name  the employee's name
     * @param id    the employee's ID
     * @param payRate   pay rate for the employee
     * @param pretaxDeductions  pretax deductions for the employee
     * @param YTDEarnings   the employee's YTD earnings
     * @param YTDTaxesPaid  the employee's YTD paid taxes
     */
    public Employee(String name, String id, double payRate, double pretaxDeductions,
                       double YTDEarnings, double YTDTaxesPaid) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.ytdEarnings = YTDEarnings;
        this.ytdTaxesPaid = YTDTaxesPaid;
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
        return this.employeeType;
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
        return this.employeeType + "," + this.name + "," + this.id + ","
                + this.payRate + "," + this.pretaxDeductions + ","
                + this.ytdEarnings + "," + this.ytdTaxesPaid;
    }

    /**
     * Calculates the gross pay of the employee.
     * @param hoursWorked   the hours worked for the pay period
     * @return  the pay stub for the current pay period
     */
    protected abstract IPayStub calculateGrossPay(double hoursWorked);

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
        if (hoursWorked <= 0) {
            throw new IllegalArgumentException("Worked hours must be greater than 0");
        } else {
            return calculateGrossPay(hoursWorked);
        }
    }

    /**
     * A method to round up the double value by BigDecimal.
     * @param val  the double value needed to be set scale
     * @return  the rounded double value
     */
    public double decimalRoundUp(double val) {
        int newScale = 2;
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Set the employee's type.
     * @param employeeType  the employee's type
     */
    protected void setEmployeeType(String employeeType) {
        switch (EmployeeType.valueOf(employeeType)) {
            case HOURLY, SALARY:
                this.employeeType = employeeType;
                break;
            default:
                throw new IllegalArgumentException("Unknown employee type " + employeeType);
        }
    }

    /**
     * Set the employee's YTD earnings.
     * @param YTDEarnings  the employee's YTD earnings
     */
    protected void setYTDEarnings(double YTDEarnings) {
        this.ytdEarnings = YTDEarnings;
    }

    /**
     * Set the employee's YTD paid taxes.
     * @param YTDTaxesPaid  the employee's YTD paid taxes
     */
    protected void setYTDTaxesPaid(double YTDTaxesPaid) {
        this.ytdTaxesPaid = YTDTaxesPaid;
    }

}
