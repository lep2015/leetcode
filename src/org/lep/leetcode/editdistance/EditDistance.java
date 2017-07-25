package org.lep.leetcode.editdistance;

/**
 * Source : https://oj.leetcode.com/problems/edit-distance/
 *
 * Created by lverpeng on 2017/7/25.
 *
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {

    /**
     * 计算出从一个单词变到另一个单词的最少步数，也就是最短距离，只能使用插入、删除、替换操作
     *
     * 考虑两个单词abc,bbcd，dp[i][j]表示word1的前i个字符变到word2的前j个字符，所需要的步数，""表示空串
     *    "" a b c
     * "" 0  1 2 3
     * b  1  1 1 2
     * b  1  1 1 2
     * c  3  3 2 1
     * d  4  4 3 2
     *
     * 从上面的演算可以看出
     * 当word1[i] == word2[j]的时候，dp[i][j] = dp[i-1][j-1]
     * 当word1[i] != word2[j]的时候，dp[i][j] = 其左边、左上方、正上方三个数字中最小的那一个加1
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minimumDistance (String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }


    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minimumDistance("", "abc"));
        System.out.println(editDistance.minimumDistance("b", "abc"));
        System.out.println(editDistance.minimumDistance("bb", "abc"));
        System.out.println(editDistance.minimumDistance("bbc", "abc"));
        System.out.println(editDistance.minimumDistance("bbcd", "abc"));
    }
}
