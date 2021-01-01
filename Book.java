/**
 * class that extends products class, and contains the attributes of the book product 
 * @author Harir Al-Rubaye (1108085)
 * @since 05-10-2020
 */
package eStoreSearch;

public class Book extends Products {
    private String Author;
    private String publisher;
    private double price;
    public Book(String productID, String description, double price, int year, String Author,String publisher) throws Exception {
        super(productID,description,price,year);
        this.Author = Author;
        this.publisher = publisher;
    }
    /**
     * getter function
     * @return Author
     */
    public String getAuthor() {
        return Author;
    }
    /**
     * author mutator
     * @param author
     */
    public void setAuthor(String author) {
        if (author == null)
            throw new IllegalArgumentException("Invalid string for author");
        else {
            Author = author;
        }
    }
    /**
     * getter function
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }
    /**
     * publisher mutator
     * @param publisher
     */
    public void setPublisher(String publisher) {
        if (publisher == null)
            throw new IllegalArgumentException("Invalid string for publisher");
        else {
            this.publisher = publisher;
        }
    }
    /**
     * overrides toString method
     */
    public String toString(){
        return "\ntype = book\n"+ "productID = " + productID +"\nkeyword = "+description + "\nyear = " + year +"\nprice ="+ price+ "\nauthor = "+ Author +"\npublisher = "+ publisher+"\n";
    }

}
