package 缓存淘汰;

/**
 * @author lxw
 * @date 2022/8/4 00:46
 * 双向链表
 */
public class DoubleList {
    //头虚节点
    private Node head;
    //尾虚节点
    private Node tail;

    private int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //添加 时间复杂度要求O(1)
    public void addLast(Node node) {
        node.next = tail;
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        size++;
    }

    //删除目标节点 O(1)
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    //删除队列头元素 并返回
    public Node removerFirst() {
        if (head.next == tail) {
            return null;
        }
        Node firstNode = head.next;
        remove(firstNode);
        return firstNode;
    }

    //返回链表长度
    public int size() {
        return size;
    }
}

class Node {
    int key;
    int val;

    Node next;
    Node prev;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
