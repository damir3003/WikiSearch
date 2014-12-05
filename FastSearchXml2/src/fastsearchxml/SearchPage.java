/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastsearchxml;

import static fastsearchxml.FastSearchXml.textMap;
import static fastsearchxml.FastSearchXml.titleMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Husadzicd
 */
public class SearchPage {
    
    private String searchString="";
    private int wourdCount=0;
   
    
    public SearchPage(String searchString){
        this.searchString=searchString;
        this.wourdCount=this.searchString.split("\\W").length;
    }
    
    @Override
    public String toString(){
        
        return startSearch();
    }
    
    public String startSearch(){
        
         Map<PageTitle,Integer> resultMap= find(titleMap);
         
          String returnString="";
        

        Map<PageTitle,Integer> sortedMap= sortByValues(resultMap);
        int i=0;
        int p=0;
        for(PageTitle title:sortedMap.keySet()){
            returnString+=title.toString()+"\n";
            i++;
            if(i>=10)
                break;
           
            
        }
        p=i;
        if (i<10){
            resultMap= find(textMap);
             sortedMap= sortByValues(resultMap);
        i=0;
        for(PageTitle title:sortedMap.keySet()){
            
            returnString+=title.toString()+"\n";
            i++;
            if(i>=10-p)
                break;
           
            
        }
        }
      
        return returnString;
        
    }
    
    private  HashMap sortByValues(Map map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       
       Collections.sort(list, new ResultComparator(wourdCount));
       

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }

    
    public Map<PageTitle,Integer> find(Map<String,Set<PageTitle>> map){
        
        Map<PageTitle,Integer> resultMap= new HashMap<>() ;
        
          for (String word : searchString.split("\\W")) {
            String trimWord = word.trim().toLowerCase();
            
            if (!trimWord.isEmpty()) {
                if(map.containsKey(trimWord)){
                for(PageTitle pageTitle:map.get(trimWord)){
                  
                   if(resultMap.containsKey(pageTitle)){
                      resultMap.put(pageTitle,resultMap.get(pageTitle)+1);
                       
                   }
                   else{
                       resultMap.put(pageTitle,1);
                   }
                   
                }
                }
            }
          }
       return resultMap;

    }
    

   
}
