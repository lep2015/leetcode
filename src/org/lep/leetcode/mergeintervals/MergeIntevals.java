package org.lep.leetcode.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/merge-intervals/
 *
 * Created by lverpeng on 2017/7/17.
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 *
 *
 */
public class MergeIntevals {


    /**
     *
     * 合并重叠的数组
     *
     * 先对数组按照左边界排序，然后依次合并相邻的数组
     *
     * @param list
     * @return
     */
    public List<IntervalArray> merge (List<IntervalArray> list) {
        if (list.size() <= 1) {
            return list;
        }
        Collections.sort(list);
        List<IntervalArray> result = new ArrayList<IntervalArray>();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i - 1).end >= list.get(i).start) {
                list.get(i-1).end = Math.max(list.get(i).end, list.get(i - 1).end);
            } else {
                result.add(list.get(i));
            }
        }
        return result;
    }


    private static class IntervalArray  implements Comparable<IntervalArray>{
        int start;
        int end;

        public IntervalArray(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(IntervalArray o) {
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return "[" + start + ","+ end +']';
        }
    }

    public static void main(String[] args) {
        MergeIntevals mergeIntevals = new MergeIntevals();
        List<IntervalArray> list = new ArrayList<IntervalArray>();

        list.add(new IntervalArray(2, 6));
        list.add(new IntervalArray(8, 10));
        list.add(new IntervalArray(15, 18));
        list.add(new IntervalArray(1, 3));


        List<IntervalArray> result = mergeIntevals.merge(list);

        System.out.println(Arrays.toString(result.toArray(new IntervalArray[result.size()])));

    }

}
