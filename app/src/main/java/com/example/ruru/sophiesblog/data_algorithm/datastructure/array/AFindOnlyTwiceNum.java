package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 链接：
 * https://blog.csdn.net/cncnlg/article/details/29862905
 * https://blog.csdn.net/m0_37925202/article/details/81415825
 * <p>
 * 第三种：求和方式：不合适。
 * 第四种：位运算：不合适。
 */
public class AFindOnlyTwiceNum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afind_only_two_times_num);

        int[] nums = new int[]{100, 121, 4433, 10000, 121, 10};
        int res = findRepeatedNumber(nums);
        Log.d(getClass().getName(), "onCreate:res=" + res);//121
    }

    /**
     * 第一种：
     * 时间复杂度：T(N)=O(N)
     * 优化：降到O(logN)
     * 缺点：消耗内存空间过大
     */
    private int findRepeatedNumber(int[] nums) {
        int n = nums.length;
        int[] count = new int[10001];//计数数组，默认都为0
        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
            if (count[nums[i]] == 2)
                return nums[i];
        }
        return -1;
    }

    /**
     * 第二种：
     * 时间复杂度：O(N)
     * 用空间换取时间
     */
    private int findRepeatedNumber1(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                Integer cnt = map.get(nums[i]);
                map.put(nums[i], cnt++);
                if (map.containsValue(2)) {
                    return nums[i];
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return -1;
    }
}
