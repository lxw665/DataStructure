package 数据结构.堆;

/**
 * @author lxw
 * @date 2022/7/14 23:59
 * 可以当作优先队列
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

    //返回最大的元素
    public int getMax() {
        return data[1];
    }

    //插入元素num
    public void insert(int num) {
        //插入到数组的最后
        data[size++] = num;
        //上浮到正确的位置
        swim(size);
    }

    //删除最大的元素
    public int delMax() {
        int max = data[1];
        //和最后一个元素交换
        swap(max, size);
        //删掉
        data[size] = Integer.parseInt(null);
        size--;
        //然后让1下沉到正确的位置
        sink(1);
        return max;
    }

    //上浮第x个元素，维护大顶堆的性质
    private void swim(int x) {
        while (x > 1 && less(parent(x), x)) {
            //如果x大于父节点，则把x换上去
            swap(parent(x), x);
            x = parent(x);
        }
    }

    //下沉第x个元素，维护大顶堆的性质
    private void sink(int x) {
        //沉到底了就不沉了
        while (left(x) <= size) {
            //先假设左孩子是最大的
            //为什么不直接比较左右孩子取最大的？
            //因为不一定有右孩子
            int max = left(x);
            if (right(x) <= size && less(max, right(x))) {
                max = right(x);
            }
            //如果节点x比两个孩子最大的都大就不用下沉了
            if (less(max, x)) {
                break;
            }
            //下沉
            swap(x, max);
            x = max;
        }
    }

    //交换
    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    //判断data[i]是否比data[j]小
    private boolean less(int i, int j) {
        if (data[i] < data[j]) {
            return true;
        }
        return false;
    }

}
