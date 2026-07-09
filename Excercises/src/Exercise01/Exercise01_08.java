/* (Compute the area and perimeter of a rectangle) Write a program that displays the area and perimeter of a rectangle with a width of 4.5 and a height of 7.9 using the following formulas:
   Area = width * height
   Perimeter = 2 * (width + height)
*/
package Exercise01;

public class Exercise01_08 {

    public static void main(String[] args) {
        double width = 4.5;
        double height = 7.9;
        double area = width * height;
        double perimeter = 2 * (width + height);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }

}
