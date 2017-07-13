package org.lep.leetcode.longestparentheses;

import sun.rmi.runtime.Log;

import java.util.Stack;

/**
 * Source : https://oj.leetcode.com/problems/longest-valid-parentheses/
 *
 * Created by lverpeng on 2017/7/13.
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *
 * Another example is ")()())", where the longest valid parentheses substring is "()()",
 * which has length = 4.
 *
 */
public class LongestParentheses {

    /**
     * 最长有效的括号字符串
     *
     * 和之前判断括号匹配的一样使用栈，因为要计算长度，所以栈里面放括号字符的序号
     *
     * 边界情况：
     * 栈为空遇到了右括号，开始压入一个-1，遇到右括号换的时候出栈，并压入新的右括号的序号
     *
     * @param parentheseStr
     * @return
     */
    public int longestParentheses (String parentheseStr) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0;
        stack.push(-1);
        for (int i = 0; i < parentheseStr.length(); i++) {
            if (parentheseStr.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.size() > 1) {
                    stack.pop();
                    int temp = stack.peek();
                    if (maxLen < (i - temp)) {
                        maxLen = i - temp;
                    }
                } else {
                    stack.pop();
                    stack.push(i);
                }
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {
        LongestParentheses longestParentheses = new LongestParentheses();
        System.out.println(longestParentheses.longestParentheses("(()"));
        System.out.println(longestParentheses.longestParentheses(")()())"));
    }
}
