import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileStatistics {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String filePath = "10m.txt";
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Не вдалося відкрити файл: " + e.getMessage());
            return;
        }

        List<Integer> longestIncreasing = findLongestIncreasingSubsequence(numbers);
        List<Integer> longestDecreasing = findLongestDecreasingSubsequence(numbers);

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);

        Collections.sort(numbers);
        int count = numbers.size();
        double median;
        if (count % 2 == 0) {
            median = (numbers.get(count / 2 - 1) + numbers.get(count / 2)) / 2.0;
        } else {
            median = numbers.get(count / 2);
        }

        double average = numbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

        long endTime = System.currentTimeMillis();
        double executionTime = (endTime - startTime) / 1000.0;

        System.out.println("Максимальне число: " + max);
        System.out.println("Мінімальне число: " + min);
        System.out.println("Медіана: " + median);
        System.out.println("Середнє арифметичне: " + average);
        System.out.println("Найбільша зростаюча послідовність: " + longestIncreasing);
        System.out.println("Найбільша спадаюча послідовність: " + longestDecreasing);
        System.out.println("Час обробки файлу: " + executionTime + " секунд");
    }

    public static List<Integer> findLongestIncreasingSubsequence(List<Integer> arr) {
        List<Integer> longest = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (current.isEmpty() || arr.get(i) > current.get(current.size() - 1)) {
                current.add(arr.get(i));
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(arr.get(i));
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }

    public static List<Integer> findLongestDecreasingSubsequence(List<Integer> arr) {
        List<Integer> longest = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (current.isEmpty() || arr.get(i) < current.get(current.size() - 1)) {
                current.add(arr.get(i));
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(arr.get(i));
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }
}
