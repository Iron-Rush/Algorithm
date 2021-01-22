package cn.czl.search.map;

import java.util.*;

/**
 * @author RedRush
 * @date 2021/1/18 9:20
 * @description:
 *      721. 账户合并
 *      - 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *      - 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *      - 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 *      示例 1：
 *          输入：accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
 *                      ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 *          输出：
 *              [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *              ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 *          解释：
 *              第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 *              第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 *              可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 *              ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 *      提示：
 *          accounts的长度将在[1，1000]的范围内。
 *          accounts[i]的长度将在[1，10]的范围内。
 *          accounts[i][j]的长度将在[1，30]的范围内。
 */
public class AccountsMerge_Normal {

    /**
     * 执行用时： 34 ms , 在所有 Java 提交中击败了 90.74% 的用户
     * 内存消耗： 44.1 MB , 在所有 Java 提交中击败了 23.06% 的用户
     * */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailCount = 0;
        // 为每个邮箱编码
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email, emailCount++);
                    emailToName.put(email, name);
                }
            }
        }
        // 初始化并查集，关联邮箱
        UnionSearch us = new UnionSearch(emailCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                us.union(nextIndex, firstIndex);
            }
        }
        // 遍历全部邮箱，根据父邮箱进行分组
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int parent = us.search(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(parent, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(parent, account);
        }
        // 将分组后的邮箱，添加至结果集
        List<List<String>> res = new LinkedList<>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            res.add(account);
        }
        return res;
    }
}

