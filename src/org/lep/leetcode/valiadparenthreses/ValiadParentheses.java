package org.lep.leetcode.valiadparenthreses;

import java.util.Stack;

/**
 * Source : https://oj.leetcode.com/problems/valid-parentheses/
 *
 * Created by lverpeng on 2017/7/11.
 *
 * * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 *
 */
public class ValiadParentheses {

    /**
     * 括弧匹配，使用栈进行判断，遇到左括号入栈，遇到右括号判断出栈，如果匹配则出栈，不匹配返回
     * 最后判断是否匹配完成，并判断栈是否为空
     *
     * @param str
     * @return
     */
    public boolean valiad (String str) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i  < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                char pop = stack.pop();
                if (!((pop == '(' && c == ')') || (pop == '[' && c == ']') || (pop == '{' && c == '}'))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        ValiadParentheses valiadParentheses = new ValiadParentheses();
        System.out.println("true-------" + valiadParentheses.valiad("{}[]()"));
        System.out.println("true-------" + valiadParentheses.valiad("{[()]}[]()"));
        System.out.println("true-------" + valiadParentheses.valiad("{}[({})]()"));
        System.out.println("false-------" + valiadParentheses.valiad("{}[[()]"));
        System.out.println("false-------" + valiadParentheses.valiad("{}[][(]"));
        System.out.println("false-------" + valiadParentheses.valiad("{}2[][]()[]"));

    }


}
