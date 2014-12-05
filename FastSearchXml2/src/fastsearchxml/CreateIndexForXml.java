/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastsearchxml;

import static fastsearchxml.FastSearchXml.textMap;
import static fastsearchxml.FastSearchXml.titleMap;
import java.io.CharArrayWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Husadzicd
 */
public class CreateIndexForXml extends DefaultHandler {

    boolean isTitle = false;
    boolean isText = false;
    boolean isPage = false;
    boolean isRedirected = false;
    boolean isTextExists = false;
    String title;
    String text;

    CharArrayWriter descriptionBuffer;

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("title")) {
            isTitle = true;
            descriptionBuffer = new CharArrayWriter();
        } else if (qName.equalsIgnoreCase("text")) {
            isText = true;
            isTextExists=true;
            descriptionBuffer = new CharArrayWriter();

        } else if (qName.equalsIgnoreCase("page")) {
            isPage = true;
            isRedirected = false;
            title = "";
            text="";
        }

    }

    
    private void addIndex(String text, Map<String,Set<PageTitle>> map ) {

        for (String word : text.split("\\W")) {
            String trimWord = word.trim().toLowerCase();
            if (!trimWord.isEmpty()) {
                if (map.containsKey(trimWord)) {
                    ((Set<PageTitle>) map.get(trimWord)).add(new PageTitle(title));

                } else {
                    Set<PageTitle> set = new HashSet<>();
                    set.add(new PageTitle(title));
                    map.put(trimWord, set);
                }
                
            }
        }

    }



    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {

        if (qName.equalsIgnoreCase("text")) {
            
            text = descriptionBuffer.toString();
            descriptionBuffer = null;
            isText=false;

        } else if (qName.equalsIgnoreCase("title")) {

            title = descriptionBuffer.toString();
            descriptionBuffer = null;
            isTitle=false;
        } else if(qName.equalsIgnoreCase("page")){
            if(isTextExists){
                addIndex(title, titleMap);
                if(!isRedirected){
                    addIndex(text,textMap);
                }
                
            }
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (isTitle) {
            descriptionBuffer.write(ch, start, length);
        }

        if (isText) {
            String s = new String(ch, start, length);
            if (s.trim().startsWith("#REDIRECT")) {
                isRedirected = true;
                isText = false;
            }
            descriptionBuffer.write(ch, start, length);

        }

    }

}
