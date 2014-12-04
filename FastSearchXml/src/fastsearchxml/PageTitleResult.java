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
public class PageTitleResult {
    
    private PageTitle title;
    private int count=0;
    
    public PageTitleResult(PageTitle title){
        this.title=title;
        count++;
    }

    
    public PageTitle getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }
    
    public void incrementCount(){
        count++;
    }
    
    @Override
    public String toString(){
        return title.toString();
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof PageTitleResult){
            return this.title.equals(((PageTitleResult)o).getTitle());
        }
        return false;
        
    }

    @Override
    public int hashCode() {     
        return getTitle().hashCode();
    }
    
}
