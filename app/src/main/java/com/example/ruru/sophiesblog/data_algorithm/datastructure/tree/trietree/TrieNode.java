package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.trietree;

/**
 * 每个Trie树的结点
 */
public class TrieNode {

    public char data;
    public boolean isword = false;//判断从根节点到这个节点是否能组成一个单词
    public TrieNode[] next = new TrieNode[26];//一个长度为26的数组保存每个节点下的子节点位置

    public TrieNode(char data) {
        this.data = data;
    }
}
