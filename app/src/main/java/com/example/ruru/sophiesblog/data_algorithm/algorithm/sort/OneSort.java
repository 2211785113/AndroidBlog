package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class OneSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_sort);

        int[] arr = {3, 1, 7, 2, 6, 8, 4, 5};//len=8

        change(arr, 0, arr.length - 1);
        Log.d(getClass().getName(), "onCreate:res=" + Arrays.toString(arr));//3,1,2,4,5,6,7,8
    }

    /**
     * 小于最后一个数，值不变
     * 大于最后一个数，不用管
     * 遇到小于的，与之前那一个数交换
     * <p>
     * 3 1 2 7 6 8 4 5   j=3，i=4      j=3,i=6
     * 3 1 2 4 6 8 7 5   j=4,i=7       j=4,i=8
     * 3 1 2 4 5 6 8 7   j=5,i=8       j=6和j=7交换
     * 3 1 2 4 5 6 7 8
     *
     * @param arr
     * @param left
     * @param r
     */
    private void change(int[] arr, int left, int r) {
        int v = arr[r];
        int j = left - 1;
        for (int i = left; i <= r; i++) {
            if (arr[i] <= v) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, r, j + 1);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
