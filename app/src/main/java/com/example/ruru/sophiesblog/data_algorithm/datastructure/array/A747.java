package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 找出最大元素，并判断最大元素是否>=其它元素的2倍，如果>=返回最大元素索引，否则返回-1。
 */
public class A747 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a747);

        int[] nums = new int[]{3, 6, 7, 0};
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{12, 74, 94, 2, 3};
        int[] nums3 = new int[]{12, 10, 94, 2, 3};
        int res = dominantIndex2(nums2);
        Log.d(getClass().getName(), "onCreate: res=" + res);
    }

    /**
     * 普通：1ms  34.5MB
     * 时间复杂度：T(N)=3O(N)
     * 找出最大元素，并判断是否是其它元素的2倍。
     */
    public int dominantIndex(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[max])
                max = i;
        }

        for (int i = 0; i < max; i++) {
            if (nums[max] < 2 * nums[i])
                return -1;
        }

        for (int i = max + 1; i < n; i++) {
            if (nums[max] < 2 * nums[i])
                return -1;
        }

        return max;
    }

    /**
     * 巧妙：2ms  34.6MB
     * 时间复杂度：T(N)=O(N)
     * 比较次小于这个元素的数。如果>=次最大元素，则其它元素肯定都大于。
     */
    private int dominantIndex1(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[max])
                max = i;
        }

        Arrays.sort(nums);//从小到大进行排序
        if (n == 1) {//特殊情况一定要进行判断
            return 0;
        } else {
            return nums[n - 1] >= 2 * nums[n - 2] ? max : -1;
        }
    }

    /**
     * 巧妙：一个循环。
     * 利用了n+mIndex的动态变化和求余%先判断最大值后边的值再判断最大值前边的值。
     * 关键代码：
     * while (i < n + mIndex) {
     * int m = i % n;
     * 1ms  34.6MB
     */
    private int dominantIndex2(int[] nums) {
        int n = nums.length;//数组长度=5
        int mValue = -1;//最大值
        int mIndex = -1;//最大值的索引
        int cnt = 0;//统计个数

        if (n == 1)
            return 0;//特殊情况要进行判断

        int i = 0;
        while (i < n + mIndex) {
            int m = i % n;//注意这里是求余，找到最大值会先判断最大值后边的，再判断最大值前边的。
            if (nums[m] > mValue) {
                mValue = nums[m];
                cnt = 0;
                mIndex = m;
            } else {
                if (mValue >= 2 * nums[m]) {
                    Log.d(getClass().getName(), "dominantIndex2: m==" + m);
                    cnt++;
                }
            }
            i++;
        }
        Log.d(getClass().getName(), "dominantIndex2: cnt==" + cnt + " n==" + n);
        return cnt == n - 1 ? mIndex : -1;
    }
}
