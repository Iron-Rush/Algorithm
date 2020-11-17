package cn.czl.node.treenode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2020/11/16 17:00
 * @description:
 *      297. 二叉树的序列化与反序列化
 *      - 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 *      进而可以将转换后的数据存储在一个文件或者内存中，
 *      同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *      - 请设计一个算法来实现二叉树的序列化与反序列化。
 *      这里不限定你的序列 / 反序列化算法执行逻辑，
 *      你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *      - 示例：
 *              1
 *             / \
 *            2   3
 *               / \
 *              4   5
 *      序列化为 "[1,2,3,null,null,4,5]"
 */
public class SerializeAndDeserialize_BinaryTree_Hard {
    @Test
    public void TestSolution(){
        String treeStr = "1,2,3,null,null,4,5,6,7";
        String treeStr2 = "1,2,3,null,null,4,5,null,null,null,null,6,7,null,null";
        TreeNode root = deserialize(treeStr);
        dfs(root);
        String res = serialize(root);
        System.out.println(res);
        root = deserialize(res);
        dfs(root);
    }

    /**
     * BFS-逐层生成/逐层序列化。
     * 执行用时：25 ms, 在所有 Java 提交中击败了45.24%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了54.34%的用户
     * */
    // 将 TreeNode 序列化为 字符串
    public String serialize(TreeNode root) {
        if (root == null){
            return "null";
        }
        StringBuilder sb = new StringBuilder(root.val + ",");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            StringBuilder tempStr = new StringBuilder();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode != null){
                    queue.add(curNode.left);
                    queue.add(curNode.right);
                    if (curNode.left != null){
                        tempStr.append(curNode.left.val + ",");
                    }else {
                        tempStr.append("null,");
                    }
                    if (curNode.right != null){
                        tempStr.append(curNode.right.val + ",");
                    }else {
                        tempStr.append("null,");
                    }
                }
            }
            sb.append(tempStr);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String serialize2(TreeNode root) {
        if (root == null){
            return "null";
        }
        StringBuilder sb = new StringBuilder(root.val + ",");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode != null){
                    queue.add(curNode.left);
                    queue.add(curNode.right);
                    sb.append(curNode.left != null ? curNode.left.val + "," : "null,");
                    sb.append(curNode.right != null ? curNode.right.val + "," : "null,");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // 将 字符串 反序列化 为 TreeNode
    public TreeNode deserialize(String data) {
        String[] dataList = data.split(",");
        if(!"null".equals(dataList[0])){
            TreeNode root = new TreeNode(Integer.parseInt(dataList[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int pos = 1;
            while (pos < dataList.length){
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curNode = queue.poll();
                    if(curNode != null){
                        if(!"null".equals(dataList[pos])){
                            curNode.left = new TreeNode(Integer.parseInt(dataList[pos]));
                        }
                        pos++;
                        if(!"null".equals(dataList[pos])){
                            curNode.right = new TreeNode(Integer.parseInt(dataList[pos]));
                        }
                        pos++;
                        queue.offer(curNode.left);
                        queue.offer(curNode.right);
                    }
                }
            }
            return root;
        }
        return null;
    }

    void dfs(TreeNode root){
        if(root != null){
            System.out.print(root.val + ",");
            dfs(root.left);
            dfs(root.right);
        }
    }
}
