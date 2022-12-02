package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.PriorityQueue;

public class CommandLineReader {
    public static final String BLANK_CHARACTER = "";
    public static Integer maxValue = Integer.MIN_VALUE;
    public static Integer maxGroup = Integer.MIN_VALUE;
    public static PriorityQueue<Integer> pq;

    public static void main(String [] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("day1/elf_inventory.txt"));

        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) return -1;
                if (o1 == o2) return 0;
                return 1;
            }
        });
        
        ArrayList<Integer> allValues = new ArrayList<>();
        String line = "";
        try {
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                Integer calories = line.equals(BLANK_CHARACTER) ? 0 : Integer.valueOf(line);
                if (!line.equals(BLANK_CHARACTER)) {
                    list.add(line);
                }
                else {
                    maxValue = Math.max(largestCaloricContent(list.toArray(new String [list.size()])), maxValue);
                    int sumCalories = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sumCalories += Integer.valueOf(list.get(i));
                    }
                    allValues.add(sumCalories);
                    list.clear();
                }

            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Max value %d \n", maxValue);
        System.out.printf("Max group %d \n", findNLargestCalories(allValues, 3));
        System.out.printf("Priority queue contents %s \n", pq.toString());

    }


    private static Integer findNLargestCalories(ArrayList<Integer> list, int n) {
    
        System.out.println(list.size());
        for (Integer each : list) {
            pq.add(each);
            if (pq.size() > n) {
                pq.poll();
            }
        }
        
        System.out.println("Queue size" + pq.size());
        int sum = 0;
        while (pq.size() > 0) {
            sum += pq.poll();
        }
        return sum;
    }

    public static Integer largestCaloricContent(String [] calories) {
        return Stream.of(calories).map(Integer::valueOf).collect(Collectors.summingInt(Integer::intValue));
    }
}
