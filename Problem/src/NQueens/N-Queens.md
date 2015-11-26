# N-Queens
## Description
(From [LeetCode](https://leetcode.com/problems/n-queens/))

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where `Q` and `.` both indicate a queen
 and an empty space respectively.
For example, there exist two distinct solutions to the 4-queens puzzle:
```
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
```

## Algorithm
1. Backtracking by two-dimensional boolean array representing the whole chessboard
2. Backtracking by one-dimensional int array representing the queen position in every line

## Solution Number
|   n   | Solution Number |
| :---- | :--------- |
|   1   |   1        |
|   2   |   0        |
|   3   |   0        |
|   4   |   2        |
|   5   |   10       |
|   6   |   4        |
|   7   |   40       |
|   8   |   92       |
|   9   |   352      |
|   10  |   724      |
|   11  |   2680     |
|   12  |   14200    |
|   13  |   73712    |
|   14  |   365596   |
|   15  |   2279184  |
|   16  |   14772512 |
|   17  |   1        |
|   18  |   1        |
|   19  |   1        |
|   20  |   1        |
