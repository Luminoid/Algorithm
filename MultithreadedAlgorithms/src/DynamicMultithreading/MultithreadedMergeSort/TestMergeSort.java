package DynamicMultithreading.MultithreadedMergeSort;

/**
 * Created by Ethan on 15/12/9.
 */
public class TestMergeSort {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        for (int i = 1; i <= 10; i++) {
            int num = 1000000 * i;

            int[] array1 = new int[num];
            int[] array2 = new int[num];
            int[] array3 = new int[num];
            for (int j = 0; j < num; j++) {
                array1[j] = (int) (Math.random() * num);
            }
            System.arraycopy(array1, 0, array2, 0, num);
            System.arraycopy(array1, 0, array3, 0, num);

            startTime = System.currentTimeMillis();
            MergeSort.mergeSort(array1);
            endTime = System.currentTimeMillis();
            System.out.println("Ordinary merge sort: The time of size " + num + " is: " + (endTime - startTime));
//            System.out.println(Arrays.toString(array1));

            startTime = System.currentTimeMillis();
            MergeSortParallel1.mergeSort(array2);
            endTime = System.currentTimeMillis();
            System.out.println("Merge Sort Parallel level 1: The time of size " + num + " is: " + (endTime - startTime));
//            System.out.println(Arrays.toString(array2));

            startTime = System.currentTimeMillis();
            MergeSortParallel2.mergeSortP(array3);
            endTime = System.currentTimeMillis();
            System.out.println("Merge Sort Parallel level 2: The time of size " + num + " is: " + (endTime - startTime));
//            System.out.println(Arrays.toString(array3));

            System.out.println("\n**********************************\n");
        }

    }
}