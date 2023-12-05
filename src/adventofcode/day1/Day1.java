package src.adventofcode.day1;

import java.util.Arrays;

public class Day1 {

    private static int total = 0;

    public static void main(String[] args) {
        final String str = """
                1abc2
                pqr3stu8vwx®
                a1b2c3d4e5f
                treb7uchet
                """;

        Arrays.stream(str.split("\\r?\\n")).forEach(line -> {
            String first = getFirstNumber(line);
//            System.out.println("First number: " + first);
            String last = getLastNumber(line);
//            System.out.println("Last  number: " + last);
            int fusion = Integer.parseInt(first + last);
//            System.out.println("fusion: " + fusion);
            total += fusion;
        });
        System.out.println("Total: " + total);
    }

    private static String getFirstNumber(String str) {
        int i = 0;
        while (i < str.length() && !Character.isDigit(str.charAt(i))) {
            i++;
        }
        return str.substring(i, i + 1);
    }

    private static String getLastNumber(String str) {
        int i = str.length() - 1;
        while (i >= 0 && !Character.isDigit(str.charAt(i))) {
            i--;
        }
        return str.substring(i, i + 1);
    }

}
