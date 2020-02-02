package com.example.ruru.sophiesblog.data_algorithm.algorithm.search;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 斐波那契查找(随后再跑跑)
 * 为什么用F(k)-1???
 */
public class FibonacciSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_search);

        int[] arr = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        Arrays.sort(arr);//1 2 3 4 5 6 7 8 9
        int len = arr.length;//9

        int res = fibonacciSearch(arr, len, 2);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 最原始的数组：[1, 2, 3, 4, 5, 6, 7, 8, 9] 9, 9, 9, 9    9+4=13
     * 斐波那契数列：[1, 1, 2, 3, 5, 8, 13, 21, 34]            13>=9
     * 前半部分：8个元素，后半部分：5个元素
     * <p>
     * len=9
     * 因为：8<9
     * 所以：k=7
     * F[k]-1=13
     * mid=7
     */
    private int fibonacciSearch(int[] arr, int len, int value) {
        int left = 0;
        int right = len - 1;

        int[] F = new int[len];
        createFibonacci(F, len);

        int k = 0;
        while (F[k] - 1 < len)
            k++;//k=7

        Log.d(getClass().getName(), "fibonacciSearch:F(k)-1= " + (F[k] - 1));//12
        int[] temp = new int[F[k] - 1];
        for (int i = 0; i < len; i++)
            temp[i] = arr[i];
        for (int i = len; i < F[k] - 1; i++)
            temp[i] = arr[len - 1];

        while (left <= right) {
            int mid = left + F[k - 1] - 1;//mid=7
            if (value < temp[mid]) {
                right = mid - 1;
                k -= 1;//k=6
            } else if (value > temp[mid]) {
                left = mid + 1;
                k -= 2;
            } else {
                if (mid < len)
                    return mid;
                else
                    return len - 1;
            }
        }
        return -1;
    }


    /**
     * 构造斐波那契数组
     *
     * @param F
     * @param len
     */
    private void createFibonacci(int[] F, int len) {
        F[1] = 1;
        F[2] = 1;
        for (int i = 3; i < len; i++)
            F[i] = F[i - 1] + F[i - 2];
    }
}
