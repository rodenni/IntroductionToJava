public class Exercise01_04 {
    public static void main(String[] args) {
        int number;
        for (int i = 0; i < 1; i++) {
            System.out.println("a     a^2     a^3");
            for (int j = 1; j < 5; j++) {
                number = j;
                System.out.println(number + "     " + (number * number) +"       " + (number * number * number));
            }
        }
    }
}
