package cn.czl.node.treenode.verify;

import java.util.Stack;

/**
 * @author RedRush
 * @date 2021/3/12 15:56
 * @description:
 *      331. 验证二叉树的前序序列化
 *      - 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *           _9_
 *          /   \
 *         3     2
 *        / \   / \
 *       4   1  #  6
 *      / \ / \   / \
 *      # # # #   # #
 *      - 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *      - 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *      - 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *      - 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 *      示例 1:
 *          输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *          输出: true
 *      示例 2:
 *          输入: "1,#"
 *          输出: false
 *      示例 3:
 *          输入: "9,#,#,1"
 *          输出: false
 */
public class VerifyPreorderSerializationOfBinaryTree_Normal {

    /**
     * 从后向前遍历，累计#数量，每遇见一个数字，减去两个#，并把当前数字变为#
     * 最后将根节点变为#，因此遍历结束时count == 1则满足条件
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 94.38% 的用户
     * */
    public boolean isValidSerialization(String preorder) {
        char[] chs = preorder.toCharArray();
        int i = preorder.length() - 1;
        int count = 0;  //记录#的个数
        for(; i >= 0; i--){
            if(chs[i] != ','){
                if(chs[i] == '#'){
                    count++;
                }else{
                    while(i >= 0 && chs[i] != ','){ // 该节点数字可能存在多位
                        i--;
                    }
                    if(count >= 2){     //#的个数>=2，消除2个#，消除一个节点数字并转换成#，即num-1
                        count--;
                    }else{
                        return false;   //#的个数不足2，证明false
                    }
                }
            }
        }
        return count == 1;  //最终#的个数须==1
    }

    /**
     * 栈，使用栈维护槽位变化
     * 每一个数字会消耗一个槽位，并产生两个新的槽位，每个'#'会消耗一个槽位。
     * 如果中途没有槽位/最后有多余槽位，则return false
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 38.91% 的用户
     * 内存消耗： 37.2 MB , 在所有 Java 提交中击败了 78.11% 的用户
     * */
    public boolean isValidSerialization2(String preorder) {
        int len = preorder.length();
        int i = 0;
        char[] chs = preorder.toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);  // 为根节点初始化槽位
        while (i < len){
            if(stack.isEmpty()){
                return false;
            }
            if(chs[i] == ','){
                i++;
            }else if(chs[i] == '#'){
                int top = stack.pop() - 1;
                if(top > 0){
                    stack.push(top);
                }
                i++;
            }else {
                while (i < len && chs[i] != ','){   // 读取一个数字
                    i++;
                }
                int top = stack.pop() - 1;
                if(top > 0){
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }
}
