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
 * Created by mkostiuk on 18/07/2017.
 */
public class LecteurXmlPage {

    private String udn;
    private String numPage;

    public LecteurXmlPage(String xml) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {

            boolean isUdn = false;
            boolean isPage = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equalsIgnoreCase("UDN"))
                    isUdn = true;
                if (qName.equalsIgnoreCase("PAGE"))
                    isPage = true;
            }


            @Override
            public void characters(char ch[], int start, int length) {
                if (isUdn) {
                    isUdn = false;
                    udn = new String(ch, start, length);
                }
                if (isPage) {
                    isPage = false;
                    numPage = new String(ch,start,length);
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

}
