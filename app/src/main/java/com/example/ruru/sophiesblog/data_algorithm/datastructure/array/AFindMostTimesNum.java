package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/eniac12/p/5296139.html
 */
public class AFindMostTimesNum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amost_times_num);

        int[] nums = new int[]{1, 3, 2, 4, 1, 3, 2};
        int res = findMostTimesNum(nums);
        Log.d(getClass().getName(), "onCreate:res=" + res);//3
    }

    /**
     * 时间复杂度：T(N)=3O(N)
     */
    private int findMostTimesNum(int[] nums) {

        Arrays.sort(nums);

        int[] count = new int[101];//计数数组，每个元素默认值为0
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            count[nums[i]]++;//对应计数值加1
        }

        int mCount = 0;//对应的次数
        int mNumber = count[0];//对应的数

        for (int i = 0; i < 101; i++) {//找出最多出现的次数
            if (count[i] > mCount)
                mCount = count[i];
        }

        for (int i = 0; i < 101; i++) {//如果不用break/continue/return退出，for循环101次循环都能执行完
            if (count[i] == mCount) {
                mNumber = i;
            }
        }

        return mNumber;
    }
}
