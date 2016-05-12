package com.company.xmlrecords;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by margusra on 11.05.2016.
 */
public class Sprints {
    private int sprintCount;
    private int totalPoints;
    private float totalSprintAverage;

    public void totalDevStoryPoints(String devName) throws IOException, SAXException, ParserConfigurationException {
        BurndownData data = new BurndownData();
        List<String> points = data.getTaskPoints("task","burned_points",devName);
        setTotalPoints();

        for(String point : points){
            totalPoints = totalPoints + Integer.parseInt(point);
        }
    }

    public void devSprintCount(String devName) throws ParserConfigurationException, SAXException, IOException {
        BurndownData data = new BurndownData();
        sprintCount = data.attributeCountPerNode("sprint",devName);
    }

    //You need to explicitly call <totalDevStoryPoints> & <devSprintCount> before you can use the calculation
    public void calculateAverage() {
        totalSprintAverage = Math.round((float)totalPoints / (float)sprintCount);
    }

    private void setTotalPoints(){
        totalPoints = 0;
    }

    public Integer getTotalPoints(){
        return totalPoints;
    }

    public int getSprintCount(){
        return sprintCount;
    }

    public float getTotalSprintAverage(){
        return totalSprintAverage;
    }
}
