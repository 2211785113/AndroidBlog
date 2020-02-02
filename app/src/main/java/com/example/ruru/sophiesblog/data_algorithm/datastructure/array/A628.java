package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class A628 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a628);

        int[] nums = new int[]{1, 2, 3, 4};
        int res = maximumProduct(nums);
        Log.d(getClass().getName(), "onCreate:res=" + res);
    }

    /**
     * 18ms  38.2MB
     * 四种情况：
     * 三正：最后三个数
     * 两负一正：前两个数与最后一个数
     * 两正一负：最后三个数，因为只有一个负。
     * 三负：不可能最大。
     * 然后比较得出最大的即可。
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int s1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int s2 = nums[0] * nums[1] * nums[n - 1];
        return s1 > s2 ? s1 : s2;
    }

    /**
     * 18ms 40MB
     */
    public int maximumProduct1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
}
