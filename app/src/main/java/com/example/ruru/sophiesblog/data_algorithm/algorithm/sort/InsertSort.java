package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 插入排序：
 * 第一个元素已排好序，新元素小于已排序元素，位置全部向后挪，最后新元素赋值给i
 * <p>
 * break:结束循环
 * continue:终止本次循环
 */
public class InsertSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sort);

        int[] a = new int[]{8, 1, 5, 4, 9, 2, 6, 3, 7};
        int len = a.length;//个数为9

        int[] res = insertSort1(a, len);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(res));//[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * 先一步找到插入元素的位置，然后再整体向后挪
     * <p>
     * 其它找位置办法：顺序循环，折半查找(遇到查找再来补充)
     */
    public static int[] insertSort(int[] arr, int len) {
        for (int i = 1; i < len; i++) {
            if (arr[i] >= arr[i - 1]) {
                continue;
            }
            int temp = arr[i];

            int j = i - 1;
            int index = 0;
            while (j >= 0) {
                if (arr[i] < arr[j]) {
                    index = j;
                }
                j--;
            }

            for (int k = i; k > index; k--) {
                arr[k] = arr[k - 1];
            }

            arr[index] = temp;
        }
        return arr;
    }

    /**
     * 顺序循环：比较元素的大小，一个一个往后挪
     *
     * @param arr
     * @param len
     * @return
     */
    public static int[] insertSort1(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}

