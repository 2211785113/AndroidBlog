package com.example.ruru.sophiesblog.data_algorithm.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_sort);

        int[] a = new int[]{5, 2, 7, 3, 6, 1, 4};
        int len = a.length;//7
        int first = 0;//0
        int last = len - 1;//6
        int[] temp = new int[len];//7

        mergeSort(a, first, last, temp);
        Log.d(getClass().getName(), "onCreate: res=" + Arrays.toString(a));//[1, 2, 3, 4, 5, 6, 7]
    }

    /**
     * first=0,last=6,mid=3      mergeSort:0,3  4,6
     * first=0,last=3,mid=1      mergeSort:0,1  2,3
     * first=0,last=1,mid=0      mergeSort:0,0  1,1
     * first=0,last=0停止
     * <p>
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>原来：[5, 2, 7, 3, 6, 1, 4]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>左边排序：0-1，2-3，0-3>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * l= 0 mid=0 r=1
     * arr3= [2, 5, 7, 3, 6, 1, 4]
     * l= 2 mid=2 r=3
     * arr3= [2, 5, 3, 7, 6, 1, 4]
     * l= 0 mid=1 r=3
     * arr3= [2, 3, 5, 7, 6, 1, 4]
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>右边排序：4-5，4-6>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * l= 4 mid=4 r=5
     * arr3= [2, 3, 5, 7, 1, 6, 4]
     * l= 4 mid=5 r=6
     * arr3= [2, 3, 5, 7, 1, 4, 6]
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>合并排序：0-6>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * l= 0 mid=3 r=6
     * arr3= [1, 2, 3, 4, 5, 6, 7]
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>结果：onCreate: res=[1, 2, 3, 4, 5, 6, 7]>>>>>>>>>>>>>>>>>>>>
     *
     * @param a
     * @param first
     * @param last
     * @param temp
     */
    public void mergeSort(int[] a, int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) >> 1;
            mergeSort(a, first, mid, temp);    //左边有序
            mergeSort(a, mid + 1, last, temp); //右边有序
            mergeArray(a, first, mid, last, temp); //再将二个有序数列合并
        }
    }

    /**
     * 将两个有序数列a[first...mid]和a[mid...last]合并。
     *
     * @param a
     * @param l
     * @param mid
     * @param r
     */
    public void mergeArray(int[] a, int l, int mid, int r, int[] temp) {
        Log.d(getClass().getName(), "l= " + l + " mid=" + mid + " r=" + r);
        int i = l, j = mid + 1;
        int m = mid, n = r;
        int k = 0;

        while (i <= m && j <= n) {
            if (a[i] <= a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= m)
            temp[k++] = a[i++];

        while (j <= n)
            temp[k++] = a[j++];

        for (i = 0; i < k; i++)
            a[l + i] = temp[i];
        Log.d(getClass().getName(), "arr3= " + Arrays.toString(a));
    }
}
