package com.example.ruru.sophiesblog.data_algorithm.datastructure.stack;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;
import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;

public class A739 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a739);

        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = dailyTemperatures1(temperatures);
        Log.d(getClass().getName(), "onCreate:res= " + Arrays.toString(res));//[1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * 普通：314ms  47.1MB
     * 时间复杂度：T(N)=O(N^2)
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];//默认值都为0

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }

        return res;
    }

    /**
     * 堆栈：53ms，42.5MB
     * 时间复杂度：T(N)=O(N)
     * res打印顺序：
     * res[0]=1
     * res[1]=1
     * res[4]=1
     * res[3]=2
     * res[5]=1
     * res[2]=4
     * 具体过程：看笔记本推理。
     * 核心：栈中永远存放的是没有比它大的值的索引。
     */
    public int[] dailyTemperatures1(int[] T) {//[73, 74, 75, 71, 69, 72, 76, 73]
        int n = T.length;//8
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n; i++) {
            int top;
            while (!stack.empty() && T[i] > T[top = stack.peek()]) {

                res[top] = i - top;
                stack.pop();
            }
            stack.push(i);
        }

        return res;
    }
}


