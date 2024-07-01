<?php

ini_set('memory_limit', '1G');

$startTime = microtime(true);

$filePath = '10m.txt'; //вводимо шлях до текстового файлу розташованого локально на носії

$handle = fopen($filePath, "r");
if ($handle === false) {
    die("Не вдалося відкрити файл.");
}

$numbers = [];
while (($line = fgets($handle)) !== false) {
    $numbers[] = (int)$line;
}
fclose($handle);

$longestIncreasing = LongestIncreasingSubsequence($numbers);
$longestDecreasing = LongestDecreasingSubsequence($numbers);

// максимальне та мінімальне значення
$max = max($numbers);
$min = min($numbers);

// медіана
sort($numbers);
$count = count($numbers);
$median = ($count % 2 == 0) ? ($numbers[$count / 2 - 1] + $numbers[$count / 2]) / 2 : $numbers[floor($count / 2)];

// середнє арифметичне
$average = array_sum($numbers) / $count;

// найбільша послідовність чисел, яка збільшується
function LongestIncreasingSubsequence($arr) {
    $longest = [];
    $current = [];
    
    foreach ($arr as $i => $num) {
        if ($i === 0 || $num > end($current)) {
            $current[] = $num;
        } else {
            if (count($current) > count($longest)) {
                $longest = $current;
            }
            $current = [$num];
        }
    }
    if (count($current) > count($longest)) {
        $longest = $current;
    }
    return $longest;
}

//найбільша послідовність чисел, яка зменшується
function LongestDecreasingSubsequence($arr) {
    $longest = [];
    $current = [];
    
    foreach ($arr as $i => $num) {
        if ($i === 0 || $num < end($current)) {
            $current[] = $num;
        } else {
            if (count($current) > count($longest)) {
                $longest = $current;
            }
            $current = [$num];
        }
    }
    if (count($current) > count($longest)) {
        $longest = $current;
    }
    return $longest;
}



$endTime = microtime(true); // Кінець вимірювання часу
$executionTime = $endTime - $startTime; // Час виконання скрипта

echo "Максимальне число: $max\n";
echo "Мінімальне число: $min\n";
echo "Медіана: $median\n";
echo "Середнє арифметичне: $average\n";
echo "Найбільша зростаюча послідовність: " . implode(', ', $longestIncreasing) . "\n";
echo "Найбільша спадаюча послідовність: " . implode(', ', $longestDecreasing) . "\n";
echo "Час обробки файлу: $executionTime секунд\n";

?>
