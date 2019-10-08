package airbnb2019;

public class MaxNightCanAccomadate {
	public static void main(String args[])
	{
	int[] nums = {4, 10, 3, 1, 5};
	System.out.println(maxSum(nums));
	}
	
	public static int maxSum(int[] nums) {
		int include = nums[0];
		int exclude = 0;
		for (int i = 1 ; i < nums.length ; i ++) {
			int ii = include;
			int ee = exclude;
			
			include = ee + nums[i];
			exclude = Math.max(ii, ee);
		}
		return Math.max(include, exclude);
	}
}
