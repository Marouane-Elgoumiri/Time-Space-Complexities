# Comprehensive Guide to Time and Space Complexity Analysis
<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![LeetCode](https://img.shields.io/badge/LeetCode-000000?style=for-the-badge&logo=LeetCode&logoColor=#d16c06)
![HackerRank](https://img.shields.io/badge/-Hackerrank-2EC866?style=for-the-badge&logo=HackerRank&logoColor=white)

</div>

## Table of Contents
1. Basic Principles
2. Time Complexity Analysis
3. Space Complexity Analysis
4. Common Patterns and Examples
5. Special Cases
6. Best Practices
7. Interview Tips

## 1. Basic Principles

### Big O Notation Rules
1. **Drop Constants**: O(2n) → O(n)
2. **Drop Non-Dominant Terms**: O(n² + n) → O(n²)
3. **Different Variables**: Keep different input sizes separate. Example: O(n + m)

### Common Complexities (from best to worst)
1. O(1) - Constant
2. O(log n) - Logarithmic
3. O(n) - Linear
4. O(n log n) - Log Linear
5. O(n²) - Quadratic
6. O(2ⁿ) - Exponential
7. O(n!) - Factorial

## 2. Time Complexity Analysis

### Step-by-Step Analysis Method
1. Identify the input(s)
2. Count operations inside each loop/recursion
3. Multiply nested operations
4. Add separate operations
5. Simplify using Big O rules

### Common Structures Analysis

#### 1. Loops
```python
# Simple loop: O(n)
for i in range(n):
    print(i)

# Nested loops: O(n²)
for i in range(n):
    for j in range(n):
        print(i, j)

# Loop with increment: O(n)
i = 0
while i < n:
    i += 2  # Still O(n) even though it processes half the elements
```

#### 2. Recursion
```python
# Simple recursion: O(n)
def factorial(n):
    if n <= 1: return 1
    return n * factorial(n-1)

# Binary recursion: O(2ⁿ)
def fibonacci(n):
    if n <= 1: return n
    return fibonacci(n-1) + fibonacci(n-2)

# Divide and conquer: O(log n)
def binary_search(arr, target, left, right):
    if left > right: return -1
    mid = (left + right) // 2
    if arr[mid] == target: return mid
    if arr[mid] > target:
        return binary_search(arr, target, left, mid-1)
    return binary_search(arr, target, mid+1, right)
```

### 3. Multiple Inputs
```python
# Two different inputs: O(n + m)
def process_two_lists(list1, list2):
    for x in list1:  # O(n)
        print(x)
    for y in list2:  # O(m)
        print(y)

# Nested loops with different inputs: O(n * m)
def nested_lists(list1, list2):
    for x in list1:      # O(n)
        for y in list2:  # O(m)
            print(x, y)
```

## 3. Space Complexity Analysis

### Memory Usage Patterns

#### 1. Constant Space: O(1)
```python
def find_max(arr):
    max_val = arr[0]  # Single variable
    for num in arr:
        max_val = max(max_val, num)
    return max_val
```

#### 2. Linear Space: O(n)
```python
def create_double_array(arr):
    result = []  # New array proportional to input
    for num in arr:
        result.append(num * 2)
    return result
```

#### 3. Quadratic Space: O(n²)
```python
def create_matrix(n):
    return [[0 for _ in range(n)] for _ in range(n)]
```

### Recursive Space Complexity
```python
# Linear space due to call stack: O(n)
def factorial(n):
    if n <= 1: return 1
    return n * factorial(n-1)

# Linear space despite exponential time: O(n)
def fibonacci(n):
    if n <= 1: return n
    return fibonacci(n-1) + fibonacci(n-2)
```

## 4. Common Patterns and Examples

### Pattern 1: Divide and Conquer
```python
# Binary Search
# Time: O(log n), Space: O(1) iterative, O(log n) recursive
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target: return mid
        if arr[mid] > target:
            right = mid - 1
        else:
            left = mid + 1
    return -1
```

### Pattern 2: Sliding Window
```python
# Find longest substring without repeating characters
# Time: O(n), Space: O(min(m,n)) where m is charset size
def longest_substring(s):
    char_index = {}
    max_length = start = 0
    
    for i, char in enumerate(s):
        if char in char_index and char_index[char] >= start:
            start = char_index[char] + 1
        else:
            max_length = max(max_length, i - start + 1)
        char_index[char] = i
        
    return max_length
```

### Pattern 3: Dynamic Programming
```python
# Fibonacci with memoization
# Time: O(n), Space: O(n)
def fibonacci_dp(n, memo=None):
    if memo is None:
        memo = {}
    if n in memo:
        return memo[n]
    if n <= 1:
        return n
    memo[n] = fibonacci_dp(n-1, memo) + fibonacci_dp(n-2, memo)
    return memo[n]
```

## 5. Special Cases

### Amortized Time Complexity
Example: Dynamic Array Resizing
```python
# ArrayList/Vector push operation
# Single push: O(1) amortized
# Occasional resize: O(n)
# Overall: O(1) amortized time complexity
class DynamicArray:
    def __init__(self):
        self.arr = [0] * 2
        self.size = 0
        
    def append(self, element):
        if self.size == len(self.arr):
            # Double the array size
            new_arr = [0] * (len(self.arr) * 2)
            # Copy elements
            for i in range(self.size):
                new_arr[i] = self.arr[i]
            self.arr = new_arr
        self.arr[self.size] = element
        self.size += 1
```

### Average vs Worst Case
Example: QuickSort
```python
# QuickSort
# Average: O(n log n)
# Worst: O(n²)
def quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quicksort(left) + middle + quicksort(right)
```

## 6. Best Practices

1. **Always Consider Both Time and Space**
   - Trade-offs between time and space are common
   - Sometimes using more space can significantly improve time complexity

2. **Consider All Cases**
   - Best case
   - Average case
   - Worst case

3. **Look for Optimization Opportunities**
   - Can you use a hash table to reduce time complexity?
   - Would sorting first help?
   - Can you use two pointers or a sliding window?

## 7. Interview Tips

1. **Think Aloud**
   - Explain your thought process
   - Discuss trade-offs
   - Consider alternative approaches

2. **Start Simple**
   - Begin with brute force solution
   - Optimize step by step
   - Explain potential improvements

3. **Common Optimization Techniques**
   - Hash tables for O(1) lookup
   - Binary search in sorted arrays
   - Two pointers for array problems
   - Sliding window for substring problems
   - Dynamic programming for overlapping subproblems

### Example Analysis Flow
```python
def find_pair_sum(arr, target):
    seen = {}  # Space: O(n)
    for num in arr:  # Time: O(n)
        complement = target - num
        if complement in seen:
            return [seen[complement], num]
        seen[num] = num
    return []

"""
Analysis:
1. Time Complexity:
   - Single loop through array: O(n)
   - Hash table operations (get/set): O(1)
   - Overall: O(n)

2. Space Complexity:
   - Hash table storing at most n elements: O(n)
   - No recursion or additional data structures
   - Overall: O(n)

3. Trade-offs:
   - Could use less space O(1) with two pointers if array is sorted
   - But would increase time to O(n log n) due to sorting
"""
```
