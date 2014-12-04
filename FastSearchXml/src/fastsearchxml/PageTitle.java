/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastsearchxml;

/**
 *
 * @author Husadzicd
 */
public class PageTitle {
    
    private String title;
    private int numOfWords;
    
    public PageTitle(String title){
        this.title=title.trim();
        this.numOfWords=(this.title.split("\\W")).length;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfWords() {
        return numOfWords;
    }
    
    @Override
    public String toString(){
        return title;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof PageTitle){
            return this.title.equals(((PageTitle)o).getTitle());
        }
        return false;
        
    }

    @Override
    public int hashCode() {     
        return getTitle().hashCode();
    }
    
    
    
}
