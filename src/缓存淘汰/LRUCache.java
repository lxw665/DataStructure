package 缓存淘汰;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxw
 * @date 2022/8/4 00:39
 */
public class LRUCache {
    private Map<Integer, Node> map;

    private DoubleList cache;

    //最大容量
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        //如果不存在直接返回-1
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        Node node = map.get(key);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            makeRecently(key);
            return;
        }
        if (capacity == cache.size()) {
            removeLeastRecently();
        }
        addRecently(key, value);
    }


    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {
        Node node = map.get(key);
        //先删除在直接添加到队尾
        cache.remove(node);
        cache.addLast(node);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }

    /* 删除某一个 key */
    private void deleteKey(int key) {
        Node node = map.get(key);
        map.remove(key);
        cache.remove(node);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        Node deleteNode = cache.removerFirst();
        int key = deleteNode.key;
        map.remove(key);
    }
}

