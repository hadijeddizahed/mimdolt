package test;

/**
 * Created by Hadi Jeddi Zahed on 4/11/2017.
 */
public class t {

    public static void main(String[] args) {
        int[] arr3 = { 5, 17, 21, 35, 3, 10, 7, 2, 31, 40, 80 };
        arr3 = selectionSort(arr3);
        print(arr3);
    }

    public static void print(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }



    public static int[] selectionSort(int[] arr)
    {
        for (int i = arr.length - 1; i >= 0; i--)
        {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++)
            {
                if (arr[j] > arr[maxIndex])
                    maxIndex = j;
            }
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;

    }
}
