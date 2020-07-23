package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 希尔排序：
 * 把较大的数据集根据增量分割成若干个小组，对每一个小组分别进行插入排序，依次增量不断除以2，再对每一个小组分别进行插入排序，直到排序完成。
 */
public class ShellSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_sort);

        int[] a = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        int len = a.length;//个数为9

        int[] res = shellSort(a, len);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(res));
    }

    /**
     * 8, 1, 5, 4, 9, 2, 6, 3, 7（d=4->进行插入排序）
     * 7, 1, 5, 3, 8, 2, 6, 4, 9（d=2->进行插入排序）
     * 5, 1, 6, 2, 7, 3, 8, 4, 9（d=1->进行插入排序）
     * 1, 2, 3, 4, 5, 6, 7, 8, 9
     *
     * @param arr
     * @param len
     * @return
     */
    private int[] shellSort(int[] arr, int len) {
        int d = len / 2;
        while (d > 0) {
            for (int i = 0; i < len; i += d) {
                for (int j = i; j > 0; j -= d) {
                    if (arr[j] < arr[j - d]) {
                        int temp = arr[j];
                        arr[j] = arr[j - d];
                        arr[j - d] = temp;
                    }
                }
            }
            d /= 2;
        }
        return arr;
    }
}
