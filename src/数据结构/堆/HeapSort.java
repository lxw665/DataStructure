package 数据结构.堆;

public class HeapSort {
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    public void heapSort(int[] nums) {
        int len = nums.length - 1;
        buildMaxHeap(nums, len);
        for (int i = len; i >= 1; --i) {
            swap(nums, i, 0);
            len -= 1;
            maxHeapify(nums, 0, len);
        }
    }

    public void buildMaxHeap(int[] nums, int len) {
        for (int i = len / 2; i >= 0; --i) {
            maxHeapify(nums, i, len);
        }
    }

    public void maxHeapify(int[] nums, int i, int len) {
        for (; (i << 1) + 1 <= len;) {
            int lson = (i << 1) + 1;
            int rson = (i << 1) + 2;
            int large;
            if (lson <= len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson <= len && nums[rson] > nums[large]) {
                large = rson;
            }
            if (large != i) {
                swap(nums, i, large);
                i = large;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
