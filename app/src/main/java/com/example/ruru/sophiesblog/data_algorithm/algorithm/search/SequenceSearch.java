package com.example.ruru.sophiesblog.data_algorithm.algorithm.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

/**
 * 顺序查找
 */
public class SequenceSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence_find);

        int[] arr = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        int len = arr.length;

        int res = sequenceSearch(arr, 5, len);
        int res1 = sequenceSearch1(arr, 5, len);

        Log.d(getClass().getName(), "onCreate:res= " + res1);
    }

    private int sequenceSearch(int[] arr, int value, int len) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private int sequenceSearch1(int[] arr, int value, int len) {
        int i = 0;
        while (i < len && arr[i] != value)
            i++;
        return i;
    }
}
