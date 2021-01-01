/**
 * class that extends products class and has attributes of the electronics products 
 * @author Harir Al-Rubaye (1108085)
 * @since 05-10-2020
 */
package eStoreSearch;

public class Electronics extends Products {
    private String maker;
    private double price;
    public Electronics (String productID, String description, double price, int year, String maker) throws Exception{
        super(productID,description,price,year);
        this.maker = maker;
    }
    /**
     * getter function
     * @return maker
     */
    public String getMaker() {
        return maker;
    }
    /**
     * maker mutator
     * @param maker
     */
    public void setMaker(String maker) {
        if (maker == null)
            throw new IllegalArgumentException("Invalid string for maker");
        else {
            this.maker = maker;
        }
    }
    
    /**
     * override toString method
     */
    public String toString() {

        return "\ntype = electronic\n"+"productID = " + productID +"\nkeyword = "+ description + "\nyear = " + year +"\nprice = "+ price+ "\nmaker = " +maker + "\n";
    }
}
