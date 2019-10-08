package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radix_sort);

        int[] a = new int[]{5, 2, 732, 32, 61, 123, 4};

        int maxDigit = getMaxDigit(a);
        radixSort(a, maxDigit);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(a));//[2, 4, 5, 32, 61, 123, 732]
    }

    /**
     * 获取个位数：%10
     * 获取十位数：%100/10
     * 获取百位数：%1000//100
     *
     * @param arr
     * @param maxDigit
     * @return
     */
    private int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        //低位排序：0/1/2
        for (int i = 0; i < maxDigit; i++, mod *= 10, dev *= 10) {
            //考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }
        return arr;
    }

    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    /**
     * 获取最大值
     *
     * @param arr
     * @return
     */
    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * 获取最大值的位数
     *
     * @param num
     * @return
     */
    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }
}
