package com.example.ruru.sophiesblog.data_algorithm.mathmatics;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 关键：
 * 1.相反数的解是一样的，所以写绝对值。
 * 2.逼近target，换方向的条件：
 * 如果sum-target的差值是偶数，那么可以换方向到达。
 * 如果sum-target的差值是奇数，则不可以换方向。则一直向前走，直到可以换方向到达。
 *
 * 因为：换方向：sum-target=2*step
 */
public class A754 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a754);

        int target = 2;
        int res = reachNumber(target);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    private int reachNumber(int target) {
        target = Math.abs(target);//都转为正数，结果一样
        int sum = 0;//逼近最后的数
        int num = 0;//移动了多少次
        while (sum < target) {
            num++;
            sum += num;
        }
        while (((sum - target) & 1) == 1) {//如果为奇数就继续执行，偶数退出循环
            num++;
            sum += num;
        }
        return num;
    }
}
