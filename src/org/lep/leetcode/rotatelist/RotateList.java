package org.lep.leetcode.rotatelist;

import org.lep.leetcode.rotateimage.RotateImage;

/**
 * Source : https://oj.leetcode.com/problems/rotate-list/
 *
 * Created by lverpeng on 2017/7/20.
 *
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 *
 */
public class RotateList {


    /**
     * 以链表中某一个元素为支点翻转链表
     * 如果用Java中的链表做，反而不容易，可以构造一个单向链表
     * 找到链表的尾部
     * 将链表的尾部指向头部，也就是构成一个环形链表，并记录链表总数n
     * 旋转之后支点前面的长度为 n-k，则从链表尾部移动 n-k个节点，该位置的next为新的head，将该位置的next置为空也就是打断环形链表
     *
     *
     * 边界条件
     * 链表为空或者k <= 0直接返回head
     * 如果k >= n 实际上 k = k % n
     *
     * @param head
     * @param k
     */
    public Node rotate(Node head, int k) {
        if (k <= 0 || head == null) {
            return head;
        }
        Node p = head;
        int n = 1;
        // 获取链表总数、tail
        while (p.next != null) {
            p = p.next;
            ++n;
        }
        // 链表tail
        p.next = head;
        k = n - k % n;
        for (int i = 0; i < k; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
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

    public static Node generateList (int n) {
        Node list = new Node();
        list.value = 1;
        Node pointer = list;
        for (int i = 2; i <= n; i++) {
            Node node = new Node();
            node.value = i;
            pointer.next = node;
            pointer = pointer.next;
        }
        return list;
    }


    public static void main(String[] args) {
        RotateList rotateList = new RotateList();

        print(rotateList.rotate(generateList(5), 5));
        print(rotateList.rotate(generateList(5), 2));
        print(rotateList.rotate(generateList(5), 0));
        print(rotateList.rotate(generateList(5), 4));
        print(rotateList.rotate(null, 2));

    }



}
