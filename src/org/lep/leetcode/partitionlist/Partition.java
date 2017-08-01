package org.lep.leetcode.partitionlist;

/**
 * Source : https://oj.leetcode.com/problems/partition-list/
 *
 * Created by lverpeng on 2017/8/1.
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come
 * before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class Partition {


    /**
     * 将链表中所有小于x的节点排在前面，然后是大于等于x的节点，partition后的链表要按照之前元素的相对顺序排序
     *
     * 将所有大于等于x的节点移除到另外一个链表，剩下的就是小于x的元素，然后将两个链表连接起来
     *
     * 因为链表头也可能被移除（可能变化），所以这里使用一个dummy节点指向原来的头，作为新的头，也就是链表的头
     *
     * @param head
     * @return
     */
    public Node partition (Node head, int x) {
        Node dummy = new Node();
        dummy.next = head;
        head = dummy;
        Node greaterList = new Node();
        Node greaterPointer = greaterList;

        while (head != null && head.next != null) {
            if (head.next.value >= x) {
                // 加入新链表
                greaterPointer.next = head.next;
                head.next = head.next.next;
                greaterPointer = greaterPointer.next;
                greaterPointer.next = null;
                // 从原来的链表移除
            } else {
                head = head.next;
            }
        }
        head.next = greaterList.next;
        return dummy.next;
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
        head.value = arr[0];
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
        Partition partition = new Partition();
        int[] arr = new int[]{1,4,3,2,5,2};
        int[] arr1 = new int[]{4,3,2,5,2};
        print(partition.partition(partition.createList(arr1), 3));
        print(partition.partition(partition.createList(arr), 3));
    }
}
