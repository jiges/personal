package segmentfault.Question1010000016661146;

import java.util.*;

public class DataPicker {

    //总分(points)最高
    private int sumPoints = 0;

    //credits的总和在100分之内（包含100）
    private float sumCredits = 0.0f;

    //每个位置(position)的人数限制
    //position-1 : 1 position-2 : 3-5 position-3 : 1-3 position-4 : 3-5
    private int cntPosition_1 = 0;
    private int cntPosition_2 = 0;
    private int cntPosition_3 = 0;
    private int cntPosition_4 = 0;

    //每队(team)人数不能超过7人,Map<teamId,count>
    private Map<Integer,Integer> cntTeam;

    //拿出n条数据
    private int dataCountToPick;

    //已选择的数据
    private Stack<Data> dataPicked;

    public DataPicker(int dataCountToPick) {
        this.dataCountToPick = dataCountToPick;
        cntTeam = new HashMap<>();
        dataPicked = new Stack<>();
    }

    public List<Data> pick(List<Data> source) {
        //首先对source排序，按照points倒序排
        source.sort(Comparator.comparing(Data::getPoints).reversed());
        if(pickData(0,source)) {
            return dataPicked;
        } else {
            //该输入无解
            return null;
        }
    }

    /**
     * 回溯递归函数，先构建解空间，再深度遍历，第一个解就是最优解
     * 设输入为(a1,a2......an),解空间的条件f(n) = checkCondition
     * 解空间树：
     *                   a1          ......             an
     *                 /|||\         ......           /|||\
     *               a2.....an       ......          a1....an-1
     *             /|||\   /|||\     ......          .......
     *           a3...an  a2....an-1 ......          .......
     *           .......
     * @param deep 搜索深度
     * @param source 数据源
     * @return 返回 false 说明这个分支下面无路可走
     */
    private boolean pickData(int deep,List<Data> source) {

        //结束递归
        if(deep >= dataCountToPick) return true;

        //逐个验证，找到一个符合条件的最优解
        for (int i = 0; i < source.size(); i++) {
            Data data = source.get(i);
            //判断这个局部解是否符合条件，若符合，入栈并继续向下查找
            if(checkCondition(data)) {
                sumPoints += data.getPoints();
                sumCredits += data.getCredits();

                cntTeam.put(data.getTeam(),Optional.ofNullable(cntTeam.get(data.getTeam())).orElse(0) + 1);
                dataPicked.push(data);
                switch (data.getPosition()) {
                    case 1: cntPosition_1 ++;break;
                    case 2: cntPosition_2 ++;break;
                    case 3: cntPosition_3 ++;break;
                    case 4: cntPosition_4 ++;break;
                }
                //继续向下查找，若返回false，说明需要回溯，并且向下查找
                boolean result = pickData(deep + 1,source);
                if(!result) {
                    //回溯
                    sumPoints = sumPoints - data.getPoints();
                    sumCredits = sumCredits - data.getCredits();

                    cntTeam.put(data.getTeam(),cntTeam.get(data.getTeam()) - 1);
                    dataPicked.pop();
                    switch (data.getPosition()) {
                        case 1: cntPosition_1 --;break;
                        case 2: cntPosition_2 --;break;
                        case 3: cntPosition_3 --;break;
                        case 4: cntPosition_4 --;break;
                    }
                    //继续寻找下一个解
                } else {
                    return true;
                }

            }
        }
        //未找到合适的解
        return false;
    }


    /**
     * 判断每一步的解是否符合条件
     * 1、未被选取过
     * 2、sumCredits <= 100
     * 3、每队(team)人数不能超过7人 cntTeam <= 7
     * 4、Position_1位置至少需要1人且不能超过1人，Position_2位置至少需要3人且不能超过5人，
     *    Position_3位置至少需要1人且不能超过3人，Position_4位置至少需要3人且不能超过5人。
     * @return true,该记录在解空间内，false，该记录不在解空间内
     */
    private boolean checkCondition(Data data){
        //从未被选取过的
        if (dataPicked.contains(data)) {
            return false;
        }
        // credits <= 100 - sumCredits
        if (data.getCredits() > 100 - sumCredits) {
            return false;
        }

        //每队(team)人数不能超过7人 cntTeam <= 7
        if(Optional.ofNullable(cntTeam.get(data.getTeam())).orElse(0) + 1 > 7) {
            return false;
        }

        //剩余次数
        int rest = dataCountToPick - cntPosition_1 - cntPosition_2 - cntPosition_3 - cntPosition_4 - 1;
        switch (data.getPosition()) {
            case 1:
                if(cntPosition_1 + 1 > 1 || rest < 3 - cntPosition_2 || rest < 1 - cntPosition_3 || rest < 3 - cntPosition_4) {
                    return false;
                }
                break;
            case 2:
                if(cntPosition_2 + 1 > 5 || rest < 1 - cntPosition_1 || rest < 1 - cntPosition_3 || rest < 3 - cntPosition_4) {
                    return false;
                }
                break;
            case 3:
                if(cntPosition_3 + 1 > 3 || rest < 1 - cntPosition_1 || rest < 3 - cntPosition_2 || rest < 3 - cntPosition_4) {
                    return false;
                }
                break;
            case 4:
                if(cntPosition_4 + 1 > 5 || rest < 1 - cntPosition_1 || rest < 3 - cntPosition_2 || rest < 1 - cntPosition_3) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        DataPicker picker = new DataPicker(11);
        System.out.println("输入数据:");
        Data.getStaticData().forEach(System.out::println);
        List<Data> picked = picker.pick(Data.getStaticData());
        System.out.println("选取的数据：");
        if(picked != null) {
            picked.forEach(System.out::println);
            System.out.println(String.format("sumPoints:%d, sumCredits:%-5.2f,\n " +
                    "position-1 picked(1): %-2d, position-2 picked(3-5): %-2d, " +
                    "position-3 picked(1-3): %-2d, position-4 picked(3-5): %-2d \n team picked(less than 7):%s",
                    picker.sumPoints,picker.sumCredits,picker.cntPosition_1,picker.cntPosition_2,picker.cntPosition_3,picker.cntPosition_4,picker.cntTeam));
        } else {
            System.out.println("该输入无解");
        }

    }

}
