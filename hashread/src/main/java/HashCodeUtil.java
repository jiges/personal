import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccr at 2018/5/16.
 */
public class HashCodeUtil {
    /**
     * 列举 hashCode 为 h 长度为2的字符串
     * @param h hashCode h <= 127*31 + 127
     * @return List<String>
     */
    public static List<String> transferHashCode2String(int h){
        if(h > 127*31*31*31 + 127 * 31*31 + 127*31 + 127) {
            return null;
        }
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < 127; i++) {
            if(i == h) {
                ret.add(String.valueOf(new char[]{(char)i}));
            }
            for (int j = 0; j < 127; j++) {
                if(31 * i + j  == h) {
                    ret.add(String.valueOf(new char[]{(char)i,(char)j}));
                }
                for (int k = 0; k < 127; k++) {
                    if(31 * i * 31 + 31 * j + k == h) {
                        ret.add(String.valueOf(new char[]{(char)i,(char)j,(char)k}));
                    }
                    for (int l = 0; l < 127; l++) {
                        if(31 * i * 31 * 31 + 31 * 31 * j + 31 * k + l == h) {
                            ret.add(String.valueOf(new char[]{(char)i,(char)j,(char)k,(char)l}));
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int h = "abcd".hashCode();
        System.out.println(transferHashCode2String(h));
    }
}
