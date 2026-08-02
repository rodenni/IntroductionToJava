/**
 * InvalidTime.java
 * CMSC 215 - Programming Project 4
 * Author: Rodney
 * Date: August 2, 2026
 *
 * A checked exception thrown when a Time object is constructed with
 * invalid hour, minute, or meridian values.
 */
public class InvalidTime extends Exception {

    // Holds the reason the time was considered invalid
    private String message;

    /**
     * Constructs an InvalidTime exception with a descriptive message.
     *
     * @param message the reason the time is invalid
     */
    public InvalidTime(String message) {
        this.message = message;
    }

    /**
     * Returns the message describing why the time was invalid.
     *
     * @return the exception message
     */
    public String getMessage() {
        return message;
    }
}