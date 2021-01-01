/**
 * A class that contains all the shared attributes between the different types of products available 
 * @author Harir Al-Rubaye (1108085)
 * @since 05-10-2020
 */
package eStoreSearch;


public abstract class Products {
    protected String productID;
    protected String description;
    protected int year;
    protected double price;
    
    public Products (String productID, String description, double price, int year) throws Exception{
        this.productID = productID;
        this.description = description;
        this.price = price;
        this.year = year;
    }
   /**
     * getter function
     * @return product id 
     */
    public String getproductID() {
        return productID;
    }
    /**
     * mutator ID
     * @param productID
     */
    public void setProductID(String productID) {
        if (productID == null)
            throw new IllegalArgumentException("Invalid value for ID");
        else {
            this.productID = productID;
        }   
    }
    /**
     * getter function
     * @return keyword
     */
    public String getDescription() {
        return description;
    }
    /**
     * description mutator
     * @param description
     */
    public void setDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException("Invalid String for description");
        else {
            this.description = description;
        }
    }
    /**
     * getter function
     * @return year
     */
    public int getYear() {
        return year;
    }
    /**
     * year mutator
     * @param year
     */
    public void setYear(int year) {
        if (year <= 0)
            throw new IllegalArgumentException("Invalid value for year");
        else {
            this.year = year;
        }
    }
    /**
     * getter function
     * @return price
     */
    public double getPrice() {
        return price;
    }
    /**
     * year mutator
     * @param price
     */
    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Invalid value for price");
        else {
            this.price = price;
        }
    }

    /**
     * override equals method
     * @param attribute takes a product object 
     * @return true if its equal and false if not 
     */
    public boolean equals(Products attribute){
        if (description.equalsIgnoreCase(attribute.getDescription()) 
        && year == attribute.getYear() && productID == attribute.getproductID())
        return true;
        else {
            return false;
        }
    }
     /**
     * override equals method to check if there is a match
     * @param productID the serial number of a product
     * @param startYear the lower limit for the year range 
     * @param endYear the upper limit for year range
     * @param description  keywords from the original descriptions to look for matches 
     * @return true if matches have been found 
     */
    public boolean equals(String productID, int startYear, int endYear, String description){
            // for all of them
            if(productID.length() != 0 && !description.equals("") &&  startYear != 0 && endYear !=0){
                return Service.matchString(description,getDescription())
                && startYear <= getYear() && endYear >= getYear() && productID.equals(getproductID());
            }
            // for productID and description
            else if(productID.length() != 0 && !description.equals("") && startYear == 0 && endYear ==0){
                return Service.matchString(description,getDescription())
                && productID.equals(getproductID());
            }
            // for description and year
            else if(productID.length() == 0 && !description.equals("") &&  startYear != 0 && endYear !=0){
                return Service.matchString(description,getDescription())
                &&startYear <= getYear() && endYear >= getYear();
            }
            // for productID and year
            else if(productID.length() != 0 && description.equals("") &&  startYear != 0 && endYear !=0){
                return(startYear <= getYear() && endYear >= getYear())
                && productID.equals(getproductID());
            }
            // for year alone
            else if(productID.length() == 0 && description.equals("") && startYear != 0 && endYear !=0){
                return(startYear <= getYear() && endYear >= getYear());
            }
            //for productID alone
            else if(productID.length() != 0 && description.equals("") && startYear == 0 && endYear ==0){
                return productID.equals(getproductID());
            }
            //for description alone
            else if(productID.length() == 0 && !description.equals("") && startYear == 0 && endYear ==0){
                return Service.matchString(description,getDescription());
            }
           return true;
    }
   
}
