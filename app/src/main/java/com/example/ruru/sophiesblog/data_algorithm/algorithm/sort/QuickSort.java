package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 快速排序
 */
public class QuickSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_sort);

        int[] a = new int[]{5, 2, 7, 3, 6, 1, 4};
        int left = 1;
        int right = a.length - 2;//5

        quickSort(a, left, right);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(a));//[1, 2, 3, 4, 5, 6, 7]
    }

    private void quickSort(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        int i = left - 1;
        int j = right + 1;
        if (left < right) {
            while (true) {
                while (arr[++i] < arr[mid]) ;//i=2 i=3
                while (arr[--j] > arr[mid]) ;//j=5 j=3>>>>>>>>>>>2,1,3,6,7
                if (i >= j)
                    break;
                Log.d(getClass().getName(), "quickSort: i=" + i + " j=" + j);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, j + 1, right);
        }
    }
}
