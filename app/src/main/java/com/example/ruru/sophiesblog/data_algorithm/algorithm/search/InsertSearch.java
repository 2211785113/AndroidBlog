package com.example.ruru.sophiesblog.data_algorithm.algorithm.search;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 插值查找
 */
public class InsertSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_search);

        int[] arr = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        int len = arr.length;

        //先排序
        Arrays.sort(arr);//1 2 3 4 5 6 7 8 9
        int left = 0;
        int right = len - 1;

        int res = insertSearch(arr, 10, left, right);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    private int insertSearch(int[] arr, int value, int left, int right) {
        if (left <= right) {
            int mid = left + ((value - arr[left]) / (arr[right] - arr[left])) * (right - left);
            int midVal = arr[mid];

            if (value == midVal) {
                return mid;
            }

            if (value > midVal) {
                return insertSearch(arr, value, mid + 1, right);
            }

            if (value < midVal) {
                return insertSearch(arr, value, left, mid - 1);
            }
        }
        return -1;
    }
}
