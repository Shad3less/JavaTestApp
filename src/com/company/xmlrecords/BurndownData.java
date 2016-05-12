package com.company.xmlrecords;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.org.apache.xerces.internal.dom.ParentNode;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 * Created by margusra on 10.05.2016.
 */
public class BurndownData {
    //this is count of a certain tag that we need later
    private int tagCount;
    private final File xmlFile = new File(System.getProperty("user.dir")+ "/resource/input_data.xml");
    private String searchTag;

    public void setTagCount(){
        try {
            File fXmlFile = xmlFile;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList tagList = doc.getElementsByTagName(searchTag);
            tagCount = tagList.getLength();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public List<String> developerName() throws ParserConfigurationException, IOException, SAXException {

        List<String> dev = new ArrayList<String>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                dev.add(elem.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
            }
        }
        return dev;
    }

    //We search for each developer and their tag <task>, in this case it is called childTag.
    //This is good for future to be able to reuse such cases.
    public List<String> getTasksByName(String childTag,String subElement,String devName) throws ParserConfigurationException, IOException, SAXException {

        List<String> tasks = new ArrayList<String>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
        NodeList nList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if(node.getNodeType() == 1){
               Element elem = (Element) node;
               String dev = elem.getElementsByTagName("name").item(0).getTextContent();
               if(dev.equals(devName)){
                   int taskLenght = childTagCount(childTag,elem);
                   //We start from 1 because we need to skip the <NAME> tag of developer.
                   for(int j = 1; j <= taskLenght; j++){
                       String taskName = elem.getElementsByTagName(subElement).item(j).getChildNodes().item(0).getTextContent();
                       tasks.add(taskName);
                   }
               }
            }
        }
        return tasks;
    }

    //Pretty much as as <getTasksByName> only difference is that we don't have duplicate tag name in this case.
    public List<String> getTaskPoints(String childTag,String subElement,String devName)throws ParserConfigurationException, IOException, SAXException {

        List<String> points = new ArrayList<String>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
        NodeList nList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if(node.getNodeType() == 1){
                Element elem = (Element) node;
                String dev = elem.getElementsByTagName("name").item(0).getTextContent();
                if(dev.equals(devName)){
                    int taskLenght = childTagCount(childTag,elem);
                    //We start from 1 because we need to skip the <NAME> tag of developer.
                    for(int j = 0; j < taskLenght; j++){
                        String point = elem.getElementsByTagName(subElement).item(j).getChildNodes().item(0).getTextContent();
                        points.add(point);
                    }
                }
            }
        }
        return points;
    }

    public Integer attributeCountPerNode(String subElement,String devName) throws IOException, SAXException, ParserConfigurationException {
        int count = 0;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for(int i=0,size= nodeList.getLength(); i<size; i++) {
            Node n = nodeList.item(i);
            if(n.getNodeType() == 1){
                Element elem = (Element) n;
                String dev = elem.getElementsByTagName("name").item(0).getTextContent();
                if(dev.equals(devName)) {
                    count = childTagCount(subElement, elem);
                }
            }
        }
        return count;
    }

    private int childTagCount(String childTag, Element e){
        int count = e.getElementsByTagName(childTag).getLength();
        return count;
    }

    public int getCount(){
        return tagCount;
    }

    public void setTag(String tag){
        searchTag = tag;
    }
}
