package airbnb2019;

import java.util.ArrayList;
import java.util.List;

public class CSVParser {
	public static void main(String args[]) {
		System.out.println("c:\\windows\\system32");
		String s = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1\"\"\"Alexandra Alex\"\"\"";
		System.out.println(parseCSV(s));
	}

	public static String parseCSV(String str) {
		List<String> res = new ArrayList<String>();
		boolean inQuote = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (inQuote) {
				if (str.charAt(i) == '\"') {
					if (i + 1 < str.length() && str.charAt(i + 1) == '\"') {
						sb.append('\"');
						i++;
					} else {
						inQuote = false;
					}
				} else {
					sb.append(str.charAt(i));
				}
			} else {
				if (str.charAt(i) == '\"') {
					inQuote = true;
				} else if (str.charAt(i) == ',') {
					res.add(sb.toString());
					sb.setLength(0);
				} else {
					sb.append(str.charAt(i));
				}
			}
		}

		if (sb.length() > 0) {
			res.add(sb.toString());
		}
//		return String.join("|", res);
		sb.setLength(0);
		for (String s : res) {
			sb.append(s).append("|");
		}
		return sb.toString();
	}
}
