package airbnb2019;

import java.util.HashSet;
import java.util.List;

public class PyramidTransitionMatrix {

	// LC 756
    String[] letter = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        HashSet<String> set = new HashSet<>();
        HashSet<String> cache = new HashSet<>();
        for (String s : allowed) set.add(s);
        return helper(set,cache, bottom,  "", 0);
    }
    
    private boolean helper(HashSet<String> set, HashSet<String> cache, String bottom, String next, int start) {
        // base case
        if (bottom.length() == 1) return true;
        // calculate the next row
        if (start == bottom.length() - 1) {
            // do not caculate the same string again.
            if (cache.contains(next)) return false;
            cache.add(next);
            return helper(set, cache, next, "", 0);
        }
        String t = bottom.substring(start, start + 2);
        for (int j = 0; j < letter.length; j++) {
            if (!set.contains(t + letter[j])) continue;
            if (helper(set,cache, bottom, next + letter[j], start + 1)) {
                return true;
            }
        }
        return false;
    }  
}
