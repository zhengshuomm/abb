import java.util.ArrayList;
import java.util.List;
//打印一个string数组，要求每行不能超过max长度，不到max，就在最后space，然后打印出来。结果应该是下面这样的
//+----------+
//+this is an +
//+apple  	+

public class SimpleTextJustification {

	public static void main(String[] args) {
		String str = "this is an apple";
		int max = 11;
		System.out.println(justify(str.split(" "), max));
	}

	private static List<String> justify(String[] words, int max) {
		List<String> result = new ArrayList<>();
		int start = 0;
		while (start < words.length) {
			StringBuilder sb = new StringBuilder(words[start]);
			int end = start + 1;
			while (end < words.length) {
				if (sb.length() + words[end].length() + 1 > max) break;
				sb.append(" ").append(words[end]);
				end ++;
			}
			while (sb.length() < max) {
				sb.append("*");
			}
			result.add(sb.toString());
			start = end;
		}
		return result;
	}
}
