package org.lep.leetcode.generateparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/generate-parentheses/
 *
 * Created by lverpeng on 2017/7/11.
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 */
public class GenerateParentheses {

    /**
     * 生成的括号个数是2n个（包括左括号和右括号），而且1-2n范围内左括号的个数一定大于右括号的个数
     *
     * @param n
     * @return
     */
    public List<String> generate (int n) {
        List<String> list = new ArrayList<String>();
        generate(n, n, list, "");
        return list;
    }

    /**
     * 使用深度优先算法
     *
     * @param left      左括号的个数
     * @param right     右括号的个数
     * @param result    最终字符串的保存在这里
     * @param str
     */
    private void generate (int left, int right, List<String> result, String str) {
        if (left == 0 && right == 0) {
            result.add(str);
        }
        if (left > 0) {
            generate(left - 1, right, result, str + "(");
        }

        // 维护括号配对的规则，现有左括号，才能有右括号
        if (left < right && right > 0) {
            generate(left, right - 1, result, str + ")");
        }
    }

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> list = generateParentheses.generate(2);
        System.out.println(Arrays.toString(list.toArray(new String[list.size()])));
    }


}
