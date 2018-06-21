/**
 * Created by ccr at 2018/5/16.
 */
public class TestHashOfMap {

    public static void main(String[] args) {
        String s = "abcde";
        int h = s.hashCode();
        System.out.print("hashCode:" + h + " 二进制:");
        printBinaryStringWithZero(h);
        int mapHash = hash(s);
        System.out.print(" mapHash:" + mapHash + " 二进制:");
        printBinaryStringWithZero(mapHash);
    }

    static final int hash(Object key) {
        int h = key.hashCode();
        printBinaryStringWithZero(h);
        printBinaryStringWithZero(h>>>16);
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    static void printBinaryStringWithZero(int h) {
        String bynaryString = Integer.toBinaryString(h);
        int dis = 32 - bynaryString.length();
        String zero = "";
        if(dis > 0) {
            char[] zeros = new char[dis];
            for (int i = 0; i < dis; i++) {
                zeros[i] = '0';
            }
            zero = new String(zeros);
        }
        System.out.println(zero + bynaryString);
    }



}
