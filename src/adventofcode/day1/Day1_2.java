package src.adventofcode.day1;

import java.util.Arrays;

public class Day1_2 {

    private static int total = 0;

    public static void main(String[] args) {
        String str = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                  """;
        str = replaceTextToNumber(str);

        Arrays.stream(str.split("\\r?\\n")).forEach(line -> {
            int fusion = Integer.parseInt(getFirstNumber(line) + getLastNumber(line));
            total += fusion;
        });
        System.out.println("Total: " + total);
    }

    private static String replaceTextToNumber(String str) {
        return str.replace("one", "o1e")
                .replace("two", "t2o")
                .replace("three", "t3e")
                .replace("four", "f4r")
                .replace("five", "f5e")
                .replace("six", "s6x")
                .replace("seven", "s7n")
                .replace("eight", "e8t")
                .replace("nine", "n9e");
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
