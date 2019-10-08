package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heap_sort);

        int[] a = new int[]{5, 2, 7, 3, 6, 1, 4};
        int len = a.length;//个数为7

        int[] res = heapSort(a, len);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(res));//[1, 2, 3, 4, 5, 6, 7]
    }

    public int[] heapSort(int[] arr, int len) {
        //建立最大堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapfy(arr, i, len);
        }
        for (int i = len - 1; i >= 0; i--) {
            //先交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //再建最大堆
            heapfy(arr, 0, i);
        }
        return arr;
    }

    private void heapfy(int[] arr, int cur, int len) {
        int max = cur;
        if (cur < len) {
            int left = 2 * cur + 1;
            if (left < len) {
                if (arr[left] > arr[max]) {
                    max = left;
                }
            }
            int right = 2 * cur + 2;
            if (right < len) {
                if (arr[right] > arr[max]) {
                    max = right;
                }
            }
            if (max != cur) {
                int temp = arr[max];
                arr[max] = arr[cur];
                arr[cur] = temp;

                heapfy(arr, max, len);
            }
        }
    }
}
