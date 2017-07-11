package org.lep.leetcode.removenthnodefromtheendoflist;

/**
 * Source : https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Created by lverpeng on 2017/7/11.
 *
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 *    Given linked list: 1->2->3->4->5, and n = 2.
 *
 *    After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * 移除倒数第n个node，但是链表是单向的，只能从前向后，要找到倒数第n个需要技巧
     * 设置两个指针，faster、slower，初始化都指向head，移动faster n次，然后同时移动slower，
     * faster指向tail的时候，slower就指向了倒数第n个
     *
     * 假设链表共有t个元素，faster第一次移动n个之后，剩下的就是t - n，这个时候slower从开始移动，就是移动t - n次，也就是倒数第n个
     *
     * 考虑特殊情况：
     * 链表为空
     * 链表总长度小于n，也就是faster提前遇到null
     *
     * n不能等于链表的长度，因为无法判断t是多少，也就无法判断faster = null的时候是 n == t还是 n > t
     *
     * @param head
     * @param n
     * @return
     */
    public Node removeNode (Node head, int n) {
        if (head == null || n <= 0) {
            return null;
        }
        Node faster = head;
        Node slower = head;
        for (int i = 0; i <= n; i++) {
            if (faster == null) {
                return null;
            }
            faster = faster.next;
        }
        if (faster == null) {
            // n == 链表的size
            head = head.next;
            return head;
        }
        while (faster != null) {
            faster = faster.next;
            slower = slower.next;
        }
        slower.next = slower.next.next;
        return head;

    }

    private static class Node {
        int value;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + (next == null ? "null" : next.value) +
                    '}';
        }
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();
        Node head = new Node();
        Node last = head;
        last.value = 1;

        for (int i = 2; i <= 5; i++) {
            Node node = new Node();
            node.value = i;
            last.next = node;
            last = node;
        }

        Node newHead = removeNthNodeFromEndOfList.removeNode(head, 6);

        Node pointer = newHead;
        while (pointer != null) {
            System.out.println(pointer);
            pointer = pointer.next;
        }
    }
}
