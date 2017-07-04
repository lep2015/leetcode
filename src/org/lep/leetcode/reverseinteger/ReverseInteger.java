package org.lep.leetcode.reverseinteger;

/**
 * Source : https://oj.leetcode.com/problems/reverse-integer/
 *
 * Created by lverpeng on 2017/7/4.
 *
 * Reverse digits of an integer.
 *
 * Example1: x =  123, return  321
 * Example2: x = -123, return -321
 *
 *
 * Have you thought about this?
 *
 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 *
 * > If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 *
 * > Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
 *   then the reverse of 1000000003 overflows. How should you handle such cases?
 *
 * > Throw an exception? Good, but what if throwing an exception is not an option?
 *   You would then have to re-design the function (ie, add an extra parameter).
 *
 */
public class ReverseInteger {

    /**
     * 翻转一个int类型的数，几种特殊情况考虑
     * 1. 末尾是零的处理方式：去掉
     * 2. 溢出：一种方式注解返回0，一种是当做无符号int输出
     *
     * 从右至左取出每一位数，然后组成新的数
     * 取余，整除的方法
     *
     * @param num
     * @return
     */
    public int reverse (int num) {
        int temp = 0;
        int result = 0;
        while (num != 0) {
            // 判断溢出
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                // 溢出返回0
                return 0;
            }
            // 最后一位数
            temp = num % 10;
            result = result * 10 + temp;
            // 去除最后一位
            num = num / 10;
        }
        return result;
    }

    /**
     * 溢出不返回0，返回翻转后的数，使用long表示
     * @param num
     * @return
     */
    public long reverse1 (int num) {
        int temp = 0;
        long result = 0;
        while (num != 0) {
            // 最后一位数
            temp = num % 10;
            result = result * 10 + temp;
            // 去除最后一位
            num = num / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(123) + " === " + "321");
        System.out.println(reverseInteger.reverse(-123) + " === " + "-321");
        System.out.println(reverseInteger.reverse(-100) + " === " + "-1");
        System.out.println(reverseInteger.reverse(1002) + " === " + "2001");
        System.out.println(reverseInteger.reverse(123) + " === " + "321");


        System.out.println(reverseInteger.reverse(1463847412) + " === " + "2147483641");
        System.out.println(reverseInteger.reverse(2147447412) + " === " + "2147447412");
        System.out.println(reverseInteger.reverse(2147447412) + " === " + "2147447412");

        // overfow
        System.out.println(reverseInteger.reverse(1000000003) + " === " + "0");
        System.out.println(reverseInteger.reverse(2147483647) + " === " + "0");
        System.out.println(reverseInteger.reverse(-2147483648) + " === " + "0");

        System.out.println("");
        System.out.println("-------------");
        System.out.println("");

        //
        System.out.println(reverseInteger.reverse1(123) + " === " + "321");
        System.out.println(reverseInteger.reverse1(-123) + " === " + "-321");
        System.out.println(reverseInteger.reverse1(-100) + " === " + "-1");
        System.out.println(reverseInteger.reverse1(1002) + " === " + "2001");
        System.out.println(reverseInteger.reverse1(123) + " === " + "321");

        //
        System.out.println(reverseInteger.reverse1(1463847412) + " === " + "2147483641");
        System.out.println(reverseInteger.reverse1(2147447412) + " === " + "2147447412");
        System.out.println(reverseInteger.reverse1(2147447412) + " === " + "2147447412");

        // overflow
        System.out.println(reverseInteger.reverse1(1000000003) + " === " + "3000000001");
        System.out.println(reverseInteger.reverse1(2147483647) + " === " + "7463847412");
        System.out.println(reverseInteger.reverse1(-2147483648) + " === " + "-8463847412");
    }
}
