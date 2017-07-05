package org.lep.leetcode.intpalindrome;

import org.lep.leetcode.parseint.IntegerParser;

/**
 * Source : https://oj.leetcode.com/problems/palindrome-number/
 *
 * Created by lverpeng on 2017/7/5.
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * Some hints:
 *
 * Could negative integers be palindromes? (ie, -1)
 *
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 *
 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
 * you know that the reversed integer might overflow. How would you handle such case?
 *
 * There is a more generic way of solving this problem.
 *
 */
public class IntPalindrome {

    /**
     * 负数可以自己决定是否进行判断，这里不进行判断，归为不是
     * 依次取出整数的第一位和最后一位比较
     * 第一位：整除
     * 最后一位：对10求余
     *
     * @param num
     * @return
     */
    public boolean isPalindrome (int num) {
        if (num < 0) {
            return false;
        }
        int base = 1;
        while (num / (base) >= 10) {
            base *= 10;
        }

        while (num != 0) {
            int left = num / base;
            int right = num % 10;
            if (left != right) {
                return false;
            }
            num = (num % base) / 10;
            base /= 100;
        }
        return true;
    }


    public static void main(String[] args) {
        IntPalindrome palindrome = new IntPalindrome();
        System.out.println(palindrome.isPalindrome(0));
        System.out.println(palindrome.isPalindrome(-101));
        System.out.println(palindrome.isPalindrome(1001));
        System.out.println(palindrome.isPalindrome(1234321));
        System.out.println(palindrome.isPalindrome(2147447412));
        System.out.println(palindrome.isPalindrome(5155));
        System.out.println(palindrome.isPalindrome(5552));
    }
}
