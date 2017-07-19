package org.lep.leetcode.permutationsequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/permutation-sequence/
 *
 * Created by lverpeng on 2017/7/20.
 *
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * Given n and k, return the kth permutation sequence.
 *
 * Note: Given n will be between 1 and 9 inclusive.
 */
public class PermutationSequence {


    /**
     * 寻找n个元素组成的第k个排列
     *
     * 第一方法：
     * 从最小的开始，循环k次找到第k个排列
     *
     * 第二种方法：
     * 找到第k个排列的规律
     *
     * 这种题目，可以先以一个简单的例子来找规律
     * 这里假设n = 4, k = 9
     * 1234
     * 1243
     * 1324
     * 1342
     * 1423
     * 1432
     * 2134
     * 2143
     * 2314     >>>> k = 9
     * 2341
     * 2413
     * 2431
     * 3124
     * 3142
     * 3214
     * 3241
     * 3412
     * 3421
     * 4123
     * 4132
     * 4213
     * 4231
     * 4312
     * 4321
     *
     * 以下认为arr下标从1开始
     * 第一位：
     * arr[4] = {1,2,3,4},k = 9,n = 4
     * 上面的排列是有规律的，每个位置每一个数字出现cycle = (n-1) = (4-1)! = 6次，整体第k个的时候，该位置已经经过 count = k / cycle = 1个周期，则该位置的数是arr[count + 1] = 2，因为已经过了count个周期，是第count+1个周期，就轮到了数组arr中的第count+1个数字
     *
     * 第二位：
     * arr[3] = {1,3,4},k' = k % (n-1)! = 3,n' = 3
     * 右面一个位置的数字，以2开头，因为2已经确定，剩下的形成一个新的数组arr[3] = {1,3,4}，每个位置每个数字出现cycle = (n' - 1) = (3-1)! = 2次，整体第k个的时候，对于当前数组组成的排列而言处于第k' = k % (n-1) = 3个，第三个的第一位这个时候已经经过count = k' / cycle = 1个周期，正处于第index = count + 1 = 2个周期，也就是该位置的数为arr[index]
     *
     * 第三位：
     * arr[2] = {1,4},k'' = k' % (n'-1)! = 1,n'' = 2
     * cycle = (n'' - 1)! = 1, count = k'' / cycle = 1,index = count + 1 = 2, arr[index] = 4
     *
     * 第四位：
     * arr[1] = {1}, k''' = k'' % (n'' - 1)! = 0, n''' = 1
     * cycle = (n''' - 1)! = 1,index = k''' / cycle + 1 = 1,arr[index] = 1
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation (int n, int k) {
        if (n < 1) {
            return "";
        }
        int[] fatorial = new int[n];
        List<Integer> arr = new ArrayList<Integer>();
        fatorial[0] = 1;
        arr.add(1);
        for (int i = 1; i < n; i++) {
            fatorial[i] = fatorial[i-1] * i;
            arr.add(i+1);
        }
        int index = 0;
        // 因为数组下标是从0开始的，比如当 n=4,k=24 的时候，计算出下标index=4，下标越界，所以这里先减1？
        // 感觉理由有点牵强，再想想
        k--;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fatorial.length; i++) {
            index = k / fatorial[n-1];
            result.append(arr.get(index));
            arr.remove(index);
            k = k % fatorial[n-1];
            n--;
        }
        return result.toString();
    }


    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
        System.out.println(permutationSequence.getPermutation(4, 9));
        System.out.println(permutationSequence.getPermutation(4, 24));

    }

}
