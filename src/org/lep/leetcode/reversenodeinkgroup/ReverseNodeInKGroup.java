package org.lep.leetcode.reversenodeinkgroup;

/**
 * Source : https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Created by lverpeng on 2017/7/12.
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 */
public class ReverseNodeInKGroup {


    /***
     * 首先得找到翻转的界限，先找到第k个node
     *
     * 从head开始，依次将下一个node指向上一个node，也就是从前向后改变指向关系
     *
     * 返回翻转后的最后一个元素，也就是当前元素的上一个节点
     *
     * @param head
     * @param k
     */
    public Node reverseKnode (Node head, int k) {
        Node end = head;
        while (end != null && k > 0) {
            end = end.next;
            k --;
        }

        // 如果链表总长度小于k
        if (k > 0) {
            return head;
        }

        Node next = head;
        Node last = end;
        Node tempNext = null;
        while (next != end) {
            tempNext = next.next;
            next.next = last;
            last = next;
            next = tempNext;
        }

        return last;
    }

    /**
     * 循环翻转，每次翻转k个node
     *
     * 保存head：第一次翻转后的head就是最终的head
     *
     * @param head
     * @param k
     * @return
     */
    public Node reverseAll (Node head, int k) {
        Node fakeHead = new Node();     // 记录最终的head
        fakeHead.next = head;
        Node pointer = fakeHead;           // 记录当前node
        while (pointer != null) {
            pointer.next = reverseKnode(pointer.next, k);
            for (int i = 0; i < k && pointer != null; i++) {
                pointer = pointer.next;
            }
        }

        return fakeHead.next;
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
        Node list1 = new Node();
        list1.value = 1;
        Node pointer = list1;
        for (int i = 2; i < 11; i++) {
            Node node = new Node();
            node.value = i;
            pointer.next = node;
            pointer = pointer.next;
        }
        print(list1);
        System.out.println();
        print(new ReverseNodeInKGroup().reverseAll(list1, 3));
    }

}
