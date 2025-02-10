package student;

/**
 * A class to create PayStub.
 */
public class PayStub implements IPayStub {
    /** the employee's name. */
    private final String name;
    /** the employee's net pay. */
    private final double netPay;
    /** the employee's taxes. */
    private final double taxes;
    /** the employee's YTD earnings. */
    private final double ytdEarnings;
    /** the employee's YTD paid taxes. */
    private final double ytdTaxesPaid;

    /**
     * Constructor for PayStub.
     * @param employeeName  the employee's name
     * @param netPay    the pay for the current pay period
     * @param taxes     the taxes paid for the current pay period
     * @param ytdEarnings   the YTD earnings
     * @param ytdTaxesPaid  the YTD paid taxes
     */
    public PayStub(String employeeName, double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid) {
        this.name = employeeName;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Gets the pay for the current pay period.
     * @return the pay for the current pay period
     */
    public double getPay() {
        return this.netPay;
    }
    /**
     * Gets the taxes paid for the current pay period.
     * @return the taxes paid for the current pay period
     */
    public double getTaxesPaid() {
        return this.taxes;
    }

    /**
     * Gets the Ytd earnings.
     * @return the Ytd earnings
     */
    public double getYtdEarnings() {
        return this.ytdEarnings;
    }

    /**
     * Gets the Ytd paid taxes.
     * @return the Ytd paid taxes
     */
    public double getYtdTaxesPaid() {
        return this.ytdTaxesPaid;
    }

    /**
     * Converts the PayStub object to a CSV string.
     * Format of the CSV string is: "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"
     * @return the CSV string
     */
    public String toCSV() {
        return this.name + "," + String.format("%.2f", netPay) + "," + String.format("%.2f", taxes) + ","
                + String.format("%.2f", ytdEarnings) + "," + String.format("%.2f", ytdTaxesPaid);
    }
}
