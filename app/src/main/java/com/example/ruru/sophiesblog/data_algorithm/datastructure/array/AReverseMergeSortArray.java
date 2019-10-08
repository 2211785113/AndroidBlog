package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

/**
 * 题目：
 * a,b是两个从小到大排序好的数组
 * reverseMergeSortArray：要求按从大到小的顺序合并a和b
 * 要求：时间复杂度位O(N)
 * <p>
 * 链接：https://www.kancloud.cn/maliming/leetcode/844245
 * 注意：&&比||优先级高
 */
public class AReverseMergeSortArray extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areverse_merge_sort_array);

        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{2, 3, 4, 5, 6, 7};
        int[] res = reverseMergeSortArray(a, b);
        Log.d(getClass().getName(), "onCreate:res= " + res);//[7,6,5,5,4,4,3,3,2,2,1]
    }

    /**
     * 正序
     */
    private int[] reverseMergeSortArray(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] c = new int[m + n];
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            c[k--] = a[i] > b[j] ? a[i--] : b[j--];
        }
        System.out.println("i=" + i + " j=" + j);
        //因为定了长度，所以最前面那两个值取了0
        while (i >= 0 || j >= 0) {
            c[k--] = i >= 0 ? a[i--] : b[j--];
        }
        return c;
    }

    /**
     * 倒序
     */
    private int[] reverseMergeSortArray1(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] c = new int[m + n];
        int i = m - 1, j = n - 1, k = 0;
        while (i >= 0 && j >= 0) {
            c[k++] = a[i] > b[j] ? a[i--] : b[j--];
        }
        while (i >= 0 || j >= 0) {
            c[k++] = i >= 0 ? a[i--] : b[j--];
        }
        return c;
    }

}
