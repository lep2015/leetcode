package org.lep.leetcode.wordsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/word-search/
 *
 * Created by lverpeng on 2017/7/28.
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 *   ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"]
 * ]
 *
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 */
public class WordSearch {

    public boolean search (List<String> board, String word) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length(); j++) {
                char ch = board.get(i).charAt(j);
                if (ch == word.charAt(0)) {
                    int[][] searchedFlag = new int[board.size()][board.get(i).length()];
                    for (int k = 0; k < board.size(); k++) {
                        Arrays.fill(searchedFlag[k], 0);
                    }
                    if (exist(board, i, j, word, 0, searchedFlag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 递归就是每一次做的事情一样，所以才会自己调用自己
     * 所以分析的时候可以先把一个元素做的事情列出来
     * 该元素做的事情完成之后，传入下一个元素
     * 恢复之前的状态
     *
     * @param board
     * @param i
     * @param j
     * @param word
     * @param index
     * @param searchedFlag
     * @return
     */
    public boolean exist (List<String> board, int i, int j, String word, int index, int[][] searchedFlag) {
        if (word.charAt(index) == board.get(i).charAt(j) && searchedFlag[i][j] == 0) {
            searchedFlag[i][j] = 1;
            if (index+1 == word.length()) {
                return true;
            }
            // 判断当前匹配字符上、右、下、左有没有匹配
            if (i > 0 && exist(board, i-1, j, word, index + 1, searchedFlag)
                    || j < board.get(i).length()-1 && exist(board, i, j+1, word, index+1, searchedFlag)
                    || i < board.size()-1 && exist(board, i+1, j, word, index+1, searchedFlag)
                    || j > 0 && exist(board, i, j-1, word, index+1, searchedFlag)) {
                return true;
            }
            searchedFlag[i][j] = 0;
        }
        return false;
    }


    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        List<String> list = new ArrayList<String>(){{
            add("ABCE");
            add("SFCS");
            add("ADEE");
        }};

        System.out.println(wordSearch.search(list, "ABCCED"));
        System.out.println(wordSearch.search(list, "SEE"));
        System.out.println(wordSearch.search(list, "ABCB"));
    }

}
