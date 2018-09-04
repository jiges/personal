package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 三数求和问题解决方案
 * 1、排序。( 时间复杂度n*lg(n) )
 * 2、将数组分成两份（负数和非负数），分组后两数组长度分别为a,b(a + b = n)
 * 3、在两份数组中个取一个数（共有a*b中组合）
 * 4、二分查找法找出剩余的数（时间复杂度 lg(n) ）
 * 总体时间复杂度 n*lg(n) + a * b * lg(n)
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        //分组
        int middleIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= 0) {
                middleIndex = i;
                break;
            }
        }

        for(int i = 0; i < middleIndex; i ++) {
            for (int j = middleIndex; j < nums.length; j++) {
                //二分法查找
                List<Integer> ret = binarySearch(nums,0 - nums[i] - nums[j]);
                for (int k = 0; k < ret.size(); k++) {
                    int index;
                    if((index = ret.get(k)) != i && index != j) {
                        result.add(Arrays.asList(nums[i],nums[j],nums[index]));
                    }
                }
            }
        }


        return result;
    }

    //二分法查找包含重复元素的取出所有元素下标
    public static List<Integer> binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        List<Integer> result = new ArrayList<>();

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];



            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else {
                //判断前后元素能不能命中
                result.add(mid);
                int mid_down = mid;
                int mid_up = mid;
                while (--mid_down >= low) {
                    if(a[mid_down] == key) {
                        result.add(mid);
                    } else {
                        break;
                    }
                }
                while (++ mid_up  <= high) {
                    if(a[mid_up] == key) {
                        result.add(mid);
                    } else {
                        break;
                    }
                }
                return result;
            }

        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(arr));
        System.out.println(10 % 10);
    }
}
