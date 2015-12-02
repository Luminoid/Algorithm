# String Matching
## string-matching problem
We assume that the text is an array T[1...n] of length n and that the pattern is an array P[1...m] of length m ≤ n. 
We further assume that the elements of P and T are characters drawn from a finite alphabet ∑.
The character arrays P and T are often called strings of characters.
We say that pattern P occurs with shift s in text T if 0 ≤ s ≤ n-m and T[s+1 ... s+m] = P[1 ... m].
The string-matching problem is the problem of finding all valid shifts with which a given pattern P occurs in a given text T.

## Algorithm
1. The naive string-matching algorithm
2. The Rabin-Karp algorithm
3. The Rabin-Karp algorithm on two-dimensional array
4. String matching with finite automata
5. The Knuth-Morris-Pratt algorithm
6. String cyclic rotation judging algorithm based on KMP in linear time

## Time analysis
|     Algorithm      | Preprocessing time | Matching time |
| :----------------- | :----------------- | :------------ |
| Naive              |  0                 |  Ο((n-m-1)m)  |
| Rabin-Karp         |  Θ(m)              |  Ο((n-m-1)m)  |
| Finite automaton   |  Ο(m\|∑\|)         |  Θ(n)         |
| Knuth-Morris-Pratt |  Θ(m)              |  Θ(n)         |