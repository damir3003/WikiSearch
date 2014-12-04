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
import java.util.Comparator;
import java.util.HashMap;
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
   
    
    public SearchPage(String searchString){
        this.searchString=searchString;
    }
    
    @Override
    public String toString(){
        
        return find(textMap);
    }
    
    public String find(Map<String,Set<PageTitle>> map){
        
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
        System.out.println(entriesSortedByValues(resultMap));
      
        
        String returnString="";
        
        int resultCount=resultMap.size()>10?10:resultMap.size();
        
      
        return resultMap.keySet().toString();
    }
    
    static <K,V extends Comparable<? super V>>
SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
        new Comparator<Map.Entry<K,V>>() {
            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
               int res = e2.getValue().compareTo(e1.getValue());
               return res;
            }
        }
    );
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
}
   
}
