package student;

/**
 * A class HourlyEmployee extends Employee
 */
public class HourlyEmployee extends Employee {

    /**
     * Constructor for HourlyEmployee
     * @param name  the employee's name
     * @param ID    the employee's ID
     * @param payRate   the employee's pay rate
     * @param pretaxDeductions  the employee's pretax deductions
     * @param YTDEarnings   the employee's YTD earnings
     * @param YTDTaxesPaid  the employee's YTD Paid Taxes
     */
    public HourlyEmployee(String name, String ID, double payRate,double pretaxDeductions,
                          double YTDEarnings, double YTDTaxesPaid) {
        super(name, ID, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
        // set employeeType for our new HourlyEmployee
        this.setEmployeeType(EmployeeType.HOURLY.name());
    }

    /**
     * A protected calculateGrossPay method to calculate the gross pay for the pay period, as runPayroll is exactly
     * the same for both SalaryEmployee and HourlyEmployee, but implementation is different.
     * For hourly employees, the pay is calculated as payRate * hoursWorked for the first 40 hours,
     * then payRate * 1.5 * (hoursWorked - 40) for overtime.
     * All numbers (across all methods) are rounded to the nearest cent. (2 decimal places)
     * @param hoursWorked   the hours worked for the pay period
     * @return  the pay stub for the current pay period
     */
    @Override
    protected IPayStub calculateGrossPay(double hoursWorked) {
        double pay, netPay, taxes;
        // Employee who works not over max working hours, pay = workedHours * payRate - pretaxDeductions
        if (hoursWorked <= maxWorkHours) {
            pay = decimalRoundUp(hoursWorked * getPayRate() - getPretaxDeductions());
        // Employee who works over max working hours, the overtime work hours should apply new pay rate
        // pay = maxWorkHours * payRate - pretaxDeductions + (workedHours - maxWorkHours) * overWorkPayRate
        } else {
            pay = decimalRoundUp(maxWorkHours * getPayRate()) +
                    decimalRoundUp((hoursWorked - maxWorkHours) * getPayRate() * overWorkPayRate - getPretaxDeductions());
        }
        taxes = decimalRoundUp(pay * taxRate);
        // Final net pay is calculated as pay - pretaxDeductions - taxes
        netPay = decimalRoundUp(pay - taxes);
        // Update the Employee's YTDEarnings
        this.setYTDEarnings(decimalRoundUp(this.YTDEarnings + netPay));
        // Update the Employee's YTDTaxesPaid
        this.setYTDTaxesPaid(decimalRoundUp(this.YTDTaxesPaid + taxes));

        return new PayStub(this.name, netPay, taxes, this.getYTDEarnings(), this.getYTDTaxesPaid());
    }
}
