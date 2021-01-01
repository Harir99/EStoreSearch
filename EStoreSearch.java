/**
 * This class contains the add and search methods, as well as any method that works on a list of products 
 * @author Harir Al-Rubaye (1108085)
 * @since 05-10-2020
 * @version 2
 */
package eStoreSearch;


import java.io.*;
import java.util.*;


public class EStoreSearch {
    private List<Products> products = new ArrayList <Products>();
    StringTokenizer wordsFactory;
    String GetUserInput = "";
    Products book;
    Products electronics;
    String SearchArray;


    /**
     *check if productID already exists in books or electronics 
     * @param productID unqiue serial number belongs to the product to be found
     * @return null if the productID does not exist
     */
    public Products SearchProductID(String productID){
       for(Products prod:products){
            if(prod.getproductID().equals(productID)){
                return prod;
            }
        }
        return null;
    }

    /**
     * This Method performs a search for a producton different combinations of the following attributes:
     *  The productID, year range and description 
     * @param productID the serial number of a product
     * @param startYear the lower limit for the year range 
     * @param endYear the upper limit for year range
     * @param description  keywords from the original descriptions to look for matches 
     * @return matching products 
     */
    public List<Products> search(String productID, int startYear, int endYear, String description){ 
        List<Products> matching = new ArrayList<Products>();
        System.out.println("started the searching process\n");

        // match product 
        for(Products prod:products){
            System.out.println("entered looping products\n");

            if(prod.equals(productID, startYear, endYear, description)){
                System.out.println("matches found\n");
                matching.add(prod);
            }
         
        } 
        
       return matching;
    }
/**
 * add a book to the products list 
 * @param productID get prodict ID 
 * @param description get the keywords 
 * @param price get price 
 * @param year get year 
 * @param Author get author name 
 * @param publisher get publisher name
 * @return
 */
    public List<Products> addBook(String productID, String description, double price, int year, String Author, String publisher){
        try{
            book = new Book(productID, description, price, year, Author, publisher);
            products.add(book);
        }catch(Exception e){ }   
        return products;
    }
    /**
 * add a electronics  to the products list 
 * @param productID get prodict ID 
 * @param description get the keywords 
 * @param price get price 
 * @param year get year 
 * @param maker get maker name
 * @return
 */
    public List<Products> addElectronics(String productID, String description, double price, int year, String maker){
        try{
            electronics = new Electronics(productID, description, price, year, maker);   
            products.add(electronics);
        }catch(Exception e){}
        return products;
    }
   
    /**
     * getter for products list  
     * @return list 
     */
    public List<Products> getProducts(){
        return products;
    }

   
    
}