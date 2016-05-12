package com.company.xmlrecords;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by margusra on 10.05.2016.
 */
//To use this class as a object later we need to first specify the developer
//After that we can call setDevTasks(), which will store the given tasks per developer in a list <tasks>
//We for now assume that story points are always present. This needs to be changed in case Points are missing
public class Tasks {
    private String developer;
    private List<String> tasks;

    //This method will also set the task count.
    public void setDevTasks() throws IOException, SAXException, ParserConfigurationException {
        BurndownData data = new BurndownData();
        tasks = data.getTasksByName("task","name",developer);
    }

    public void setDev(String developer){
        this.developer = developer;
    }

    public String getDev(){
        return developer;
    }

    //returns
    public List<String> getTasks(){
        String devTasks = "";
        for(String task : tasks){
            devTasks = devTasks + "," + task;
        }
        return tasks;
    }
}
