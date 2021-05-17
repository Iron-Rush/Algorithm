package cn.czl.node.treenode.verify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author RedRush
 * @date 2021/5/17 15:55
 * @description:
 *      993. 二叉树的堂兄弟节点
 *      - 二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *      - 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 *      - 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 *      - 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *      示例 1：
 *          输入：root = [1,2,3,4], x = 4, y = 3
 *          输出：false
 *      示例 2：
 *          输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 *          输出：true
 *      示例 3：
 *          输入：root = [1,2,3,null,4], x = 2, y = 3
 *          输出：false
 *      提示：
 *          二叉树的节点数介于 2 到 100 之间。
 *          每个节点的值都是唯一的、范围为 1 到 100 的整数。
 */
public class ConsinsInBinaryTree_Easy {

    /**
     * BFS - 广度优先搜索
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 63.53% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 27.84% 的用户
     * */
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        int preDepth = -1;
        int depth = 0;
        TreeNode preParent = null;
        if(root.val == x || root.val == y){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.poll();
                if(curNode.left != null){
                    if(curNode.left.val == x || curNode.left.val == y){
                        if(preDepth == -1){
                            preParent = curNode;
                            preDepth = depth;
                        }else{
                            return (curNode != preParent) && (preDepth == depth);
                        }
                    }
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    if(curNode.right.val == x || curNode.right.val == y){
                        if(preDepth == -1){
                            preParent = curNode;
                            preDepth = depth;
                        }else{
                            return (curNode != preParent) && (preDepth == depth);
                        }
                    }
                    queue.add(curNode.right);
                }
            }
            depth++;
        }
        return false;
    }

    /**
     * BFS - 广度优先搜索 [优化]
     * 每层遍历结束时，如果仅找到答案，则无解
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 97.19% 的用户
     * */
    public boolean isCousins2(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        int preDepth = -1;
        int depth = 0;
        TreeNode preParent = null;
        if(root.val == x || root.val == y){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.poll();
                if(curNode.left != null){
                    if(curNode.left.val == x || curNode.left.val == y){
                        if(preDepth == -1){
                            preParent = curNode;
                            preDepth = depth;
                        }else{
                            return (curNode != preParent) && (preDepth == depth);
                        }
                    }
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    if(curNode.right.val == x || curNode.right.val == y){
                        if(preDepth == -1){
                            preParent = curNode;
                            preDepth = depth;
                        }else{
                            return (curNode != preParent) && (preDepth == depth);
                        }
                    }
                    queue.add(curNode.right);
                }
            }
            if(preDepth != -1){
                return false;
            }
            depth++;
        }
        return false;
    }


    /**
     * BFS - 广度优先搜索[优化2]
     * 以层为单位遍历，如果找到两个非'同父节点'的解
     * 则有解，否则无解
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.9 MB , 在所有 Java 提交中击败了 94.57% 的用户
     * */
    public boolean isCousins3(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        int ansCount = 0;
        int depth = 0;
        if(root.val == x || root.val == y){
            return false;
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.poll();
                boolean flag = true;
                if(curNode.left != null){
                    if(curNode.left.val == x || curNode.left.val == y){
                        if(ansCount == 1){
                            return true;
                        }
                        ansCount++;
                        flag = false;
                    }
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    if(curNode.right.val == x || curNode.right.val == y){
                        if(ansCount == 1){
                            return flag;
                        }
                        ansCount++;
                    }
                    queue.add(curNode.right);
                }
            }
            if(ansCount == 1){
                return false;
            }
            depth++;
        }
        return false;
    }


    /**
     * DFS - 深度优先搜索
     * 对每个找到的节点[深度、父节点]做单独记录；返回比较结果
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 99.81% 的用户
     * */
    // x, y的记录信息
    int x, y;
    int xDepth, yDepth;
    boolean xFound = false, yFound = false;
    TreeNode xParent, yParent;

    public boolean isCousins4(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(0, root, null);
        return xDepth == yDepth && xParent != yParent;
    }
    // 深度优先搜索递归函数
    public void dfs(int depth, TreeNode node, TreeNode parent){
        if(node == null){
            return;
        }
        // 识别当前节点是否符合要求
        if(node.val == x){
            xParent = parent;
            xDepth = depth;
            xFound = true;
        }else if (node.val == y){
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        // 剪枝， 递归搜索
        if (xFound && yFound){
            return;
        }
        dfs(depth + 1, node.left, node);
        if (xFound && yFound){
            return;
        }
        dfs(depth + 1, node.right, node);
    }

}
