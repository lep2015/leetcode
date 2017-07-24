package org.lep.leetcode.simplifypath;

import java.util.Stack;

/**
 *
 * Source : https://oj.leetcode.com/problems/simplify-path/
 *
 * Created by lverpeng on 2017/7/24.
 *
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 */
public class SimplifyPath {

    /**
     * 简化unix形式的文件路径
     * 考虑几种特殊情况
     * 有多个斜杠
     * 只剩下根路径的情况
     * 路径中文件夹名称包含.
     *
     * @param path
     * @return
     */
    public String simplify (String path) {
        Stack<String> stack = new Stack<String>();
        stack.push("/");
        String[] paths = path.split("/");
        for (int i = 0; i < paths.length; i++) {
            String cur = paths[i];
            if (cur.equals("/") || cur.equals(".") || cur.equals("")) {
                continue;
            }
            if (cur.equals("..")) {
                if (stack.size() > 1) {
                    stack.pop();
                }
            } else {
                stack.push(cur);
            }
        }
        if (stack.size() == 0) {
            return "/";
        }
        String result = "";
        for (int i = 0; i < stack.size(); i++) {
            result += stack.get(i);
            if (stack.get(i).equals("/")) {
                continue;
            }
            result += "/";
        }
        if (result.length() == 1) {
            return result;
        }
        return result.substring(0, result.length() - 1);
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplify("/home/"));
        System.out.println(simplifyPath.simplify("/a/./b/../../c/"));
        System.out.println(simplifyPath.simplify("/../"));
        System.out.println(simplifyPath.simplify("/../a/b"));
        System.out.println(simplifyPath.simplify("/home//foo/"));
        System.out.println(simplifyPath.simplify("/home///foo/"));
        System.out.println(simplifyPath.simplify("/home/foo.bar/"));
        System.out.println(simplifyPath.simplify("/home/.bar/"));
    }
}
