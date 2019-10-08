package com.example.ruru.sophiesblog.data_algorithm.algorithm.other.string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.HashSet;
import java.util.Set;

/**
 * 数字累加
 * 例子：2，22，222...
 * int temp = 0;
 * int a = 2;
 * for(int i = 0;i<5;i++){
 * temp = temp*10 + a;
 * }
 * <p>
 * 例子：a，aa，aaa...
 * String res = "";
 * for(int i = 0;i<5;i++){
 * res += "a";
 * }
 */
public class A824 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a824);

        String s = "I speak Goat Latin";
        String res = getGoatLatinOne(s);
        Log.d(getClass().getName(), "onCreate:res= " + res);//"Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
    }

    /**
     * 我的解法：15ms
     */
    private String getGoatLatinOne(String s) {
        String result = "";
        String[] splitArray = s.split(" ");
        for (int i = 0, len = splitArray.length; i < len; i++) {
            String word = "";
            String first = splitArray[i].substring(0, 1);
            if (first.equalsIgnoreCase("a") || first.equalsIgnoreCase("e") || first.equalsIgnoreCase("i") || first.equalsIgnoreCase("o") || first.equalsIgnoreCase("u")) {
                word += splitArray[i] + "ma";
            } else {
                word += splitArray[i].substring(1) + first + "ma";
            }
            for (int j = 0; j < i + 1; j++) {
                word += "a";
            }
            result += word + " ";
        }
        return result.trim();
    }

    /**
     * 别人的解法：1ms
     */
    private String getGoatLatinTwo(String s) {
        String result = "";
        int i = 0, j = 0;
        Set<Character> vowels = new HashSet<Character>();
        for (char c : "aeiouAEIOU".toCharArray()) {
            vowels.add(c);
        }

        for (String w : s.split("\\s")) {
            result += ' ' + ((vowels.contains(w.charAt(0))) ? w : w.substring(1) + w.charAt(0) + "ma");
            for (j = 0, ++i; j < i; ++j) {
                result += "a";
            }
        }
        return result.substring(1);
    }
}

