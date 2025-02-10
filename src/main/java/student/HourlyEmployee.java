package student;

/**
 * A class HourlyEmployee extends Employee.
 */
public class HourlyEmployee extends Employee {

    /** taxRate are calculated as 1.45% for medicare, 6.2% for social security,
        and 15% for withholding. or 22.65% total.*/
    private double taxRate = 0.2265;
    /** Maximum work hours are 40. */
    private double maxWorkHours = 40;
    /** overworked hours should be paid by PayRate * 1.5. */
    private double overWorkPayRate = 1.50;

    /**
     * Constructor for HourlyEmployee.
     * @param name  the employee's name
     * @param id    the employee's ID
     * @param payRate   the employee's pay rate
     * @param ytdEarnings   the employee's YTD earnings
     * @param ytdTaxesPaid  the employee's YTD Paid Taxes
     * @param pretaxDeductions  the employee's pretax deductions
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid, EmployeeType.HOURLY);
    }

    /**
     * A protected calculateGrossPay method to calculate the gross pay for the pay period, as runPayroll is exactly
     * the same for both SalaryEmployee and HourlyEmployee, but implementation is different.
     * For hourly employees, the pay is calculated as payRate * hoursWorked for the first 40 hours,
     * then payRate * 1.5 * (hoursWorked - 40) for overtime.
     * All numbers (across all methods) are rounded to the nearest cent. (2 decimal places)
     * @param hoursWorked   the hours worked for the pay period
     * @return  the gross pay for the current pay period
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        double grossPay;

        // Employee who works not over max working hours, pay = workedHours * payRate - pretaxDeductions
        if (hoursWorked <= maxWorkHours) {
            grossPay = hoursWorked * getPayRate() - getPretaxDeductions();
        } else {
            // Employee who works over max working hours, the overtime work hours should apply new pay rate
            // grossPay = maxWorkHours * payRate - pretaxDeductions + (workedHours - maxWorkHours) * overWorkPayRate
            grossPay = maxWorkHours * getPayRate() + (hoursWorked - maxWorkHours) * getPayRate() * overWorkPayRate
                    - getPretaxDeductions();
        }
        return grossPay;
    }
}
