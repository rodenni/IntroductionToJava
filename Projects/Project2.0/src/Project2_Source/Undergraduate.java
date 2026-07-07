/**
 * Undergraduate.java
 * Programming Project 2
 * Date: 07/06/2026
 *
 * Represents an undergraduate student, which is a Student with an
 * additional class rank (Freshman, Sophomore, Junior, or Senior).
 * An undergraduate is eligible for honor society membership only if
 * they meet the base GPA requirement AND are classified as a Junior
 * or Senior.
 */
package Project2_Source;

public class Undergraduate extends Student {

    // Additional instance variable specific to undergraduates
    private String year;

    /**
     * Constructs an Undergraduate with the given name, credit hours,
     * quality points, and class rank.
     *
     * @param name          the student's name
     * @param creditHours   total credit hours earned
     * @param qualityPoints total quality points earned
     * @param year          the student's class rank (e.g. "Junior", "Senior")
     */
    public Undergraduate(String name, double creditHours, double qualityPoints, String year) {
        super(name, creditHours, qualityPoints);
        this.year = year;
    }

    /**
     * Returns the student's class rank.
     *
     * @return the class rank
     */
    public String getYear() {
        return year;
    }

    /**
     * Determines honor society eligibility for an undergraduate student.
     * In addition to the base GPA requirement (checked via the
     * superclass method), the student must be a Junior or Senior.
     *
     * @return true if eligible, false otherwise
     */
    @Override
    public boolean eligibleForHonorSociety() {
        boolean isJuniorOrSenior = year.equalsIgnoreCase("Junior") || year.equalsIgnoreCase("Senior");
        return super.eligibleForHonorSociety() && isJuniorOrSenior;
    }

    /**
     * Returns a string containing the student's name, GPA, and year,
     * building on the superclass's toString to avoid duplicating the
     * name/GPA formatting logic.
     *
     * @return a string representation of the undergraduate student
     */
    @Override
    public String toString() {
        return super.toString() + " " + year.toUpperCase();
    }
}
