package org.lep.leetcode.zigzag;

/**
 * Source : https://oj.leetcode.com/problems/zigzag-conversion/
 *
 * Created by lverpeng on 2017/6/29.
 *
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string text, int nRows);
 *
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 *
 */
public class ZigzagConvertion {

    /**
     * 将给定的字符串转换为指定行数的锯齿状，然后按行输出
     * 找出规律：
     * 第一行和最后一行，数组下标相差2 * nRows - 2
     * 中间的行：数组下表相差2 * nRows - 2 - 2 * i，i表示第i行
     * 要注意下标越界判断
     *
     * @param text
     * @param nRows
     * @return
     */
    public String convert (String text, int nRows) {
        int n = text.length();
        int base = 2 * nRows-  2;
        int index = 0;

        if (nRows == 1) {
            System.out.println(text);
        }

        String out = "";
        for (int i = 0; i < nRows; i++) {
            index = i;
            if (i == 0 || i == nRows - 1) {
                while (index < n) {
                    out += text.charAt(index);
                    index += base;
                }
            } else {
                while (index < n) {
                    out += text.charAt(index);
                    index += base - 2 * i;
                }
            }
        }
        return out;
    }

    /**
     * 实现准备好nRows的数组，遍历字符串，依次判断字符落在那一行，也就是哪一个数组
     *
     * @param text
     * @param nRows
     * @return
     */
    public String convertByIndex (String text, int nRows) {
        if(text.length() <= 1 || text.length() == nRows) {
            return text;
        }
        String[] lines = new String[nRows];
        int row = 0;
        int step = 0;
        for (int i = 0; i < text.length(); i++) {
            if (row == 0) {
                // row需要增加
                step = 1;
            }
            if (row == nRows - 1) {
                // row需要往回退
                step = -1;
            }
            lines[row] = lines[row] == null ? "" : lines[row];
            lines[row] += text.charAt(i);
            row += step;
        }

        String out = "";
        for (String line : lines) {
            out += line;
        }
        return out;
    }

    public static void main(String[] args) {
        ZigzagConvertion zigzagConvertion = new ZigzagConvertion();
        System.out.println(zigzagConvertion.convert("PAYPALISHIRING", 3));
        System.out.println(zigzagConvertion.convertByIndex("PAYPALISHIRING", 3));
    }

}
