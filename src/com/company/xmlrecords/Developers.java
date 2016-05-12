package com.company.xmlrecords;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by margusra on 10.05.2016.
 */
public class Developers {
    private List<String> names;

    public void setDevName() throws IOException, SAXException, ParserConfigurationException {
        BurndownData data = new BurndownData();
        names = data.developerName();
    }

    public List<String> getDev() {
        return names;
    }
}
