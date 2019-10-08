package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.trietree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

public class A720 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a720);
    }


    public String longestWord(String[] words) {

        String longestword = "";
        int longlen = 0;

        Trie trie = new Trie();

        for (String word : words) {                                     //依次插入string
            trie.insert(word);
        }

        for (String word : words) {
            if (trie.isBuild(word) && word.length() > longlen) {         //遍历后发现有更长的string满足条件，进行替换
                longestword = word;
                longlen = word.length();
            } else if (trie.isBuild(word) && word.length() == longlen) {     //当出现一样长的字符串时，进行比较
                char[] longestchar = longestword.toCharArray();
                char[] wordchar = word.toCharArray();
                for (int i = 0; i < longlen; i++) {
                    if (wordchar[i] - longestchar[i] < 0) {
                        longestword = word;
                        break;
                    } else if (wordchar[i] - longestchar[i] > 0) break;
                }
            }
        }
        return longestword;
    }
}
