package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLineReader {
    public static final String BLANK_CHARACTER = "";
    public static Integer maxValue = Integer.MIN_VALUE;

    public static void main(String [] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("day1/elf_inventory.txt"));

        String line = "";
        try {
            ArrayList<String> calories = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                if (!line.equals(BLANK_CHARACTER)) {
                    calories.add(line);
                } else {
                    System.out.println(calories.toString());
                    maxValue = Math.max(largestCaloricContent(calories.toArray(new String [calories.size()])), maxValue);
                    calories.clear();
                }
            }
            System.out.println(maxValue);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer largestCaloricContent(String [] calories) {
        return Stream.of(calories).map(Integer::valueOf).collect(Collectors.summingInt(Integer::intValue));
    }
}
