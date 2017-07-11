package org.lep.leetcode.mergetwolist;

import java.util.ArrayList;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/merge-two-sorted-lists/
 *
 * Created by lverpeng on 2017/7/11.
 *
 * Merge two sorted linked lists and return it as a new list. The new list should be
 * made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedList {

    /**
     * 把两个链表合并到一个新的链表中
     * 保存新的链表头head，使用一个新链表的指针的current用来指向新链表的尾部，用来添加新的元素
     *
     * 最后加入较长链表的其余元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public Node merge (Node list1, Node list2) {
        Node head = null;
        Node current = null;
        while (list1 != null && list2 != null) {
            Node n = null;
            if (list1.value < list2.value) {
                n = list1;
                list1 = list1.next;
            } else {
                n = list2;
                list2 = list2.next;
            }
            if (head == null) {
                head = current = n;
            } else {
                current.next = n;
                current = current.next;
            }

        }
        list1 = list1 == null ? list2 : list1;

        if (list1 != null) {
            if (head != null && current != null) {
                current.next = list1;
            } else {
                head = list1;
            }
        }

        return head;
    }



    private static class Node {
        int value;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + (next == null ? "" : next.value) +
                    '}';
        }
    }

    private static void print (Node node) {
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }


    public static void main(String[] args) {
        MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList();
        Node list1 = new Node();
        Node pointer1 = list1;
        list1.value = 1;
        Node list2 = new Node();
        list2.value = 2;
        Node pointer2 = list2;
        for (int i = 3; i < 10; i++) {
            Node node = new Node();
            node.value = i;
            if (i % 2 == 1) {
                pointer1.next = node;
                pointer1 = pointer1.next;
            } else {
                pointer2.next = node;
                pointer2 = pointer2.next;
            }
        }
        print(list1);
        System.out.println();
        print(list2);
        System.out.println();
        Node result = mergeTwoSortedList.merge(list1, list2);
        print(result);


    }
}
