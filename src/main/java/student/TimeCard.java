package student;

/**
 * A class to create TimeCard
 */
public class TimeCard implements ITimeCard{
    protected String ID;
    protected double hoursWorked;

    /**
     * Constructor for TimeCard
     * @param ID    the employee's ID
     * @param hoursWorked   the employee's worked hours
     */
    public TimeCard(String ID, double hoursWorked) {
        this.ID = ID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    public String getEmployeeID() {
        return this.ID;
    }

    /**
     * Gets the hours worked by the employee.
     *
     * @return the hours worked by the employee
     */
    public double getHoursWorked() {
        return this.hoursWorked;
    }
}
