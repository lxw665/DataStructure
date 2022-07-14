package 数据结构.堆;

/**
 * @author lxw
 * @date 2022/7/14 23:59
 */
public class MaxHeap {
    private int[] data; //存放堆数据的数组
    private int size; //当前堆的大小

    public MaxHeap(int cap) {
        data = new int[cap + 1]; //因为索引0是不用的，所以要加一个
    }

    //父节点的索引
    public int parent(int root) {
        return root / 2;
    }

    //左孩子的索引
    public int left(int root) {
        return 2 * root;
    }

    //右孩子的索引
    public int right(int root) {
        return 2 * root + 1;
    }
}
