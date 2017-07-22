package org.lep.leetcode.textjustification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Source : https://oj.leetcode.com/problems/text-justification/
 *
 * Created by lverpeng on 2017/7/22.
 *
 * Given an array of words and a length L, format the text such that each line has
 * exactly L characters and is fully (left and right) justified.
 *
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 *
 * Return the formatted lines as:
 *
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Note: Each word is guaranteed not to exceed L in length.
 *
 *
 * Corner Cases:
 *
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */
public class TextJustification {

    /**
     * 格式化给定的单词串，每行长度固定
     *
     * @param words
     * @return
     */
    public String[] justify (String[] words, int length) {
        if (words.length < 1) {
            return words;
        }
        if (words.length == 1) {
            words[0] = words[0] + getSpace(length - words[0].length());
            return words;
        }
        int gap = 0;
        int lineLength = words[0].length();
        List<String> result = new ArrayList<String>();
        List<String> lineWords = new ArrayList<String>(){{add(words[0]);}};
        int index = 1;
        while (index < words.length) {
            int sum = gap + lineLength + words[index].length();
            if (sum >= length) {
                result.add(buildLine(lineWords, gap, length - gap - lineLength));
                gap = -1;
                lineLength = 0;
                lineWords.clear();
            }
            gap++;
            lineLength += words[index].length();
            lineWords.add(words[index]);
            index ++;
            // 最后一行
            if (index == words.length) {
                result.add(buildLine(lineWords, gap, length - gap - lineLength));
            }

        }
        return result.toArray(new String[result.size()]);
    }
    private String buildLine (List<String> words, int gap, int remain) {
        if (words.size() == 1) {
            return words.get(0) + getSpace(remain);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.size() - 1; i++) {
            int spaceLen = 1 + remain / gap;
            if (i < remain % gap) {
                spaceLen ++;
            }
            result.append(words.get(i) + getSpace(spaceLen));
        }
        result.append(words.get(words.size()-1));
        return result.toString();
    }

    private String getSpace (int n) {
        String space = "";
        for (int i = 0; i < n; i++) {
            space += " ";
        }
        return space;
    }


    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(Arrays.toString(textJustification.justify(words, 16)));
    }

}
