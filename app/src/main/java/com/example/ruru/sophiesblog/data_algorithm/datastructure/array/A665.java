package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class A665 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a665);

        int[] nums1 = new int[]{4, 2, 1};

        boolean res = checkPossibility(nums1);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 1个数：true
     * 2个数：true
     * 3个数：count>1都为false，否则都为true
     * 4个数以上：count大于1为false，另一个条件例子：5 6 3 4 (6>3,5大于3或者6大于4就不行)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int n = 0;
        int sz = nums.length;
        for (int i = 0; i < sz - 1; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            if (a > b) {
                n++;
                if (n > 1) {
                    return false;
                }
                if (i - 1 > -1 && nums[i - 1] > b && i + 2 < sz && a > nums[i + 2])
                    return false;
            }
        }
        return true;
    }
}
