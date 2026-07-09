/*
(Summation of a series) Write a program that displays the result of
				1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9.
*/
package Exercise01;

public class Exercise01_06 {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
