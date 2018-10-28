package leetcode.problem504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://leetcode-cn.com/problems/perfect-number/
 */
class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num == 0) return true;
        return false;
    }
}

public class MainClass {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            boolean ret = new Solution().checkPerfectNumber(num);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}