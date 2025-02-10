package student;

/** 
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {
    
    private Builder() {
    }


     /**
     * Builds an employee object from a CSV string.
     * 
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     * 
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        String[] parts = csv.split(",");
        double maxDataLength = 7;

        if (parts.length != maxDataLength) {
            // Provides more or less information
            throw new IllegalArgumentException("Invalid data length.");
        }
        EmployeeType employeeType = EmployeeType.valueOf(parts[0]);
        double payRate;
        double pretaxDeductions;
        double ytdEarnings;
        double ytdTaxesPaid;
        try {
            payRate = Double.parseDouble(parts[3]);
            pretaxDeductions = Double.parseDouble(parts[4]);
            ytdEarnings = Double.parseDouble(parts[5]);
            ytdTaxesPaid = Double.parseDouble(parts[6]);
            // Check double numbers cannot be negative
            if (payRate < 0 || pretaxDeductions < 0 || ytdEarnings < 0 || ytdTaxesPaid < 0) {
                throw new IllegalArgumentException("Negative value found in numeric fields.");
            }
        } catch (NumberFormatException e) {
            // catch around converting strings to doubles
            throw new NumberFormatException(e.getMessage());
        }
        // Build the appropriate employee object
        switch (employeeType) {
            case HOURLY:
                return new HourlyEmployee(parts[1], parts[2], payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            case SALARY:
                return new SalaryEmployee(parts[1], parts[2], payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            default:
                throw new IllegalArgumentException("Unknown employee type " + employeeType);
        }
    }


   /**
     * Converts a TimeCard from a CSV String.
     * 
     * @param csv csv string
     * @return a TimeCard object
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        String[] parts = csv.split(",");
        double hours;
        double maxDataLength = 2;

        if (parts.length != maxDataLength) {
            // Provides more or less information
            throw new IllegalArgumentException("Invalid data length.");
        }
        try {
            hours = Double.parseDouble(parts[1]);
            return new TimeCard(parts[0], hours);
        } catch (NumberFormatException e) {
            // catch around converting strings to doubles
            throw new NumberFormatException(e.getMessage());
        }
    }
}
