package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.CreateTree.strToTree;

/**
 * 深度优先搜索DFS等价于先序遍历
 */
public class A652 extends AppCompatActivity {

    private HashMap<String, Integer> map = new HashMap<>();
    private List<TreeNode> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a652);

        String[] str = new String[]{"1", "2", "3", "4", null, "2", "4", null, null, "4"};
        TreeNode node = strToTree(str);

        List<TreeNode> res = findDuplicateSubtrees(node);
        Log.d(getClass().getName(), "onCreate: res=" + res);//[[4],[2,4]]
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode node) {
        dfs(node);
        return list;
    }

    //递归获取每个子树的路径，保存于Map中
    private String dfs(TreeNode<String> node) {
        if (node == null)
            return "";
        //自底向上获取每个节点的序列化值
        String treeStr = node.value + "," + dfs(node.left) + "," + dfs(node.right) + ",";
        //将结果放入map，判断是否有相同子树
        //避免出现多次相同子树
        if (map.containsKey(treeStr) && map.get(treeStr) == 1) {//第一个条件可以改为map.get(treeStr)!=null
            list.add(node);
        }
        map.put(treeStr, map.get(treeStr) == null ? 1 : map.get(treeStr) + 1);//第二个参数可以改为map.getOrDefault(route, 0) + 1
        return treeStr;
    }
}
