package com.company;

import com.company.htmlgenerator.HtmlGenerator;
import com.company.xmlrecords.*;
import freemarker.template.TemplateException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TemplateException {

        List<Tasks> devTasks = new ArrayList<Tasks>();
        BurndownData data = new BurndownData();
        Developers dev = new Developers();
        Sprints sprint =  new Sprints();
        HtmlGenerator html = new HtmlGenerator();

        //delete old template
        html.deleteOldTemplate();
        //get developers count, we need to know
        data.setTag("developer");
        data.setTagCount();
        int devCount = data.getCount();

        //We know from above that we have 3 developers that means we will need to create 3 Tasks objects
        //Loop it 3 times and store them in List of objects <devTasks>, which is later accessible.
        for(int i = 0; i < devCount;i++){
            Tasks tasks = new Tasks();
            dev.setDevName();
            String devName = dev.getDev().get(i);
            tasks.setDev(devName);
            tasks.setDevTasks();
            devTasks.add(tasks);
        }

        //This is the final section to test all data and generates the final html file for index to use
        for(Tasks task : devTasks){
            System.out.println("Developer Name: "+task.getDev());
            System.out.println("Developer tasks: "+task.getTasks());
            sprint.totalDevStoryPoints(task.getDev());
            System.out.println("Total Story points : [" + sprint.getTotalPoints() +"]");
            sprint.devSprintCount(task.getDev());
            sprint.calculateAverage();
            System.out.println("Total average Story points per " + sprint.getSprintCount() + " Sprints: " + sprint.getTotalSprintAverage());
            html.addDeveloperToTemplate(task.getDev(),task.getTasks(),sprint.getSprintCount(),sprint.getTotalPoints(),Math.round(sprint.getTotalSprintAverage()));
        }

    }
}
