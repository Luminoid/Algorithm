## Task: 
To improve the running time of sorting by combining quicksort with insertion sort.
## Implementation: 
Upon calling quicksort on a subarray with fewer than k elements, let it simply return without sorting the
 subarray. After the top-level call to quicksort returns, run insertion sort on the entire array to finish the sorting process.
## Expected running time: 
O(kn+nlg(n/k))
### Proof: 
![proofProcess](proofProcess.jpg)

![proofResult](proofResult.jpg)