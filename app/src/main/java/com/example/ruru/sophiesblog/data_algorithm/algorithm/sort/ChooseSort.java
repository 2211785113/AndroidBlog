package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 选择排序：
 * 1到N-1中找到最小元素，与第一个元素互换位置，依次执行下去。
 */
public class ChooseSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sort);

        int[] a = new int[]{1, 8, 5, 4, 9, 2, 6, 3, 7};
        int len = a.length;

        int[] res = chooseSort(a, len);
        Log.d(getClass().getName(), "onCreate:res= " + Arrays.toString(res));//结果为[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    private int[] chooseSort(int[] array, int len) {
        int min = 0;
        while (min < len - 1) {
            for (int i = min + 1; i < len; i++) {
                if (array[i] < array[min]) {
                    int temp = array[i];
                    array[i] = array[min];
                    array[min] = temp;
                }
            }
            min++;
        }
        return array;
    }

    private int[] chooseSort1(int[] arr, int len) {
        for (int i = 0; i < len - 1; i++) {
            int index = i;
            int j;
            for (j = i + 1; j < len; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int tmp = arr[index];
            arr[index] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
}
