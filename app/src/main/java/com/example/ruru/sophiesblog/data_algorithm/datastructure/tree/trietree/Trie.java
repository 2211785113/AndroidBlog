package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.trietree;

/**
 * Trie树
 */
public class Trie {

    TrieNode node = new TrieNode('/');

    /**
     * 用来插入一个string
     *
     * @param s
     */
    public void insert(String s) {
        char[] arr = s.toCharArray();
        TrieNode p = node;
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            if (p.next[index] == null) {
                TrieNode trieNode = new TrieNode(arr[i]);
                p.next[index] = trieNode;
            }
            p = p.next[index];
        }
        p.isword = true;
    }

    /**
     * 用来判断这个字符串是不是由其他节点依次加一个字符组成
     *
     * @param s
     * @return
     */
    public boolean isBuild(String s) {
        char[] arr = s.toCharArray();
        TrieNode p = node;
        for (int i = 0; i < arr.length; i++) {
            if (!p.next[arr[i] - 'a'].isword) return false;
            p = p.next[arr[i] - 'a'];
        }
        return true;
    }
}
