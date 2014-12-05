/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastsearchxml;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Husadzicd
 */
public class ResultComparator implements Comparator{
    
    private int wourdCount=0;

    public ResultComparator(int wourdCount) {
        this.wourdCount=wourdCount;
    }
    

    @Override
    public int compare(Object o1, Object o2) {
        /*Object o3=(((Map.Entry) (o2)).getValue());
        if(o3 instanceof Integer && (Integer) (o3)==wourdCount){
            return -1;
        }*/
        return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
    }
    
}
