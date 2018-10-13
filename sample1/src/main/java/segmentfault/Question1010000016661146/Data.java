package segmentfault.Question1010000016661146;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private int id;
    private int points;
    private float credits;
    private int position;
    private int team;

    public Data(int id, int points, float credits, int position, int team) {
        this.id = id;
        this.points = points;
        this.credits = credits;
        this.position = position;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public float getCredits() {
        return credits;
    }

    public int getPosition() {
        return position;
    }

    public int getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Data)obj).getId();
    }

    @Override
    public String toString() {
        return String.format("%-3d %-3d %-4.1f %-3d %-3d",this.id,this.points,this.credits,this.position,this.team);
    }

    /**
     * 返回一个倒序排的 dataList
     * @return List<Data>
     */
    public static List<Data> getStaticData(){
        List<Data> dataList = new ArrayList<>();
        int i = 0;
        dataList.add(new Data(++i,56, 9.0f, 1,1));
        dataList.add(new Data(++i,54, 9.1f, 2,1));
        dataList.add(new Data(++i,35, 8.0f, 2,2));
        dataList.add(new Data(++i,32, 8.0f, 3,1));
        dataList.add(new Data(++i,20, 8.5f, 1,2));
        dataList.add(new Data(++i,18, 9.0f, 3,2));
        dataList.add(new Data(++i,16, 9.1f, 2,2));
        dataList.add(new Data(++i,15, 8.0f, 3,2));
        dataList.add(new Data(++i,13, 7.0f, 3,1));
        dataList.add(new Data(++i,10, 8.5f, 4,2));
        dataList.add(new Data(++i,7, 9.0f, 4, 2));
        dataList.add(new Data(++i,5, 8.0f, 2, 1));
        dataList.add(new Data(++i,4, 7.0f, 3, 1));
        dataList.add(new Data(++i,3, 8.5f, 4, 1));
        dataList.add(new Data(++i,2, 9.0f, 3, 1));
        dataList.add(new Data(++i,1, 8.5f, 4, 2));
        dataList.add(new Data(++i,0, 9.0f, 3, 2));
        dataList.add(new Data(++i,0, 8.5f, 4, 1));
        dataList.add(new Data(++i,0, 9.0f, 3, 2));
        dataList.add(new Data(++i,0, 8.5f, 4, 1));
        dataList.add(new Data(++i,0, 9.0f, 3, 1));
        dataList.add(new Data(++i,0, 8.5f, 4, 2));
        return dataList;
    }
}
