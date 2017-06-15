package po;

import java.util.ArrayList;

/**
 * Created by JenKin on 2017/6/10.
 */

public class CustomGrade extends Grade {
    private ArrayList<CustomUser> customUserArrayList;

    public ArrayList<CustomUser> getCustomUserArrayList() {
        return customUserArrayList;
    }

    public void setCustomUserArrayList(ArrayList<CustomUser> customUserArrayList) {
        this.customUserArrayList = customUserArrayList;
    }

}
