/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastsearchxml;


import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 *
 * @author Husadzicd
 */
public class FastSearchXml {

    /**
     * @param args the command line arguments
     */
   public static Map<String,Set<PageTitle>> titleMap = new TreeMap<>();
   public static Map<String,Set<PageTitle>> textMap = new TreeMap<>();
    public static void main(String[] args) {
      try {
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
      
        
        String match="Damir";
        DefaultHandler handler2 = new CreateIndexForXml();
 

       saxParser.parse("C:\\Users\\husadzicd\\Desktop\\xml\\wikipedia.xml", handler2);
          System.out.println("Title count:"+titleMap.size());
              System.out.println("Text count:"+textMap.size());   
       System.out.println("Kreiranje indeksa gotovo");
          System.out.println("----------------------------");
         
      InputStreamReader inputStreamReader = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(inputStreamReader);
       while(true){
           System.out.println("Unesite pojam za pretragu:");
           System.out.println(new SearchPage(reader.readLine()));
           
           
       }
      
 
     } catch (Exception e) {
       e.printStackTrace();
     }
    }
    
}
