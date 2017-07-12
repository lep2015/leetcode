package org.lep.leetcode.swapnodesinpairs;

/**
 * Source : https://oj.leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Created by lverpeng on 2017/7/12.
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list,
 * only nodes itself can be changed.
 *
 */
public class SwapNodeInPairs {

    /**
     * 交换相邻两个node 的位置
     *
     * 记录下当前节点的下一个节点， node = current.next
     * 将当前节点的下一个指向，下下个，current.next = node.next
     * 将下一个节点指向当前节点，node.next = current
     * 将上一个节点的下一个指向当前节点，las.next = current
     *
     * @param head
     * @return
     */
    public Node swap (Node head) {
        if (head == null) {
            return null;
        }
        Node pointer = head;
        Node next = head.next;
        Node last = null;
        while (next != null) {
            pointer.next = next.next;
            next.next = pointer;

            // 记录head
            if (pointer == head) {
                head = next;
            }

            if (last != null) {
                last.next = next;
            }
            last = pointer;

            // 更新pointer，next
            pointer = pointer.next;
            if (pointer != null) {
                next = pointer.next;
            } else {
                break;
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
        SwapNodeInPairs swapNodeInPairs = new SwapNodeInPairs();

        Node list1 = new Node();
        list1.value = 1;
        Node pointer = list1;
        for (int i = 2; i < 10; i++) {
            Node node = new Node();
            node.value = i;
            pointer.next = node;
            pointer = pointer.next;
        }
        print(list1);
        System.out.println();
        print(swapNodeInPairs.swap(list1));

    }

}
