package org.lep.leetcode.mergeklist;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Source : https://oj.leetcode.com/problems/merge-k-sorted-lists/
 *
 * Created by lverpeng on 2017/7/11.
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 */
public class MergeKList {

    /**
     * 两两归并排序
     *
     * @param headList
     * @return
     */
    public Node merge (List<Node> headList) {
        while (headList.size() > 1) {
            Node list1 = headList.remove(headList.size() - 1);
            Node list2 = headList.remove(headList.size() - 1);
            Node mergeList = mergeTwoList(list1, list2);
            headList.add(0, mergeList);
        }
        return headList.get(0);

    }

    /**
     * 对k个链表做归并排序，比较k个元素的时候使用最小堆排序
     *
     * @param headList
     * @return
     */
    public Node merge0 (List<Node> headList) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        for (Node node : headList) {
            priorityQueue.add(node);
        }

        Node head = null;
        Node current = null;
        while (priorityQueue.size() > 0) {
            Node node = priorityQueue.poll();
            if (head == null) {
                head = node;
                current = node;
            } else {
                current.next = node;
                current = current.next;
            }
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
        }
        return head;
    }

    /**
     * 归并排序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static Node mergeTwoList (Node list1, Node list2) {
        Node head = null;
        Node current = null;
        while (list1 != null && list2 != null) {
            Node node = null;
            if (list1.value > list2.value) {
                node = list2;
                list2 = list2.next;
            } else {
                node = list1;
                list1 = list1.next;
            }
            if (head == null) {
                head = current = node;
            } else {
                current.next = node;
                current = current.next;
            }
        }

        list1 = list1 == null ? list2 : list1;
        if (list1 != null) {
            if (head != null) {
                current.next = list1;
            } else {
                head = list1;
            }
        }
        return head;
    }




    private static class Node  implements Comparable<Node>{
        int value;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + (next == null ? "" : next.value) +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    private static void print (Node node) {
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }


    public static void main(String[] args) {
        Node list1 = new Node();
        Node pointer1 = list1;
        list1.value = 1;
        Node list2 = new Node();
        list2.value = 2;
        Node pointer2 = list2;
        Node list3 = new Node();
        list3.value = 3;
        Node pointer3 = list3;
        for (int i = 4; i < 20; i++) {
            Node node = new Node();
            node.value = i;
            if (i % 3 == 1) {
                pointer1.next = node;
                pointer1 = pointer1.next;
            } else if (i % 3 == 2) {
                pointer2.next = node;
                pointer2 = pointer2.next;
            } else {
                pointer3.next = node;
                pointer3 = pointer3.next;
            }
        }

        List<Node> list = new ArrayList<Node>();
        list.add(list1);
        list.add(list1);
        list.add(list1);
        MergeKList mergeKList = new MergeKList();
//        Node result = mergeKList.merge(list);
//        print(result);

        System.out.println();

        Node result = mergeKList.merge0(list);
        print(result);

    }
}
