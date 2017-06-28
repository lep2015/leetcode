package org.lep.leetcode.medianoftwosortedarrays;

/**
 * Created by lverpeng on 2017/6/28.
 */
public class MedianOfTwoSortedArrays {
    /**
     * 找出数组（如果没有排序，先排序，这个例子中是排好序的）的中间位置median的数（实际也是数组的中位数，因为已经排好序）
     * 将median和要查找的数key进行比较：
     *  median < key: 说明key处于median和high之间，在数新数组median + 1到high之间继续查找
     *  median >= key: 说明key处于low到median - 1之间，在新数组low到median - 1之间继续查找
     *
     *  如果没有查找到说明没有相等的key，返回key处于数组中的位置（最后low == high）
     *
     *  注意：key有可能大于（比数组最后一个元素都大，length）或者小于（比数组最后一个元素都小， -1）数组的边界值，
     *          但是因为是return low，所以返回值最大为length，但是最小为0
     *
     * @param array
     * @param low
     * @param high
     * @param key
     * @return
     */
    public int binarySearch (int[] array, int low, int high, int key) {
        while (low <= high) {
            int median = low + (high - low) / 2;
            if (array[median] == key) {
                return median;
            }
            if (array[median] > key) {
                // 因为median位置的已经被查找过，所以不必被包含在新数组中
                high = median - 1;
            } else {
                // 因为median位置的已经被查找过，所以不必被包含在新数组中
                low = median + 1;
            }
        }
        // 上面循环结束后low == high，所以返回low和high是一样的
        return low;
    }

    /**
     *
     * 前提：两个数据都是排好序的，假设合并好的数组是C
     * 先找到较长数组A中间值的index：median
     * 然后判断（二分查找）A[median]在较短数组B中的位置pos，
     * 那么数组B在low到pos之间元素在数组C中的位置一定在A[median]前面，
     * 数组B中pos到high之间的元素在新数组C中的位置一定在A[median]之后，
     * 也就是说A[median]在新数组C中的位置就是newPos = median + pos
     * 如果newPos正好等于 (m + n) / 2，那么就找到中位数
     * 如果是小于，那么两个数组合并后的中位数在两个位置（median,pos）的右边（右边较大）
     * 如果是大于，那么中位数在两个位置的左边（左边较小）
     * 然后对两个新的数组递归查找
     *
     * @param arrA
     * @param m
     * @param arrB
     * @param n
     * @param lowA
     * @param highA
     * @param lowB
     * @param highB
     * @return
     */
    public double findMedianInSortedArraysHelper (int[] arrA, int m, int[] arrB, int n, int lowA, int highA, int lowB, int highB) {
        int median = (lowA + highA) / 2;
        int pos = binarySearch(arrB, lowB, highB, arrA[median]);
        int newPos = median + pos;

        if (newPos == (m + n) / 2) {
            if ((m + n) % 2 == 1) {
                // 如果是奇数个
                return arrA[median];
            }

            // 如果是偶数个，要找到前一个求平均值
            int pre = 0;
            if (median > 0 && pos > 0) {
                pre = arrA[median - 1] > arrB[pos - 1] ? arrA[median - 1] : arrB[pos - 1];
            } else if (median > 0) {
                pre = arrA[median - 1];
            } else {
                pre = arrB[pos - 1];
            }

            return (arrA[median] + pre) / 2.0;
        }

        /**
         *
         * 为什么计算low、high的时候需要加1或者减1？
         * 因为对median加减1是因为要减少二分查找次数，边界元素不包含在新数组中
         * 对pos减1是因为上面binarySearch函数返回的下标可能是等于数组length的，所以要减1
         * 不对pos加1 是因为binarySearch返回的下标是0，但是实际上应该是-1，也就是说相当于返回的已经是加过1的，再加1的话就会跳过一个元素
         *
         *
         **/

        if (newPos < (m + n) / 2) {
            lowA = median + 1;
            lowB = pos;
            if (highA -lowA > highB - lowB) {
                return findMedianInSortedArraysHelper(arrA, m, arrB, n, lowA, highA, lowB, highB);
            }
            return findMedianInSortedArraysHelper(arrB, n, arrA, m, lowB, highB, lowA, highA);
        } else {
            highA = median - 1;
            highB = pos - 1;
            if (highA -lowA > highB - lowB) {
                return findMedianInSortedArraysHelper(arrA, m, arrB, n, lowA, highA, lowB, highB);
            }
            return findMedianInSortedArraysHelper(arrB, n, arrA, m, lowB, highB, lowA, highA);

        }
    }

    /**
     * 先考虑数组长度为0的特殊情况，然后对两个数组进行二分查找
     *
     * @param arrA
     * @param m
     * @param arrB
     * @param n
     * @return
     */
    public double findMedianInSortedArrays (int[] arrA, int m, int[] arrB, int n) {
        if (m == 0 && n ==0)  {
            return 0.0;
        }
        if (m == 0 && n != 0) {
            return n % 2 == 1 ? arrB[n / 2] : (arrB[n / 2] + arrB[n / 2 - 1]);
        }
        if (n == 0 && m != 0) {
            return m % 2 == 1 ? arrA[m / 2] : (arrA[m / 2] + arrA[m / 2 - 1]);
        }

        /**
         *
         * 为什么要区分m、n大小？
         * 时机不区分也是可以的，但是做了区分的话，每次做二分查找对应的是小数组，查找更快，效率会比原来高
         *
         */
        if (m > n) {
            return findMedianInSortedArraysHelper(arrA, m, arrB, n, 0, m-1, 0, n-1);
        } else {
            return findMedianInSortedArraysHelper(arrB, n, arrA, m, 0, n-1, 0, m-1);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int r1[] = {1};
        int r2[] = {2};

        System.out.println("Median is 1.5 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(r1, r1.length, r2, r2.length));

        int ar1[] = {1, 12, 15, 26, 38};
        int ar2[] = {2, 13, 17, 30, 45, 50};

        System.out.println("Median is 17 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(ar1, ar1.length, ar2, ar2.length));

        int ar11[] = {1, 12, 15, 26, 38};
        int ar21[] = {2, 13, 17, 30, 45 };
        System.out.println("Median is 16 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(ar11, ar11.length, ar21, ar21.length));

        int a1[] = {1, 2, 5, 6, 8 };
        int a2[] = {13, 17, 30, 45, 50};
        System.out.println("Median is 10.5 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(a1, a1.length, a2, a2.length));

        int a10[] = {1, 2, 5, 6, 8, 9, 10 };
        int a20[] = {13, 17, 30, 45, 50};
        System.out.println("Median is 9.5 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(a10, a10.length, a20, a20.length));

        int a11[] = {1, 2, 5, 6, 8, 9 };
        int a21[] = {13, 17, 30, 45, 50};
        System.out.println("Median is 9 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(a11, a11.length, a21, a21.length));

        int a12[] = {1, 2, 5, 6, 8 };
        int a22[] = {11, 13, 17, 30, 45, 50};
        System.out.println("Median is 11 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(a12, a12.length, a22, a22.length));

        int b1[] = {1 };
        int b2[] = {2,3,4};
        System.out.println("Median is 2.5 = " + medianOfTwoSortedArrays.findMedianInSortedArrays(b1, b1.length, b2, b2.length));

    }


}
