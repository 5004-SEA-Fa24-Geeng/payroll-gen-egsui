package student;

/**
 * A class to create TimeCard.
 */
public class TimeCard implements ITimeCard {
    /** The employee's ID. */
    private final String id;
    /** The worked hours. */
    private final double hoursWorked;

    /**
     * Constructor for TimeCard.
     * @param id    the employee's ID
     * @param hoursWorked   the employee's worked hours
     */
    public TimeCard(String id, double hoursWorked) {
        this.id = id;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    public String getEmployeeID() {
        return this.id;
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
