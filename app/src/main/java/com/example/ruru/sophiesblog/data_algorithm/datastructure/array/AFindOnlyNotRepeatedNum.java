package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

public class AFindOnlyNotRepeatedNum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afind_only_not_repeated_num);

        int[] nums = new int[]{2, 3, 2, 4, 4};
        int res = findOnlyNotRepeatedNum(nums);
        Log.d(getClass().getName(), "onCreate:res= " + res);//3
    }

    /**
     * 思想：位运算之异或。
     */
    private int findOnlyNotRepeatedNum(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
