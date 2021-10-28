package cn.czl.node.treenode.verify;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/10/28 17:27
 * @description:
 *      100. 相同的树
 *      - 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *      - 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *      示例 1：
 *          输入：p = [1,2,3], q = [1,2,3]
 *          输出：true
 *      示例 2：
 *          输入：p = [1,2], q = [1,null,2]
 *          输出：false
 *      示例 3：
 *          输入：p = [1,2,1], q = [1,1,2]
 *          输出：false
 *      提示：
 *          两棵树上的节点数目都在范围 [0, 100] 内
 *          -10^4 <= Node.val <= 10^4
 */
public class IsSameTree_Easy {

    /**
     * 借助栈 迭代比较
     * */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> pstack = new Stack<>();
        Stack<TreeNode> qstack = new Stack<>();
        pstack.add(p);
        qstack.add(q);
        while(!pstack.isEmpty() || !qstack.isEmpty()){
            TreeNode pTemp = pstack.pop();
            TreeNode qTemp = qstack.pop();
            if(pTemp == qTemp)  continue;
            if(pTemp == null || qTemp == null)  return false;
            if(pTemp.val != qTemp.val)  return false;
            pstack.add(pTemp.left);
            pstack.add(pTemp.right);
            qstack.add(qTemp.left);
            qstack.add(qTemp.right);
        }
        return pstack.isEmpty() && qstack.isEmpty();
    }

    /**
     * 递归比较
     * */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p == null && q == null)  return true;
        if(p == null || q == null)  return false;
        boolean flag = p.val == q.val;
        return flag && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
