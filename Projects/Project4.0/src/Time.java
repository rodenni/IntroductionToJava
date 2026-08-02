/**
 * Time.java
 * CMSC 215 - Programming Project 4
 * Author: Rodney
 * Date: August 2, 2026
 *
 * An immutable class representing a 12-hour clock time made up of hours,
 * minutes, and a meridian (AM or PM). Implements Comparable so two Time
 * objects can be ordered chronologically.
 */
public class Time implements Comparable<Time> {

    // Instance variables; final because the class is immutable
    private final int hours;
    private final int minutes;
    private final String meridian;

    /**
     * Constructs a Time object from separate hour, minute, and meridian values.
     *
     * @param hours    the hour, must be between 1 and 12
     * @param minutes  the minute, must be between 0 and 59
     * @param meridian must be "AM" or "PM" (case-insensitive)
     * @throws InvalidTime if any value is out of range or invalid
     */
    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        validate(hours, minutes, meridian);
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian.toUpperCase();
    }

    /**
     * Constructs a Time object by parsing a string in the format "HH:MM AM"
     * (or "HH:MM PM").
     *
     * @param timeString the string representation of the time
     * @throws InvalidTime if the string is malformed or contains invalid values
     */
    public Time(String timeString) throws InvalidTime {
        if (timeString == null || timeString.trim().isEmpty()) {
            throw new InvalidTime("Time string cannot be empty");
        }

        // Expecting exactly two tokens: "HH:MM" and "AM"/"PM"
        String[] parts = timeString.trim().split("\\s+");
        if (parts.length != 2) {
            throw new InvalidTime("Time string must be in the format HH:MM AM");
        }

        String[] hourMinute = parts[0].split(":");
        if (hourMinute.length != 2) {
            throw new InvalidTime("Time string must be in the format HH:MM AM");
        }

        // Check that hours and minutes are numeric before parsing
        int parsedHours;
        int parsedMinutes;
        try {
            parsedHours = Integer.parseInt(hourMinute[0].trim());
            parsedMinutes = Integer.parseInt(hourMinute[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidTime("Hours and minutes must be numeric values");
        }

        validate(parsedHours, parsedMinutes, parts[1]);

        this.hours = parsedHours;
        this.minutes = parsedMinutes;
        this.meridian = parts[1].toUpperCase();
    }

    /**
     * Validates hour, minute, and meridian values, throwing InvalidTime
     * with a descriptive reason if any check fails.
     *
     * @param hours    the hour to validate
     * @param minutes  the minute to validate
     * @param meridian the meridian to validate
     * @throws InvalidTime if any value is invalid
     */
    private void validate(int hours, int minutes, String meridian) throws InvalidTime {
        if (hours < 1 || hours > 12) {
            throw new InvalidTime("Hours must be between 1 and 12");
        }
        if (minutes < 0 || minutes > 59) {
            throw new InvalidTime("Minutes must be between 0 and 59");
        }
        if (meridian == null || !(meridian.equalsIgnoreCase("AM") || meridian.equalsIgnoreCase("PM"))) {
            throw new InvalidTime("Meridian must be AM or PM");
        }
    }

    /**
     * Converts this time to the number of minutes since midnight, used
     * internally to make chronological comparisons straightforward.
     *
     * @return minutes since midnight (0-1439)
     */
    private int toMinutesSinceMidnight() {
        int hour24 = hours % 12; // 12 AM -> 0, 12 PM -> 0 (before adding 12)
        if (meridian.equals("PM")) {
            hour24 += 12;
        }
        return hour24 * 60 + minutes;
    }

    /**
     * Compares this time to another chronologically.
     *
     * @param other the Time to compare against
     * @return a negative number, zero, or a positive number if this time is
     *         earlier than, equal to, or later than the other time
     */
    @Override
    public int compareTo(Time other) {
        return Integer.compare(this.toMinutesSinceMidnight(), other.toMinutesSinceMidnight());
    }

    /**
     * Returns the string representation of this time in the format "HH:MM AM".
     *
     * @return the formatted time string
     */
    @Override
    public String toString() {
        return String.format("%d:%02d %s", hours, minutes, meridian);
    }
}
