package com.example.ruru.sophiesblog.data_algorithm.datastructure.stack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Stack;

/**
 * 用栈实现队列:
 * 使用双栈的原因：
 * 能通过b栈将原a栈的数据倒置，此时栈的top，也就是队列的peek。可以实现队列的peek和pop操作。
 * 操作完再把b栈转换回a栈，这样当再push数据进栈的时候顺序才会正确。
 */
public class A232 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a232);

        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);

        Log.d(getClass().getName(), "onCreate:peek= " + queue.peek());//1
        Log.d(getClass().getName(), "onCreate:pop= " + queue.pop());//1

        Log.d(getClass().getName(), "onCreate:peek1= " + queue.peek());//2
        Log.d(getClass().getName(), "onCreate:pop1= " + queue.pop());//2

        Log.d(getClass().getName(), "onCreate:peek2= " + queue.peek());//3
        Log.d(getClass().getName(), "onCreate:pop2= " + queue.pop());//3

        Log.d(getClass().getName(), "onCreate:empty= " + queue.empty());//false

        queue.push(4);

        Log.d(getClass().getName(), "onCreate:peek4= " + queue.peek());//3
        Log.d(getClass().getName(), "onCreate:pop4= " + queue.pop());//3

    }

    /**
     * 栈的方法：push/pop/peek/empty/size
     */
    class MyQueue {

        private Stack<Integer> s1;
        private Stack<Integer> s2;

        private int front;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            s1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (s1.isEmpty() && s2.isEmpty()) {
                throw new RuntimeException("Queue is Empty");
            } else if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (s1.isEmpty() && s2.isEmpty()) {
                throw new RuntimeException("Queue is Empty");
            } else if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return s1.isEmpty();
        }
    }
}
