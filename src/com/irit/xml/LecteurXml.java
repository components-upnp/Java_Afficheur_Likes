package com.irit.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by mkostiuk on 17/07/2017.
 */
public class LecteurXml {

    private String udn;
    private HashMap<String,String> likes;

    public LecteurXml(String xml) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();

        likes = new HashMap<>();

        DefaultHandler handler = new DefaultHandler() {

            boolean isUdn = false;
            boolean isPage = false;
            boolean isKey = false;
            boolean isNbLikes = false;
            String key = "";
            String nb = "";

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equalsIgnoreCase("UDN"))
                    isUdn = true;
                if (qName.equalsIgnoreCase("PAGE"))
                    isPage = true;
                if (qName.equalsIgnoreCase("KEY"))
                    isKey = true;
                if (qName.equalsIgnoreCase("NBLIKES"))
                    isNbLikes = true;
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase("PAGE"))
                    likes.put(key, nb);
            }

            @Override
            public void characters(char ch[], int start, int length) {
                if (isUdn) {
                    isUdn = false;
                    udn = new String(ch, start, length);
                }
                if (isKey) {
                    isKey = false;
                    key = new String(ch,start,length);
                }
                if (isNbLikes) {
                    isNbLikes = false;
                    nb = new String(ch,start,length);
                }
            }

        };
        sp.parse(new InputSource(new StringReader(xml)), handler);
    }

    public String getNumPage() {
        String page = new String();
        return page;
    }

    public String getUdn() {
        return udn;
    }

    public HashMap<String, String> getLikes() {
        return likes;
    }
}
