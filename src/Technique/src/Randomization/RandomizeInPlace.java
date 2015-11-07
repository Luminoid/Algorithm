package Randomization;

import java.util.Arrays;

/**
 * Created by Ethan on 15/10/27.
 */
public class RandomizeInPlace {
    public static void randomizeInPlace(int[] a){
        int n = a.length;
        int swap;
        int tmp;
        for (int i = 0; i < n; i++) {
            swap = (int)(i + Math.random()*(n-i));
            tmp = a[i];
            a[i] = a[swap];
            a[swap] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        randomizeInPlace(array);
        System.out.println(Arrays.toString(array));
    }
}
