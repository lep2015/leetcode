package org.lep.leetcode.sqrt;

/**
 * Source : https://oj.leetcode.com/problems/sqrtx/
 *
 * Created by lverpeng on 2017/7/24.
 *
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 */
public class Sqrt {

    /**
     * 求x的平方根，这里要求的是整数
     * 使用试乘法（可能存在大数乘法，会溢出）、或者试除法
     * 这里使用试乘法，可以通过二分法来快速收敛
     * 使用试除法可以避免大数乘法
     *
     * 试乘法
     *
     * @param x
     * @return
     *
     */
    public int sqrt (int x) {
        if (x == 0) {
            return 0;
        }
        int mid = x / 2 + 1;
        int left = 0;
        int right = mid;
        long temp;
        while (left < right) {
            temp = (long)mid * mid;
            if (temp == x) {
                return mid;
            }
            if (temp > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (right + left) / 2;
        }
        temp = right * right;
        if (temp > x) {
            return right - 1;
        } else {
            return right;
        }
    }

    /**
     * 试除法
     * @param x
     * @return
     */
    public int sqrt1 (int x) {
        if (x == 0) {
            return x;
        }
        int i = 1;
        for (; i < x; i++) {
            if (i == x / i) {
                return i;
            } else if (i > x/ i) {
                return i - 1;
            }
        }
        return i;
    }

    /**
     * 使用牛顿法：可计算较精确的根
     * 参见：https://zh.wikipedia.org/wiki/%E7%89%9B%E9%A1%BF%E6%B3%95
     *
     * @param x
     * @return
     */
    public int sqrt2 (int x) {
        if (x == 0) {
            return x;
        }
        double current = 1;
        double last = 0;
        while (current != last) {
            last = current;
            current = (current + x / current) / 2;
        }
        return (int)current;
    }


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println("=========sqrt============");
        System.out.println(sqrt.sqrt(9));
        System.out.println(sqrt.sqrt(8));
        System.out.println(sqrt.sqrt(0));
        System.out.println(sqrt.sqrt(1));
        System.out.println(sqrt.sqrt(Integer.MAX_VALUE));

        System.out.println("=========sqrt1============");
        System.out.println(sqrt.sqrt1(9));
        System.out.println(sqrt.sqrt1(8));
        System.out.println(sqrt.sqrt1(0));
        System.out.println(sqrt.sqrt1(1));
        System.out.println(sqrt.sqrt1(Integer.MAX_VALUE));

        System.out.println("=========sqrt2============");
        System.out.println(sqrt.sqrt2(9));
        System.out.println(sqrt.sqrt2(8));
        System.out.println(sqrt.sqrt2(0));
        System.out.println(sqrt.sqrt2(1));
        System.out.println(sqrt.sqrt2(Integer.MAX_VALUE));
    }
}
