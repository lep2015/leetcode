package org.lep.leetcode.addtwonumbers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/add-two-numbers/
 *
 * Created by lverpeng on 2017/6/23.
 *
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 */
public class AddTwoNumbers {


    /**
     * 问题就是求两个数的和，只不过这两个数分别存在两个链表中，低位在前，高位在后，求出的和也存在链表中
     * 循环较长的那个链表：
     * 如果是当前有个链表都有值，则将两个数与上次计算的商（也就是进位）求和，结果的对于10的余数存于结果链表对一个的位置，结果对于10的商保存在变量中（也就是进位），
     * 如果已经超过了其中一个链表的长度，则直接把另一个链表的数存入结果链表
     *
     * @param num1
     * @param num2
     * @return
     */
    public List<Integer> addTwoNumbers (List<Integer> num1, List<Integer> num2) {
        List<Integer> numbers = new ArrayList<Integer>();
        int size = 0;
        if (num1.size() > num2.size()) {
            size = num1.size();
        } else {
            size = num2.size();
        }

        int carry = 0;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (num1.size() > i && num2.size() > i) {
                sum = carry + num1.get(i) + num2.get(i);
            } else if (num1.size() - 1 > i) {
                sum = carry + num1.get(i);
            } else {
                sum = carry + num2.get(i);
            }
            numbers.add(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            numbers.add(carry);
        }
        return numbers;
    }

    public List<Integer> addTwoNumbersRefactor (List<Integer> num1, List<Integer> num2) {
        List<Integer> result = new ArrayList<Integer>();
        int size = num1.size() > num2.size() ? num1.size() : num2.size();
        int carry = 0;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum = carry + getNumber(num1, i) + getNumber(num2, i);
            carry = sum / 10;
            result.add(sum % 10);
        }
        if (carry > 0) {
            result.add(carry);
        }
        return result;
    }

    private int getNumber(List<Integer> numbers, int i) {
        if (i < 0 || i >= numbers.size()) {
            return 0;
        }
        return numbers.get(i);
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        List<Integer> num1 = new ArrayList<Integer>(){{
                add(2);
                add(4);
                add(3);
        }};
        List<Integer> num2 = new ArrayList<Integer>(){{
            add(5);
            add(6);
            add(4);
        }};
        System.out.println(addTwoNumbers.addTwoNumbers(num1, num2));
        System.out.println(addTwoNumbers.addTwoNumbersRefactor(num1, num2));
    }
}
