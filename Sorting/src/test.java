import java.util.Arrays;

/**
 * Created by Ethan on 15/10/24.
 */
public class test {
    public static void insert(int[] a){
        int key;
        int j;

        for(int i = 1; i < a.length; i++){
            key = a[i];
            for (j = i-1; j >= 0 && a[j] >= key ; j--) {
                a[j+1] = a[j];
            }
            a[j+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 2, 3, 8, 5, 3, 1, 12};
        insert(array);
        System.out.println(Arrays.toString(array));
    }
}
