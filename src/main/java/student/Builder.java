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
        double payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid;
        try {
            payRate = Double.parseDouble(parts[3]);
            pretaxDeductions = Double.parseDouble(parts[4]);
            YTDEarnings = Double.parseDouble(parts[5]);
            YTDTaxesPaid = Double.parseDouble(parts[6]);
            // Check double numbers cannot be negative
            if (payRate < 0 || pretaxDeductions < 0 || YTDEarnings < 0 || YTDTaxesPaid < 0) {
                throw new IllegalArgumentException("Negative value found in numeric fields.");
            }
        } catch(NumberFormatException e) {
            // catch around converting strings to doubles
            throw new NumberFormatException(e.getMessage());
        }
        // Build the appropriate employee object
        switch (EmployeeType.valueOf(parts[0])) {
            case HOURLY:
                return new HourlyEmployee(parts[1], parts[2], payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
            case SALARY:
                return new SalaryEmployee(parts[1], parts[2], payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
            default:
                throw new IllegalArgumentException("Unknown employee type " + parts[0]);
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
            if (hours > 0) {
                return new TimeCard(parts[0], hours);
            } else {
                throw new IllegalArgumentException("worked hours should be greater than zero.");
            }
        } catch (NumberFormatException e) {
            // catch around converting strings to doubles
            throw new NumberFormatException(e.getMessage());
        }
    }
}
