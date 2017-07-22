package org.lep.leetcode.addbinary;

/**
 * Source : https://oj.leetcode.com/problems/add-binary/
 *
 * Created by lverpeng on 2017/7/22.
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
public class AddBinary {

    public String add (String binary1, String binary2) {
        StringBuilder result = new StringBuilder();
        int length = binary1.length() > binary2.length() ? binary2.length() : binary1.length();
        int carry = 0;
        for (int i = length - 1; i > -1; i--) {
            int sum = Integer.parseInt(binary1.substring(i, i+1)) + Integer.parseInt(binary2.substring(i, i+1)) + carry;
            carry = sum / 2;
            result.insert(0, sum % 2);
        }
        String longerStr = "";
        if (binary1.length() > binary2.length()) {
            longerStr = binary1;
        } else {
            longerStr = binary2;
        }
        for (int i = longerStr.length() - 1; i >= length; i--) {
            int sum = Integer.parseInt(longerStr.substring(i, i+1)) + carry;
            carry = sum / 2;
            result.insert(0, sum % 2);

        }
        if (carry > 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.add("", ""));
        System.out.println(addBinary.add("1", ""));
        System.out.println(addBinary.add("1", "1"));
        System.out.println(addBinary.add("10", "11"));
    }
}
