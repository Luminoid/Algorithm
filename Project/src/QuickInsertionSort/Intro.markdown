## Task: 
To improve the running time of sorting by combining quicksort with insertion sort.
## Implementation: 
Upon calling quicksort on a subarray with fewer than k elements, let it simply return without sorting the
 subarray. After the top-level call to quicksort returns, run insertion sort on the entire array to finish the sorting process.
## Expected running time: 
$O(nk+nlg(n/k))$
### Proof: 
$ T(n, k) = cn\cdot log_2({n\over k})+(ak^2+bk+c)\cdot {n\over k} = cn\cdot log_2({n\over k})+akn+\Theta (n) \sim O(kn+nlg({n\over k})) $

$$ \therefore T(n,k) =
\begin{cases}
\Theta (nlg({n\over k})), & \text{$k<lg({n\over k})$} \\
\Theta (kn), & \text{$k>lg({n\over k})$}
\end{cases}
$$