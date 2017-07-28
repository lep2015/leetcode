package org.lep.leetcode.removeduplicatesfromsortedarray;

/**
 * Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * Created by lverpeng on 2017/7/28.
 *
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 *
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 *
 */
public class RemoveDuplicates2 {


    /**
     * 只需要找到不重复的元素个数，不重复的定义是：小于等于2个
     * 遍历数组
     *
     * @param arr
     * @return
     */
    public int remove (int[] arr) {
        if (arr.length <= 2) {
            return arr.length;
        }
        int count = 1;
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                count ++;
            } else {
                if (count > 2) {
                    pos += 2;
                } else {
                    pos += count;
                }
                count = 1;
            }
        }
        if (count > 2) {
            pos += 2;
        } else {
            pos += count;
        }
        return pos;
    }

    /**
     * 出现3次及以上才算重复
     * 第一次出现记一次数。第二次出现记一次数
     *
     * @param arr
     * @return
     */
    public int remove1 (int[] arr) {
        if (arr.length <= 2) {
            return arr.length;
        }
        int count = 1;
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                count ++;
                if (count == 2) {
                    pos ++;
                }
            } else {
                count = 1;
                pos ++;
            }
        }
        return pos +1;
    }

    public static void main(String[] args) {
        RemoveDuplicates2 removeDuplicates = new RemoveDuplicates2();
        int[] arr0 = new int[]{1,1,1};
        int[] arr = new int[]{1,1,2};
        int[] arr1 = new int[]{1,1,1,2};
        int[] arr2 = new int[]{1,1,22,22,22,33};
        int[] arr3 = new int[]{1,1,1,1,1,1};
        System.out.println(removeDuplicates.remove(arr0) + "----" + removeDuplicates.remove1(arr0));
        System.out.println(removeDuplicates.remove(arr) + "----" + removeDuplicates.remove1(arr));
        System.out.println(removeDuplicates.remove(arr1) + "----" + removeDuplicates.remove1(arr1));
        System.out.println(removeDuplicates.remove(arr2) + "----" + removeDuplicates.remove1(arr2));
        System.out.println(removeDuplicates.remove(arr3) + "----" + removeDuplicates.remove1(arr3));
    }
}
