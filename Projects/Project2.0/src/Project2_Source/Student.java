/**
 * Student.java
 * Rodney Dennise
 * Programming Project 2
 * Date: 07/06/2026
 *
 * Represents a general college student with a name, credit hours earned,
 * and quality points. Provides GPA calculation and honor society
 * eligibility logic shared by all students. Subclasses (Undergraduate,
 * Graduate) extend this class to add additional eligibility requirements.
 */
package Project2_Source;

public class Student {

    // Instance variables (private for encapsulation)
    private String name;
    private double creditHours;
    private double qualityPoints;

    // Static field shared by all Student objects: the minimum GPA
    // required to be eligible for honor society membership.
    private static double gpaThreshold;

    /**
     * Constructs a Student with the given name, credit hours, and quality points.
     *
     * @param name          the student's name
     * @param creditHours   total credit hours earned
     * @param qualityPoints total quality points earned
     */
    public Student(String name, double creditHours, double qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    /**
     * Returns the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's credit hours.
     *
     * @return credit hours earned
     */
    public double getCreditHours() {
        return creditHours;
    }

    /**
     * Returns the student's quality points.
     *
     * @return quality points earned
     */
    public double getQualityPoints() {
        return qualityPoints;
    }

    /**
     * Computes the student's grade point average as the quotient of
     * quality points and credit hours.
     *
     * @return the student's GPA
     */
    public double gpa() {
        return qualityPoints / creditHours;
    }

    /**
     * Sets the minimum GPA threshold required for honor society membership.
     * This threshold applies to all Student objects since it is static.
     *
     * @param threshold the minimum qualifying GPA
     */
    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }

    /**
     * Returns the current GPA threshold for honor society membership.
     *
     * @return the GPA threshold
     */
    public static double getGpaThreshold() {
        return gpaThreshold;
    }

    /**
     * Determines whether this student is eligible for honor society
     * membership. The base requirement that applies to all students is
     * that their GPA exceeds the threshold. Subclasses override this
     * method to add additional requirements, calling this method via
     * super to avoid duplicating the base GPA check.
     *
     * @return true if the student's GPA exceeds the threshold, false otherwise
     */
    public boolean eligibleForHonorSociety() {
        return gpa() > gpaThreshold;
    }

    /**
     * Returns a string containing the student's name and GPA, formatted
     * to two decimal places.
     *
     * @return a string representation of the student
     */
    @Override
    public String toString() {
        return String.format("Name: %s GPA %.2f", name, gpa());
    }
}
