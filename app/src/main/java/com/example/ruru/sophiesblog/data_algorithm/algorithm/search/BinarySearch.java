package com.example.ruru.sophiesblog.data_algorithm.algorithm.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_search);

        int[] arr = new int[]{10, 11, 5, 4, 12, 2, 6, 3, 7};
        int len = arr.length;

        //先排序
        Arrays.sort(arr);//1 2 3 4 5 6 7 8 9

        int res = binarySearch(arr, 2, len);
        int res1 = binarySearch1(arr, 10, 0, arr.length - 1);
        Log.d(getClass().getName(), "onCreate:res= " + res1);
    }

    /**
     * 非递归版本
     *
     * @param arr
     * @param value
     * @param len
     * @return
     */
    private int binarySearch(int[] arr, int value, int len) {

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            int midVal = arr[mid];

            if (value < midVal) {
                right = mid - 1;
            } else if (value > midVal) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * 递归版本
     *
     * @param a
     * @param value
     * @param left
     * @param right
     * @return
     */
    private int binarySearch1(int[] a, int value, int left, int right) {
        int mid = (left + right) / 2;

        if (a[mid] == value)
            return mid;
        if (a[mid] > value)
            return binarySearch1(a, value, left, mid - 1);
        if (a[mid] < value)
            return binarySearch1(a, value, mid + 1, right);
        return -1;
    }
}
