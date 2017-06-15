package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JenKin on 2017/6/6.
 */

public class Grade implements Serializable {
    private float failGrade,
                    passGrade,
                    averageGrade,
                    creditGrade,
                    excellentGrade,
                    sum,
                    max,
                    min,
                    average,
                    failPercent,
                    creditPercent,
                    myScore;

    private ArrayList<Float> listFloat;
    public Grade() {
        super();
    }

    public Grade(float failGrade, float passGrade, float averageGrade, float creditGrade, float excellentGrade) {
        super();
        this.failGrade = failGrade;
        this.passGrade = passGrade;
        this.averageGrade = averageGrade;
        this.creditGrade = creditGrade;
        this.excellentGrade = excellentGrade;
    }

    public Grade(ArrayList<Float> listFloat) {
        this.listFloat = listFloat;
        if (listFloat!=null || !listFloat.isEmpty())
        math();
    }

    public void setData(ArrayList<CustomUser> arrayList, String userId)
    {
        this.listFloat = new ArrayList<Float>();
        for (CustomUser cu : arrayList)
        {
            if (cu.getId().trim().equals(userId.trim()))
                myScore = cu.getGrade();
            listFloat.add(Float.parseFloat(cu.getGrade()+"f"));
        }
        if (listFloat!=null || !listFloat.isEmpty())
            math();
    }

    public float getMyScore() {
        return myScore;
    }

    public void setMyScore(float myScore) {
        this.myScore = myScore;
    }

    public float getFailGrade() {
        return failGrade;
    }

    public void setFailGrade(float failGrade) {
        this.failGrade = failGrade;
    }

    public float getPassGrade() {
        return passGrade;
    }

    public void setPassGrade(float passGrade) {
        this.passGrade = passGrade;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public float getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(float creditGrade) {
        this.creditGrade = creditGrade;
    }

    public float getExcellentGrade() {
        return excellentGrade;
    }

    public void setExcellentGrade(float excellentGrade) {
        this.excellentGrade = excellentGrade;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getFailPercent() {
        return failPercent;
    }

    public void setFailPercent(float failPercent) {
        this.failPercent = failPercent;
    }

    public float getCreditPercent() {
        return creditPercent;
    }

    public void setCreditPercent(float creditPercent) {
        this.creditPercent = creditPercent;
    }

    public ArrayList<Float> getListFloat() {
        return listFloat;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public void setListFloat(ArrayList<Float> listFloat) {
        this.listFloat = listFloat;
    }

    public float countSum(){
        return failGrade + passGrade + averageGrade + creditGrade + excellentGrade;
    }

    public void math(){

        max = 0;
        min = listFloat.get(0);
        for ( float f : listFloat)
        {
            sum += f;
            if (f>max)
                max = f;
            if (f<min)
                min = f;
            switch (((int)f)/10)
            {
                case 6:
                    passGrade++;
                    break;
                case 7:
                    averageGrade++;
                    break;
                case 8:
                    creditGrade++;
                    break;
                case 9:
                case 10:
                    excellentGrade++;
                    break;
                default:
                    failGrade++;
            }

            setAverage(sum/listFloat.size());
            setFailPercent(failGrade/listFloat.size());
            setCreditPercent((creditGrade+excellentGrade)/listFloat.size());
        }

    }

    @Override
    public String toString() {
        return "Grade{" +
                "failGrade=" + failGrade +
                ", passGrade=" + passGrade +
                ", averageGrade=" + averageGrade +
                ", creditGrade=" + creditGrade +
                ", excellentGrade=" + excellentGrade +
                ", sum=" + sum +
                ", max=" + max +
                ", min=" + min +
                ", average=" + average +
                ", failPercent=" + failPercent +
                ", creditPercent=" + creditPercent +
                ", myScore=" + myScore +
                ", listFloat=" + listFloat +
                '}';
    }
}
