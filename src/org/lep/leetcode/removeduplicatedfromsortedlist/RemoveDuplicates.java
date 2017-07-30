package org.lep.leetcode.removeduplicatedfromsortedlist;

/**
 * Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * Created by lverpeng on 2017/7/30.
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicates {

    /**
     * 将链表中重复的元素移除，重复的元素只保留一个
     *
     * @param head
     * @return
     */
    public Node remove (Node head) {
        Node p = head;
        while (p != null && p.next != null) {
            if (p.value == p.next.value) {
                p.next = p.next.next;
                continue;
            }
            p = p.next;
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
        System.out.println();
    }

    public Node createList (int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        Node head = new Node();
        head.value = arr[1];
        Node pointer = head;
        for (int i = 1; i < arr.length; i++) {
            Node node = new Node();
            node.value = arr[i];
            pointer.next = node;
            pointer = pointer.next;
        }
        return head;
    }


    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();

        int[] arr = new int[]{1,1,2};
        int[] arr1 = new int[]{1,1,2,3,3};
        print(removeDuplicates.remove(removeDuplicates.createList(arr)));
        print(removeDuplicates.remove(removeDuplicates.createList(arr1)));

    }
}
