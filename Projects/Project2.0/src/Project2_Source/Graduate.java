/**
 * Graduate.java
 * Rodney Dennise
 * Programming Project 2
 * Date: 07/06/2026
 *
 * Represents a graduate student, which is a Student with an additional
 * field indicating the degree being sought (Masters or Doctorate). A
 * graduate student is eligible for honor society membership only if
 * they meet the base GPA requirement AND are pursuing a Masters degree.
 */
package Project2_Source;

public class Graduate extends Student {

    // Additional instance variable specific to graduate students
    private String degreeSought;

    /**
     * Constructs a Graduate with the given name, credit hours, quality
     * points, and degree sought.
     *
     * @param name          the student's name
     * @param creditHours   total credit hours earned
     * @param qualityPoints total quality points earned
     * @param degreeSought  the degree being pursued ("Masters" or "Doctorate")
     */
    public Graduate(String name, double creditHours, double qualityPoints, String degreeSought) {
        super(name, creditHours, qualityPoints);
        this.degreeSought = degreeSought;
    }

    /**
     * Returns the degree the student is pursuing.
     *
     * @return the degree sought
     */
    public String getDegreeSought() {
        return degreeSought;
    }

    /**
     * Determines honor society eligibility for a graduate student. In
     * addition to the base GPA requirement (checked via the superclass
     * method), the student must be seeking a Masters degree.
     *
     * @return true if eligible, false otherwise
     */
    @Override
    public boolean eligibleForHonorSociety() {
        boolean isSeekingMasters = degreeSought.equalsIgnoreCase("Masters");
        return super.eligibleForHonorSociety() && isSeekingMasters;
    }

    /**
     * Returns a string containing the student's name, GPA, and degree
     * sought, building on the superclass's toString to avoid duplicating
     * the name/GPA formatting logic.
     *
     * @return a string representation of the graduate student
     */
    @Override
    public String toString() {
        return super.toString() + " " + degreeSought.toUpperCase();
    }
}
