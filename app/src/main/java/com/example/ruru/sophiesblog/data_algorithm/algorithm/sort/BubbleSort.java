package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * 冒泡排序：
 * 不断比较相邻位置，把最大元素放到最后。
 */
public class BubbleSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);

        int[] a = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        int len = a.length;//个数为9

        int[] res = bubbleSort(a, len);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(res));//[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    private int[] bubbleSort(int[] array, int len) {
        int i = len;
        while (i > 0) {
            for (int j = 0; j < i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            i--;
        }
        return array;
    }
}
