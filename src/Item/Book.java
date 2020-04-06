/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author User
 */
public class Book extends Item{
    private String ISBN;
    private String publisher;
    
    public Book(String ISBN, String publisher){
        this.ISBN = ISBN;
        this.publisher = publisher;
    }
    
    public String getISBN(){
        return ISBN;
    }
    
    public String getPublisher(){
        return publisher;
    }
    
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" + "ISBN=" + ISBN + ", publisher=" + publisher + '}';
    }
    

}
