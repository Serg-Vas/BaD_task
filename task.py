import time
import sys

sys.setrecursionlimit(1000000)
memory_limit = 1024 * 1024 * 1024
try:
    import resource
    resource.setrlimit(resource.RLIMIT_AS, (memory_limit, memory_limit))
except ImportError:
    pass

start_time = time.time()

file_path = '10m.txt'

numbers = []
try:
    with open(file_path, 'r') as file:
        for line in file:
            numbers.append(int(line))
except IOError:
    print("Не вдалося відкрити файл.")
    sys.exit()

def find_longest_increasing_subsequence(arr):
    longest = []
    current = []

    for num in arr:
        if len(current) == 0 or num > current[-1]:
            current.append(num)
        else:
            if len(current) > len(longest):
                longest = current
            current = [num]

    if len(current) > len(longest):
        longest = current

    return longest

def find_longest_decreasing_subsequence(arr):
    longest = []
    current = []

    for num in arr:
        if len(current) == 0 or num < current[-1]:
            current.append(num)
        else:
            if len(current) > len(longest):
                longest = current
            current = [num]

    if len(current) > len(longest):
        longest = current

    return longest

longest_increasing = find_longest_increasing_subsequence(numbers)
longest_decreasing = find_longest_decreasing_subsequence(numbers)

max_num = max(numbers)
min_num = min(numbers)

numbers.sort()
count = len(numbers)
median = (numbers[count // 2 - 1] + numbers[count // 2]) / 2 if count % 2 == 0 else numbers[count // 2]

average = sum(numbers) / count

end_time = time.time()
execution_time = end_time - start_time 

print(f"Максимальне число: {max_num}")
print(f"Мінімальне число: {min_num}")
print(f"Медіана: {median}")
print(f"Середнє арифметичне: {average}")
print(f"Найбільша зростаюча послідовність: {', '.join(map(str, longest_increasing))}")
print(f"Найбільша спадаюча послідовність: {', '.join(map(str, longest_decreasing))}")
print(f"Час обробки файлу: {execution_time} секунд")
