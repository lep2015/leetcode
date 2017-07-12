package org.lep.leetcode.removeelement;

/**
 * Source : https://oj.leetcode.com/problems/remove-element/
 *
 * Created by lverpeng on 2017/7/12.
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveElement {

    /**
     * 判断不等于value的值个数
     * @param num
     * @param value
     * @return
     */
    public int remove (int[] num, int value) {
        int pos = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] != value) {
                pos ++;
            }
        }
        return pos;
    }


    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] num = new int[]{1,2,3,4,5,5,6};
        System.out.println(removeElement.remove(num, 5));
    }
}
