package com.company.htmlgenerator;

import java.net.URL;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Created by margusra on 12.05.2016.
 */

public class HtmlGenerator {
    private final File file = new File(System.getProperty("user.dir")+ "/templates");
    private final String templateFile = "dev-template.ftl";
    private final File fullTemplatePath = new File(System.getProperty("user.dir")+ "/templates/" + "dev-template.html");

    public void addDeveloperToTemplate(String devName,List<String> tasks,int sprints,int totalSprintPoints, int average) throws IOException, TemplateException {

        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> map = new HashMap<>();
        map.put("developer", devName);
        map.put("totalPoints", totalSprintPoints);
        map.put("sprints", sprints);
        map.put("average",average);
        List<URLContext> tasksref = new ArrayList<>();

        for(String task : tasks){
            tasksref.add(new URLContext(task));
        }

        map.put("tasksref", tasksref);

        Template template = cfg.getTemplate(templateFile);

        Writer console = new OutputStreamWriter(System.out);
        template.process(map, console);
        console.flush();

        Writer appendFile = new FileWriter (fullTemplatePath,true);
        template.process(map, appendFile);
        appendFile.flush();
        appendFile.close();
    }

    public void deleteOldTemplate(){
        if(fullTemplatePath.exists()){
            fullTemplatePath.delete();
        }
    }
}

