package org.lep.leetcode.devidetwoint;

/**
 * Source : https://oj.leetcode.com/problems/divide-two-integers/
 *
 * Created by lverpeng on 2017/7/12.
 *
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */
public class DevideTwoInt {

    /**
     * 不能使用乘、除、求模
     *
     * 可以直接每次减去除数，但是效率较低，那可以使用移位，类似乘法，将除数乘以2的倍数，然后再与被除数做减法
     *
     * @param devident
     * @param devisor
     * @return
     */
    public int devide (int devident, int devisor) {
        long[] sub = new long[32];
        int newDevident = devident < 0 ? -devident : devident;
        int newDevisor = devisor < 0 ? -devisor : devisor;

        int count = 0;
        sub[count] = newDevisor;
        int twiceNum = newDevisor;
        while (newDevident > twiceNum) {
            twiceNum = newDevisor << count;
            sub[count] = twiceNum;
            count ++;
        }
        count --;

        int remainder = newDevident;
        int result = 0;
        while (remainder >= newDevisor) {
            if (remainder >= sub[count]) {
                remainder -= sub[count];
                result = result + (1 << count);
            } else {
                count --;
            }
        }

        return (devident > 0 ^ devisor > 0) ? -result : result;
    }

    public static void main(String[] args) {
        DevideTwoInt devideTwoInt = new DevideTwoInt();
        System.out.println(devideTwoInt.devide(-100, 10));
        System.out.println(devideTwoInt.devide(-100, 9));
        System.out.println(devideTwoInt.devide(-100, -9));
        System.out.println(devideTwoInt.devide(100, 9));
        System.out.println(devideTwoInt.devide(0, 9));
    }

}
