package org.lep.leetcode.pow;

/**
 * Source : https://oj.leetcode.com/problems/powx-n/
 *
 * Created by lverpeng on 2017/7/18.
 *
 * Implement pow(x, n).
 *
 */
public class Pow {

    /**
     * 实现x^n
     * 考虑n为负数
     * 当n为偶数的时候，计算x*x，以期让n扩收缩
     *
     * @param x
     * @param n
     * @return
     */
    public double pow (int x, int n) {
        boolean sign = true;        // true：正数
        if (n < 0) {
            n = -n;
            sign = false;
        }
        double result = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= x;
            }
            n = n >> 1;
            x *= x;
        }

        return sign ? result : 1.0 / result;
    }

    public static void main(String[] args) {
        Pow pow = new Pow();
        System.out.println(pow.pow(2, 3));
        System.out.println(pow.pow(2, 10));
        System.out.println(pow.pow(2, -3));
    }
}
