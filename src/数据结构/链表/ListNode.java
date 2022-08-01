package 数据结构.链表;

/**
 * @author lxw
 * @date 2022/8/2 02:59
 */
public class ListNode {
    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
