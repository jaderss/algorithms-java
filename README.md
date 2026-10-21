# algorithms-java

[![CI](https://github.com/jaderss/algorithms-java/actions/workflows/ci.yml/badge.svg)](https://github.com/jaderss/algorithms-java/actions/workflows/ci.yml)

46 classic interview problems in Java 25, grouped by the pattern that solves them. Each problem is one class with one package per pattern. Each class has example-based tests taken from the problem statement plus edge cases, and each pattern has jqwik properties that check the solutions against brute-force oracles.

These were written in 2026 with an AI coding assistant. The tests and properties are what back them up.

## Running

```bash
./mvnw verify
```

That builds, runs about 300 tests and properties, and checks formatting with Spotless (Palantir Java Format). `./mvnw spotless:apply` fixes formatting.

The `benchmarks` module packages a JMH jar:

```bash
./mvnw package -DskipTests
java -jar benchmarks/target/benchmarks.jar             # everything, about 7 minutes
java -jar benchmarks/target/benchmarks.jar KthLargest   # one class
```

## Problems

Complexity is time, then extra space beyond the input and output. `n` is the input size, `h` the tree height, `k` the number of lists or the rank asked for.

| Problem | Pattern | Time | Space |
|---|---|---|---|
| [Two Sum](https://leetcode.com/problems/two-sum/) | Arrays and hashing | O(n) | O(n) |
| [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Arrays and hashing | O(n · m log m) | O(n · m) |
| [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Arrays and hashing | O(n) buckets, O(n log k) heap | O(n) |
| [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Arrays and hashing | O(n) | O(1) |
| [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Arrays and hashing | O(n) | O(n) |
| [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Two pointers | O(n) | O(1) |
| [3Sum](https://leetcode.com/problems/3sum/) | Two pointers | O(n²) | O(n) |
| [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Two pointers | O(n) | O(1) |
| [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Two pointers | O(n) | O(1) |
| [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Sliding window | O(n) | O(alphabet) |
| [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Sliding window | O(n + m) | O(m) |
| [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Sliding window | O(n) | O(k) |
| [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Stack | O(n) | O(n) |
| [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Stack | O(n) | O(n) |
| [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Stack | O(n) | O(n) |
| [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Binary search | O(log n) | O(1) |
| [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | Binary search | O(log n) | O(1) |
| [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Binary search | O(log min(m, n)) | O(1) |
| [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Linked list | O(n) | O(1) |
| [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Linked list | O(n + m) | O(1) |
| [LRU Cache](https://leetcode.com/problems/lru-cache/) | Linked list | O(1) per operation | O(capacity) |
| [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Linked list | O(n log k) | O(k) |
| [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) | Trees | O(n) | O(h) |
| [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Trees | O(n) | O(h) |
| [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | Trees | O(n) | O(h) |
| [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Trees | O(n) | O(h) |
| [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Trees | O(n) | O(n) |
| [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Heap | O(n log k) heap, O(n) average quickselect | O(k) heap, O(n) quickselect |
| [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Heap | O(log n) add, O(1) median | O(n) |
| [Number of Islands](https://leetcode.com/problems/number-of-islands/) | Graphs | O(rows · columns) | O(rows · columns) |
| [Clone Graph](https://leetcode.com/problems/clone-graph/) | Graphs | O(V + E) | O(V) |
| [Course Schedule](https://leetcode.com/problems/course-schedule-ii/) | Graphs | O(V + E) | O(V + E) |
| [Word Ladder](https://leetcode.com/problems/word-ladder/) | Graphs | O(n · 26 · L²) | O(n · L) |
| [Number of Connected Components](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) | Union-find | O((V + E) α(V)) | O(V) |
| [Redundant Connection](https://leetcode.com/problems/redundant-connection/) | Union-find | O(E α(V)) | O(V) |
| [Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/) | Trie | O(L) per operation | O(total letters) |
| [Word Search II](https://leetcode.com/problems/word-search-ii/) | Trie | O(cells · 4 · 3^(L-1)) | O(total letters) |
| [Subsets](https://leetcode.com/problems/subsets/) | Backtracking | O(n · 2ⁿ) | O(n) |
| [Permutations](https://leetcode.com/problems/permutations/) | Backtracking | O(n · n!) | O(n) |
| [Combination Sum](https://leetcode.com/problems/combination-sum/) | Backtracking | Exponential in target / smallest candidate | O(target / smallest candidate) |
| [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | Dynamic programming | O(n) | O(1) |
| [Coin Change](https://leetcode.com/problems/coin-change/) | Dynamic programming | O(amount · coins) | O(amount) |
| [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | Dynamic programming | O(n log n) | O(n) |
| [Edit Distance](https://leetcode.com/problems/edit-distance/) | Dynamic programming | O(m · n) | O(n) |
| [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Intervals | O(n log n) | O(n) |
| [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) | Intervals | O(n log n) | O(n) |

## Where these differ from the LeetCode signatures

- A missing answer is an empty `Optional`, `OptionalInt` or list, not `-1` or `0`. That covers Two Sum, the rotated-array search, Word Ladder, Coin Change, Lowest Common Ancestor and Redundant Connection.
- Areas and water volumes are `long`. Sums that could overflow `int` are done in `long`, and Climbing Stairs throws rather than wrapping past `ways(91)`. The overflow cases have their own tests.
- No solution changes its input. Quickselect and 3Sum work on a copy, and Number of Islands keeps its own visited grid.
- Course Schedule returns an order (Course Schedule II), and an empty result means the prerequisites have a cycle.
- Lowest Common Ancestor returns empty when either value is missing from the tree, instead of the classic algorithm's misleading answer.
- `Interval` allows `start == end`. Merge Intervals joins intervals that touch, as LeetCode does, while Non-overlapping Intervals treats touching intervals as compatible. A property test found that a zero-length interval tying on the end broke the greedy order, so ties now sort by start.

## Benchmarks

Measured on an Apple M4 Max with Temurin 25.0.4, average time per call in microseconds, lower is better.

Top K Frequent Elements, k = 10:

| Values | Distinct | Buckets | Heap | Sorting |
|---|---|---|---|---|
| 10,000 | 100 | 49 | 51 | 48 |
| 10,000 | 100,000 | 211 | 231 | 212 |
| 1,000,000 | 100 | 4,977 | 5,116 | 4,953 |
| 1,000,000 | 100,000 | 10,885 | 12,028 | 15,904 |

Counting dominates when few values are distinct, so the three tie. With many distinct values, sorting every count falls behind. Buckets only wins once they are sized by the highest count. The first version allocated one bucket per input value and was the slowest of the three, at 25,541 µs on the last row.

Kth Largest Element:

| Values | k | Heap | Quickselect | Sorting |
|---|---|---|---|---|
| 10,000 | 10 | 106 | 54 | 158 |
| 10,000 | n / 2 | 207 | 91 | 162 |
| 1,000,000 | 10 | 11,047 | 5,257 | 43,765 |
| 1,000,000 | n / 2 | 84,426 | 8,978 | 44,284 |

Quickselect wins everywhere. A heap of size k is a fine choice when k is small or the values arrive as a stream. At k = n / 2 the heap does worse than sorting, since it keeps half a million boxed integers ordered.

## Layout

```
problems/src/main/java/dev/jaderss/algorithms/<pattern>/   one class per problem
problems/src/test/java/dev/jaderss/algorithms/<pattern>/   <Problem>Test plus <Pattern>PropertiesTest
benchmarks/src/main/java/dev/jaderss/algorithms/benchmarks/
```
