package Project2_Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project2.java
 * Programming Project 2
 * Date: 07/06/2026
 *
 * Reads student records from students.txt, builds Student objects of the
 * appropriate subclass (Undergraduate or Graduate), computes the average
 * GPA across all students, sets the honor society GPA threshold to the
 * midpoint between the average GPA and 4.0, and prints a report listing
 * every student who qualifies for honor society membership.
 */
public class Project2 {

    // Name of the input file containing student records
    private static final String INPUT_FILE_NAME = "studentTrek.txt";

    // Highest possible GPA, used to compute the honor society threshold
    private static final double MAX_GPA = 4.0;

    /**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if students.txt cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File(INPUT_FILE_NAME);
        createfile(INPUT_FILE_NAME);
        Scanner fileScanner;

        // Attempt to open the input file; terminate with an error message
        // if it does not exist.
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            throw e;
        }

        ArrayList<Student> students = new ArrayList<>();
        double gpaTotal = 0.0;

        // Read each line of the file and build the appropriate Student subtype
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            if (line.isBlank()) {
                continue;
            }

            // Each line contains 4 space-separated values:
            // name, credit hours, quality points, year or degree
            String[] tokens = line.trim().split("\\s+");

            String name = tokens[0];
            double creditHours = Double.parseDouble(tokens[1]);
            double qualityPoints = Double.parseDouble(tokens[2]);
            String classification = tokens[3];

            Student student = createStudent(name, creditHours, qualityPoints, classification);
            students.add(student);
            gpaTotal += student.gpa();
        }

        fileScanner.close();

        // Compute the average GPA across all students read in
        double averageGpa = gpaTotal / students.size();

        // Set the honor society threshold to the midpoint between the
        // average GPA and the highest possible GPA
        double threshold = (averageGpa + MAX_GPA) / 2.0;
        Student.setGpaThreshold(threshold);

        System.out.printf("GPA threshold for membership is %.2f%n%n", threshold);

        // Print a report of every student eligible for honor society membership
        for (Student student : students) {
            if (student.eligibleForHonorSociety()) {
                System.out.println(student);
            }
        }
    }

    /**
     * Creates a Student object of the correct subtype based on the
     * classification value from the input file. Undergraduate students
     * are identified by class rank (Freshman, Sophomore, Junior, Senior);
     * graduate students are identified by degree sought (Masters, Doctorate).
     *
     * @param name           the student's name
     * @param creditHours    credit hours earned
     * @param qualityPoints  quality points earned
     * @param classification the year (undergraduate) or degree (graduate)
     * @return a new Undergraduate or Graduate object
     */
    private static Student createStudent(String name, double creditHours,
        double qualityPoints, String classification) {
        switch (classification.toLowerCase()) {
            case "freshman":
            case "sophomore":
            case "junior":
            case "senior":
                return new Undergraduate(name, creditHours, qualityPoints, classification);
            case "masters":
            case "doctorate":
                return new Graduate(name, creditHours, qualityPoints, classification);
            default:
                throw new IllegalArgumentException("Unknown classification: " + classification);
        }
    }

    /**
     * Ensures the input file exists by creating it if necessary.
     *
     * @param fileName name of the file to create if missing
     */
    private static void createfile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ignored) {
                // If the file cannot be created, the existing FileNotFoundException
                // handling in main will report the problem.
            }
        }
    }
}
