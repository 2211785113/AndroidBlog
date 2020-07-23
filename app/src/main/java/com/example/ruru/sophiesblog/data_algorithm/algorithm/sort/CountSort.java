package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 计数排序
 */
public class CountSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_sort);

        int[] a = new int[]{8, 12, 5, 4, 9, 11, 6, 3, 7};
        int len = a.length;//个数为9

        countSort(a, len);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(a));//[3, 4, 5, 6, 7, 8, 9, 11, 12]
    }

    private void countSort(int[] arr, int len) {
        if (len == 0)
            return;

        //min=3,max=12
        int min = arr[0], max = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }

        //3 4 5 6 7 8 9 10 11 12
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < len; i++) {
            bucket[arr[i] - min]++;
        }

        int i = 0, j = 0;
        while (i < len) {
            if (bucket[j] != 0) {
                arr[i] = j + min;
                bucket[j]--;
                i++;
            } else
                j++;
        }
    }
}
