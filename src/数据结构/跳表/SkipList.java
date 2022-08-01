package 数据结构.跳表;

/**
 * @author lxw
 * @date 2022/7/21 14:33
 * 跳表的实现
 */
public class SkipList {
    private static final double SKIPLIST_P = 0.5;
    private static final int MAX_LEVEL = 32;
    //节点数
    private int levelCount; //当前实际有效的最大层数

    private Node head = new Node(null, MAX_LEVEL); //头节点


    public SkipList() {

    }

    public boolean search(int target) {
        Node searchHead = head;
        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
        return false;
    }

    /**
     * 找到level层value刚好不小于node的节点
     * @param node node 从哪个节点开始找
     * @param levelIndex 所在层
     * @param value 节点的值
     * @return
     */
    private Node findClosest(Node node, int levelIndex, int value) {
        while (node.next[levelIndex] != null && value > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }
}

//跳表的节点
class Node {
    Integer value; //节点值
    Node[] next; //节点在不同层的下一个节点

    public Node(Integer value, int level) {
        this.value = value;
        this.next = new Node[level];
    }
}
