package 数据结构.循环队列;

import 数据结构.链表.ListNode;

/**
 * @author lxw
 * @date 2022/8/2 02:18
 * 循环队列
 */
public class MyCircularQueue {
    ListNode head;
    ListNode tail;
    int capacity;
    int size;

    public MyCircularQueue(int k) {
        this.capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = head.next;
        size--;
        return true;

    }

    //获取队首元素
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return head.val;
    }

    //获取队为元素
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return tail.val;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (capacity == size) {
            return true;
        }
        return false;
    }
}

