package leetcode.problem3;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        //滑动窗口法
        int i= 0,j = -1;
        char[] charArr = s.toCharArray();
        //窗口中的字符
        Set<Character> window = new HashSet<>();
        int charCount = 0;
        while (j < charArr.length - 1) {
            //滑动时与窗口中的字符重复，则左侧缩小到重复位置
            if (window.contains(charArr[++ j])) {
                while (charArr[i] != charArr[j]) {
                    window.remove(charArr[i ++]);
                }
                window.remove(charArr[i ++]);
            }
            window.add(charArr[j]);
            int cnt;
            if((cnt = j - i + 1) > charCount) {
                charCount = cnt;
            }
        }
        return charCount;
    }

    public static void main(String[] args) {
        String input = "alqebriavxoo";
        System.out.println(lengthOfLongestSubstring(input));
    }
}
