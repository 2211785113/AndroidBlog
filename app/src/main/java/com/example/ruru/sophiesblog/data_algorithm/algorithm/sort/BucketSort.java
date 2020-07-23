package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 桶排序：
 * bucketCount:桶的数量
 * bucketSize:一个桶中数的个数
 */
public class BucketSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_sort);

        int[] a = new int[]{8, 12, 5, 4, 9, 11, 6, 3, 7};

        try {
            bucketSort(a, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(a));//[3, 4, 5, 6, 7, 8, 9, 11, 12]
    }

    private void bucketSort(int[] arr, int bucketSize) throws Exception {
        if (arr.length == 0) {
            return;
        }

        //min=3,max=12
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }

        //桶的数量:比如3~12:345 678 91011 12共4个桶。
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        int[][] buckets = new int[bucketCount][0];

        //利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);//1 3 0 0 2 2 1 0 1
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            //对每个桶进行排序，这里使用了插入排序
            bucket = InsertSort.insertSort(bucket, bucket.length);
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
