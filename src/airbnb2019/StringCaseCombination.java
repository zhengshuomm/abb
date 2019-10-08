package airbnb2019;

import java.util.ArrayList;
import java.util.List;

public class StringCaseCombination {
	public static void main(String args[]) {
		String input = "aBc";
		List<String> result = caseCombinatio2n(input);
		for (String word : result) {
			System.out.println(word);
		}
	}
	
	private static List<String> caseCombination(String input) {
		List<String> result = new ArrayList<>();
		char[] chars = input.toCharArray();
		int n = chars.length;
		char[] temp = new char[chars.length];
		for (int i = 0 ;i < Math.pow(2, n) ; i ++) {
			for (int j = 0 ; j < n ;j ++) {
				int bit = (i >> j) & 1;
				temp[j] = bit == 1 ? Character.toUpperCase(chars[j]) : Character.toLowerCase(chars[j]);
			}
			result.add(new String(temp));
		}
		return result;
	}
	
	private static List<String> caseCombinatio2n(String input) {
		List<String> result = new ArrayList<>();
		dfs(input.toCharArray(), 0, result);
		return result;
	}
	
	private static void dfs(char[] chars, int index, List<String> result) {
		if (index == chars.length) {
			result.add(new String(chars));
			return;
		}
		
		chars[index] = Character.toLowerCase(chars[index]);
		dfs(chars, index + 1, result);
		chars[index] = Character.toUpperCase(chars[index]);
		dfs(chars, index + 1, result);
	}
}
