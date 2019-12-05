/*
Assumption
1. the input is an array of integer
2. sort the array such that number at even position are greater than the number
at odd position
3. is there duplicate number? yes
4. what if have no solution, for example [2,2,2,2] ?

Approach:
1. The idea is first do a quick select to find the median of the array.
After the selction, all the number smaller or equal to median are on median's left
all the number greater than median are on median's right.
For example, [6,5,4,3,2,1] => [2,1,3,4,6,5]
then start from 1 and 4, from right to left, we put the element to a new array
then we can get [3,5,1,6,2,4]

Time: O(n) for quick select, O(n) for rearrange
Space: O(n) for temp array
*/
public class CWiggleSort {

    public static void main(String[] args) {
        CWiggleSort sol = new CWiggleSort();
        int[] nums = {100,1,100,1,100,1};
        sol.wiggleSort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
         
        int n = nums.length;
         
        // Step 1: Find median of the array, return the the median
        int median = findMedian(nums, 0, n - 1, (n + 1) / 2);
         
        // Step 2: 3-way sort, put median in the middle, 
        // numbers less than median on the left, 
        // numbers greater than median on the right
        int[] temp = new int[n];
        int left = 0;
        int right = n - 1;
         
        for (int i = 0; i < n; i++) {
            if (nums[i] < median) {
                temp[left] = nums[i];
                left++;
            } else if (nums[i] > median) {
                temp[right] = nums[i];
                right--;
            }
        }
         
        // add median into the middle
        for (int i = left; i <= right; i++) {
            temp[i] = median;
        }
         
        // Step 3: wiggle sort
        left = (n - 1) / 2;
        right = n - 1;
         
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                nums[i] = temp[left];
                left--;
            } else {
                nums[i] = temp[right];
                right--;
            }
        }
    }
     
    private int findMedian(int[] nums, int lo, int hi, int k) {
        // if (lo >= hi) {
        //     return lo;
        // }
         
        int leftEnd = partition(nums, lo, hi);
        int leftSize = leftEnd - lo + 1;
        if (leftSize == k) {
            return nums[leftEnd];
        }
         
        if (leftSize > k) {
            return findMedian(nums, lo, leftEnd - 1, k);
        } else {
            return findMedian(nums, leftEnd + 1, hi, k - leftSize);
        }
    }
     
    private int partition(int[] nums, int left, int right) {
        int index = left;
        int pivot = nums[right];
        for (int i = left; i <= right ; i ++){
            if (nums[i] >= pivot){
                swap(nums, i, index);
                index ++;
            }
        }
        return index - 1;
    }
     
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
