package org.lep.leetcode.foursum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 *
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 *
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 */
public class FourSum {

    /**
     *
     * 分解为多个3sum问题，求出3sum问题普通解法
     * 3sum的解法就是分为多个2sum问题，2sum问题就可以使用原来的方法进行解答
     */
    public Set<Integer[]>  threeSum (int[] num, int total) {
        Set<Integer[]> set = new HashSet<Integer[]>();
//        Arrays.sort(num);
        for (int i= 0; i < num.length; i++) {
            int left = i + 1;
            int right = num.length - 1;
            int sum2 = total - num[i];
            while (left < right) {
                if (num[left] + num[right] == sum2) {
                    Integer[] answer = {num[i], num[left], num[right]};
                    set.add(answer);
                    left++;
                    right--;
                    while (left < right && num[left] + num[right] == sum2) {
                        Integer[] ans = {num[i], num[left], num[right]};
                        set.add(ans);
                        left++;
                        right--;
                    }

                } else if (num[left] + num[right] < sum2) {
                    left++;
                    while (left < right && num[left] + num[right] < sum2) {
                        left++;
                    }
                } else {
                    right--;
                    while (left < right && num[left] + num[right] > sum2) {
                        right--;
                    }
                }
            }
        }
        return set;
    }


    /**
     * 对数组排序，分解为3sum处理
     *
     * @param num
     * @param total
     * @return
     */
    public Set<Integer[]> fourSum (int[] num, int total) {
        Set<Integer[]> set = new HashSet<Integer[]>();
        Arrays.sort(num);
        int[] subArr = new int[num.length - 1];
        for (int i = 0; i < num.length; i++) {
            int a = num[i];
            System.arraycopy(num, i + 1, subArr, 0, num.length  - i - 1);
            Set<Integer[]> threeSumResult = threeSum(subArr, total - a);
            for (Integer[] intArr : threeSumResult) {
                Integer[] arr = new Integer[4];
                arr[0] = a;
                System.arraycopy(intArr, 0, arr, 1, 3);
                set.add(arr);
            }
        }
        return set;
    }

    public void printList (Set<Integer[]> set) {
        for (Integer[] arr : set) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        int[] arr = {1, 0, -1, 0, -2, 2};
        Set<Integer[]> set  = fourSum.fourSum(arr, 0);
        fourSum.printList(set);
    }

}
