package org.lep.leetcode.removeduplicatedfromsortedlist;

/**
 * Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Created by lverpeng on 2017/7/30.
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class RemoveDuplicates2 {

    /**
     * 移除链表中所有重复的元素
     *
     * @param head
     * @return
     */
    public Node remove (Node head) {
        Node dummy = new Node();
        dummy.next = head;
        Node cur = head;
        Node pre = dummy;
        boolean duplicated = false;
        while (cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                cur.next = cur.next.next;
                duplicated = true;
            } else if (duplicated) {
                // 如果出现重复则移除重复的元素
                pre.next = cur.next;
                cur = pre.next;
                duplicated = false;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        if (duplicated) {
            pre.next = cur.next;
        }
        head = dummy.next;
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
        RemoveDuplicates2 removeDuplicates2 = new RemoveDuplicates2();

        int[] arr = new int[]{1,1,2};
        int[] arr1 = new int[]{1,1,2,3,3};
        int[] arr2 = new int[]{1,1,2,3,3,3,4,4,5};
        print(removeDuplicates2.remove(removeDuplicates2.createList(arr)));
        print(removeDuplicates2.remove(removeDuplicates2.createList(arr1)));
        print(removeDuplicates2.remove(removeDuplicates2.createList(arr2)));
    }

}
