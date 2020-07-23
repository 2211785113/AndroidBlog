package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 未经排序整数数组，最长且连续的递增序列长度。
 */
public class A674 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a674);

        int[] nums = new int[]{1, 3, 5, 4, 7};
        int[] nums1 = new int[]{2, 2, 2, 2, 2};
        int[] nums2 = new int[]{5, 4, 3, 2, 1};
        int res = findLengthOfLCIS(nums2);
        Log.d(getClass().getName(), "onCreate:res=" + res);//3
    }

    /**
     * 自己做的非常棒。
     * 1ms  38.1MB
     */
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int cnt = 1;
        int n = nums.length;

        if (n == 0)
            return 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt > max)
                max = cnt;
        }
        return max;
    }

    public int findLengthOfLCIS1(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;

        int max = 1;
        for (int i = 1; i < n; i++) {
            int cnt = 1;
            //while循环：要用到cnt变量，要终止循环。
            while (i < n && nums[i] > nums[i - 1]) {
                cnt++;
                i++;
            }
            max = Math.max(cnt, max);
        }
        return max;
    }
}
