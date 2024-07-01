using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;

class Program
{
    static void Main()
    {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.Start();

        string filePath = @"..\10m.txt"; //вводимо шлях до текстового файлу розташованого локально на носії

        List<int> numbers = new List<int>();
        using (StreamReader sr = new StreamReader(filePath))
        {
            string line;
            while ((line = sr.ReadLine()) != null)
            {
                numbers.Add(int.Parse(line));
            }
        }

        List<int> longestIncreasing = LongestIncreasingSubsequence(numbers);
        List<int> longestDecreasing = LongestDecreasingSubsequence(numbers);

        int max = numbers.Max();
        int min = numbers.Min();

        numbers.Sort();
        int count = numbers.Count;
        double median = (count % 2 == 0) ? (numbers[count / 2 - 1] + numbers[count / 2]) / 2.0 : numbers[count / 2];
        double average = numbers.Average();

        stopwatch.Stop();
        TimeSpan executionTime = stopwatch.Elapsed;

        Console.WriteLine($"Максимальне число: {max}");
        Console.WriteLine($"Мінімальне число: {min}");
        Console.WriteLine($"Медіана: {median}");
        Console.WriteLine($"Середнє арифметичне: {average}");
        Console.WriteLine($"Найбільша зростаюча послідовність: {string.Join(", ", longestIncreasing)}");
        Console.WriteLine($"Найбільша спадаюча послідовність: {string.Join(", ", longestDecreasing)}");
        Console.WriteLine($"Час обробки файлу: {executionTime.TotalSeconds} секунд");
    }

    static List<int> LongestIncreasingSubsequence(List<int> arr)
    {
        List<int> longest = new List<int>();
        List<int> current = new List<int>();

        foreach (int num in arr)
        {
            if (current.Count == 0 || num > current.Last())
            {
                current.Add(num);
            }
            else
            {
                if (current.Count > longest.Count)
                {
                    longest = new List<int>(current);
                }
                current.Clear();
                current.Add(num);
            }
        }

        if (current.Count > longest.Count)
        {
            longest = new List<int>(current);
        }

        return longest;
    }

    static List<int> LongestDecreasingSubsequence(List<int> arr)
    {
        List<int> longest = new List<int>();
        List<int> current = new List<int>();

        foreach (int num in arr)
        {
            if (current.Count == 0 || num < current.Last())
            {
                current.Add(num);
            }
            else
            {
                if (current.Count > longest.Count)
                {
                    longest = new List<int>(current);
                }
                current.Clear();
                current.Add(num);
            }
        }

        if (current.Count > longest.Count)
        {
            longest = new List<int>(current);
        }

        return longest;
    }
}
