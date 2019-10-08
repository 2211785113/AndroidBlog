package com.example.ruru.sophiesblog.data_algorithm.datastructure.array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

/**
 * 顾客付5美元/10美元/20美元
 * 每杯5美元，开始手里0美元
 * 结果：正确找零返回true
 */
public class A860 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a860);

        int[] bills = new int[]{5, 5, 5, 10, 20};
        boolean res = lemonadeChange(bills);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 自己做：2ms  39.9MB
     */
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        int fiveSum = 0;//5美元的数量
        int tenSum = 0;//10美元的数量

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (bills[i] == 5) {
                fiveSum++;
            }

            if (bills[i] == 10) {
                tenSum++;
                if (fiveSum == 0)
                    flag = false;
                fiveSum--;
            }

            if (bills[i] == 20) {
                if (tenSum == 0) {
                    if (fiveSum < 3)
                        flag = false;
                    fiveSum -= 3;
                } else {
                    if (fiveSum < 1)
                        flag = false;
                    tenSum--;
                    fiveSum--;
                }
            }
        }
        return flag;
    }

    /**
     * 需细细体会。
     * 2ms  39.9MB
     */
    public boolean lemonadeChangeOne(int[] bills) {
        int x = 0;//5美元数量
        int y = 0;//10美元数量
        int n = bills.length;
        int i = 0;
        while (i < n && x >= 0 && y >= 0) {
            switch (bills[i]) {
                case 5:
                    x++;
                    break;
                case 10:
                    x--;
                    y++;
                    break;
                case 20:
                    if (y > 0) {
                        x--;
                        y--;
                    } else {
                        x -= 3;
                    }
                    break;
                default:
                    break;
            }
            i++;
        }
        return x < 0 || (x >= 0 && y < 0) ? false : true;//全部结束了之后进行判断，需要用到while进行判断，不是每一次结束之后进行判断
    }
}
