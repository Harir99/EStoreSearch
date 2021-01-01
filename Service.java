package eStoreSearch;
import java.awt.event.*;
import java.awt.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.io.*;


public class Service  {
    private static List<Products> prod = new ArrayList<Products>();
    private static String inputFileName = null;
    private static File filename;
    private static EStoreSearch estore;
    private static Map<Integer, String> properties;

    private JFrame frame;
    private JComboBox<String> type;
    private JTextField productID, description, price, year, author, publisher, maker, startYear, endYear, ID, keyword;
    private JLabel publisherLabel, authorLabel, makerLabel, outputLabel, addingProduct, searchingProduct;
    private JPanel background, welcomeBackground, addBackground, searchBackground;
    private JTextArea incoming, Addincoming;
    private BoxLayout boxLayout;
    private String[] typeStrings = {"book","electronic"};
    private JMenuBar menuBar;
    private GridLayout border, border2;
    private Box buttonBox, buttonBox2;
    private JPanel searchingIt, inputPanel, insidePanel6,insidePanel7,insidePanel8, addingIt, ErrorPanel2, ErrorPanel, buttonsPanel, buttonsPanel2;
    private JScrollPane qScroller2, qScroller;

    /**
     * The main
     * @param args
     */
    public static void main(String[] args) {
        // variables declarations
        estore = new EStoreSearch();
        inputFileName = args[0];
        filename = new File(inputFileName);
        Service service = new Service();
        prod = service.readFile(filename,estore,prod);  
        service = new Service();
        service.BuildGUI();
    }
  
    /**
     * get the frame
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }
    /**
     * main interface GUI that is the background for all interfaces and main one 
     */
    public void BuildGUI(){
        
        frame = new JFrame("eStoreSearch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background = new JPanel();
        BoxLayout boxes = new BoxLayout(background,BoxLayout.Y_AXIS);

        background.setLayout(boxes);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        
        welcomeBackground = getWelcomePanel();

        addBackground = getAddPanel();
        
        border2 = new GridLayout(5,0);
       
        buttonsPanel2 = new JPanel(border2);
        buttonBox2 = new Box(BoxLayout.Y_AXIS); 
        buttonsPanel2.setVisible(false);

        JPanel c = new JPanel (); 
        JPanel d = new JPanel (); 

        buttonsPanel2.add(c);
        buttonsPanel2.add(d);

        JButton reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100,30));
        reset.addActionListener(new MyResetListener());
        buttonBox2.add(reset);
        buttonBox2.add(Box.createVerticalStrut(20));
        buttonBox2.add(Box.createHorizontalStrut(50));

        JButton add = new JButton("Add");
        add.setPreferredSize(new Dimension(100,30));
        add.addActionListener(new MyAddListener());
        buttonBox2.add(add);
        buttonBox2.add(Box.createVerticalStrut(20));
        buttonBox2.add(Box.createHorizontalStrut(50));

        buttonsPanel2.add(buttonBox2);
        addBackground.add(buttonsPanel2,BorderLayout.EAST);    
        searchBackground = getSearchPanel();
        border = new GridLayout(5,0);
          
        buttonsPanel = new JPanel(border);
        buttonBox = new Box(BoxLayout.Y_AXIS); 
        buttonsPanel.setVisible(false);
        JPanel a = new JPanel (); 
        JPanel b = new JPanel (); 

        buttonsPanel.add(a);
        buttonsPanel.add(b);

        JButton reset2 = new JButton("Reset");
        reset2.addActionListener(new MyResetListener());
        reset2.setPreferredSize(new Dimension(100,30));
        buttonBox.add(reset2);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(Box.createHorizontalStrut(50));


        JButton search = new JButton("Search");
        search.addActionListener(new MySearchListener());
        search.setPreferredSize(new Dimension(100,30));
        buttonBox.add(search);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(Box.createHorizontalStrut(50));
        buttonsPanel.add(buttonBox);
    
        searchBackground.add(buttonsPanel,BorderLayout.EAST);
       

        background.setVisible(true);
        background.add(welcomeBackground);
      
        frame.getContentPane().add(background);
        
        frame.setBounds(50,50,300,300);
        frame.setSize(700,700);
        frame.setVisible(true);
    }
    /** 
     * inner panel in search interface that holds the fields 
     */
    public JPanel getInputFind(JPanel background) {
        // initialize buttons

        ID = new JTextField(25);
        keyword = new JTextField(25);
        startYear = new JTextField(25);
        endYear = new JTextField(25);
       
        
        // set font of buttons
        Font font = new Font("serif", Font.BOLD, 12);
        ID.setFont(font);
        keyword.setFont(font);
        startYear.setFont(font);
        endYear.setFont(font);

        JPanel inputPanel = new JPanel();
        boxLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
        inputPanel.setLayout(boxLayout);
        JPanel insidePanel1 = new JPanel();
        JPanel insidePanel2 = new JPanel();
        JPanel insidePanel3 = new JPanel();
        JPanel insidePanel4 = new JPanel();
        JLabel space2 = new JLabel("  ");
        JLabel space3 = new JLabel("    ");


        searchingProduct =  new JLabel("Search for a Product");
        searchingIt = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchingIt.add(searchingProduct);
        inputPanel.add(searchingIt);

        insidePanel1.add(new JLabel("ID:         "));
        insidePanel1.add(space3);
        insidePanel1.add(ID);
        insidePanel1.add(Box.createVerticalStrut(20));

        insidePanel2.add(new JLabel("<html>Description<br/>Keywords:</html>"));
        
        insidePanel2.add(keyword);
        insidePanel2.add(Box.createVerticalStrut(20));

        insidePanel3.add(new JLabel("Start Year:  "));
        insidePanel1.add(space2);
        insidePanel3.add(startYear);
        insidePanel3.add(Box.createVerticalStrut(20));

        insidePanel4.add(new JLabel("End Year:    "));
        insidePanel1.add(space2);
        insidePanel4.add(endYear);
        insidePanel4.add(Box.createVerticalStrut(20));
        
        inputPanel.add(insidePanel1);
        inputPanel.add(insidePanel2);
        inputPanel.add(insidePanel3);
        inputPanel.add(insidePanel4);

        searchBackground.add(inputPanel,BorderLayout.LINE_START);
        return searchBackground;
    } 
    /**
     * search interface panel that holds fields and buttons and text area for messages 
     * @return
     */
    public JPanel getSearchPanel(){
        searchBackground = new JPanel();
        BorderLayout newLayout = new BorderLayout();
        searchBackground.setLayout(newLayout);

        searchBackground.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        searchBackground.setVisible(false);
      
        frame.setJMenuBar(menuBar);
        searchBackground = getInputFind(searchBackground);
        
        incoming = new JTextArea(10,15);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        
        ErrorPanel2 = new JPanel(new BorderLayout());
        qScroller2 = new JScrollPane(incoming);
        qScroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        ErrorPanel2.add(new JLabel("Search Results"),BorderLayout.NORTH);
        ErrorPanel2.add(qScroller2,BorderLayout.SOUTH);
        searchBackground.add(ErrorPanel2,BorderLayout.SOUTH);
        background.add(searchBackground);
        return searchBackground;
    }
    /**
     * add interface that holds the fields and buttons for the interface add 
     * @return
     */
    public JPanel getAddPanel(){
        addBackground = new JPanel();
        BorderLayout layout = new BorderLayout();
        addBackground.setLayout(layout);
        addBackground.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        addBackground.setVisible(false);

        frame.setJMenuBar(menuBar);  

        addBackground = getInputPanel(addBackground);
        
        Addincoming = new JTextArea(10,15);
        Addincoming.setLineWrap(true);
        Addincoming.setWrapStyleWord(true);
        Addincoming.setEditable(false);
        
        ErrorPanel = new JPanel(new BorderLayout());
        qScroller = new JScrollPane(Addincoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        ErrorPanel.add(new JLabel("Messages"),BorderLayout.NORTH);
        ErrorPanel.add(qScroller,BorderLayout.SOUTH);
        addBackground.add(ErrorPanel,BorderLayout.SOUTH);
        background.add(addBackground);
        return addBackground;
    }
    /**
     * the inner panel inside the add interface , where all fields show up 
     * @param addBackground panel for add inerface 
     * @return the panel 
     */
    public JPanel getInputPanel(JPanel addBackground) {
        // initialize buttons
        type = new JComboBox<String>(typeStrings);
        productID = new JTextField(15);
        description = new JTextField(30);
        price = new JTextField(10);
        year = new JTextField(10);
        author = new JTextField(30);
        publisher = new JTextField(30);
        maker = new JTextField(30);
        
        // set font of buttons
        Font font = new Font("serif", Font.BOLD, 12);
        type.setFont(font);
        productID.setFont(font);
        description.setFont(font);
        price.setFont(font);
        year.setFont(font);
        publisher.setFont(font);
        maker.setFont(font);
        author.setFont(font);

     
        inputPanel = new JPanel();
        JPanel insidePanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel insidePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insidePanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insidePanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insidePanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel space = new JLabel(" ");
        JLabel space2 = new JLabel("  ");
        JLabel space3 = new JLabel("   ");
        JLabel space4 = new JLabel("     ");


        insidePanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insidePanel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insidePanel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        boxLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
        inputPanel.setLayout(boxLayout);

        addingProduct = new JLabel("Adding a Product");
        addingIt = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addingIt.add(addingProduct);
        inputPanel.add(addingIt);

        insidePanel1.add(new JLabel("Type: ")); 
        insidePanel1.add(type);
        type.addActionListener(new MySelectionListener());        
        
        insidePanel2.add(new JLabel("productID:  "));
        insidePanel2.add(space);
        insidePanel2.add(productID);

        insidePanel3.add(new JLabel("Description: "));
        insidePanel3.add(description);
        
        insidePanel4.add(new JLabel("Price:       "));
        insidePanel4.add(space4);
        insidePanel4.add(price);
   

        insidePanel5.add(new JLabel("Year:           "));
        insidePanel5.add(space);
        insidePanel5.add(year);
        
        authorLabel = new JLabel("Author:     ");
        insidePanel6.add(authorLabel);
        insidePanel6.add(space3);
        insidePanel6.add(author);
        
        publisherLabel = new JLabel("Publisher:    ");
        insidePanel7.add(publisherLabel);
        insidePanel7.add(space2);
        insidePanel7.add(publisher);

        makerLabel = new JLabel("Maker:       ");
        insidePanel8.add(makerLabel);
        insidePanel8.add(space2);
        insidePanel8.add(maker);    
        insidePanel8.setVisible(false);

        inputPanel.add(insidePanel1);
        inputPanel.add(insidePanel2);
        inputPanel.add(insidePanel3);
        inputPanel.add(insidePanel4);
        inputPanel.add(insidePanel5);
        inputPanel.add(insidePanel6);
        inputPanel.add(insidePanel7);
        inputPanel.add(insidePanel8);

        addBackground.add(inputPanel,BorderLayout.LINE_START);
        return addBackground;
    } 
    /**
     * welcome panel when user open program, shows instructions on what to do 
     * @return
     */
    public JPanel getWelcomePanel(){
        welcomeBackground = new JPanel();
        BoxLayout Wlayout = new BoxLayout(welcomeBackground,BoxLayout.Y_AXIS);
        welcomeBackground.setLayout(Wlayout);
        welcomeBackground.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuBar = getMenu();
        JPanel text = new JPanel();
        text.setLayout(new BorderLayout());
        outputLabel = new JLabel("<html>Welcome to eStoreSearch!<br/>Choose a command from the 'commands' menu above for adding product, searching product, or quitting the program...</html>");
        Font bigFont = new Font("serif", Font.BOLD, 20);
        outputLabel.setFont(bigFont);
    
        welcomeBackground.setVisible(true);
        
        text.add(outputLabel,BorderLayout.CENTER);
        welcomeBackground.add(text);
     
        return welcomeBackground;
    }

/**
 * get the menu to choose add, search , or command
 * @return the menu bar
 */
    public JMenuBar getMenu () {

        JMenuBar menuBar = new JMenuBar();

        JMenu command = new JMenu("Command");
        
        JMenuItem add = new JMenuItem("Add");
        add.addActionListener(new ChoiceActionListener());
        command.add(add);

        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(new ChoiceActionListener());
        command.add(search);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ChoiceActionListener());
        command.add(quit);

        Font menuFont = new Font("serif", Font.BOLD, 14);
        command.setFont(menuFont);
        add.setFont(menuFont);
        search.setFont(menuFont);
        quit.setFont(menuFont);

        menuBar.add(command);
       
        return menuBar;
    }

/**
 * clear all fields for search interface
 */
class MyResetListener implements ActionListener {
    public void actionPerformed (ActionEvent a){
        type.setSelectedIndex(0);
        ID.setText("");
        keyword.setText("");
        startYear.setText("");
        endYear.setText("");
        incoming.setText("");

        productID.setText("");
        description.setText("");
        price.setText("");
        year.setText("");
        author.setText("");
        publisher.setText("");
        maker.setText("");
        Addincoming.setText("");

    }
  
}
/**
 * add products to list when button clicked 
 */
class MyAddListener implements ActionListener {
 
    String AproductID = "";
    String Adescription = null, AAuthor = null, Apublisher = null, Amaker = null;
    double Aprice = 0.0; 
    int Ayear = 0;
    EStoreSearch estore = new EStoreSearch();
    String GetUserInput = "";
    boolean error = false;
    /**
     * action performed when button is clicked 
     */
    public void actionPerformed (ActionEvent a){ 
        
        GetUserInput = (String) type.getSelectedItem();  
        AproductID = getString(false,productID.getText());
        if(!containsDigits(AproductID)){
                Addincoming.append("length of ID has to be digits alone!!");
        }
            
        if(AproductID.length() != 6){
            Addincoming.append("length of ID has to be 6 digits!!");
        }  
        if(estore.SearchProductID(AproductID) != null){
            Addincoming.append("ID Already Exists!!");    
            
        }                  

        Adescription = getString(false, description.getText());
        Aprice = getdouble(true, price.getText());
        Ayear = getNumber(false, year.getText());
        if(Ayear > 9999 ||Ayear < 1000){
            Addincoming.append("Error! follow the following syntax for year : Year < 9999 and year > 1000\n"); 
        }
        
        if(GetUserInput.equals("book")){
            AAuthor = getString(true, author.getText());
            Apublisher = getString(true,publisher.getText());
            prod = estore.addBook(AproductID, Adescription, Aprice, Ayear, AAuthor, Apublisher);
           
        }
        else {
            Amaker = getString(true,  maker.getText());
            prod = estore.addElectronics(AproductID, Adescription, Aprice, Ayear, Amaker);
           

        }
    }

}
/**
 * search listener to get the found product when button clicked 
 */
class MySearchListener implements ActionListener {
    String AproductID = "";
    String Adescription = null, AAuthor = null, Apublisher = null, Amaker = null;
    double Aprice = 0.0; 
    int Ayear = 0;
    EStoreSearch estore = new EStoreSearch();
    String GetUserInput = "";
    boolean error = false;
    List<Products> matches = new ArrayList <Products>();
    int AstartYear = 0;
    int AendYear = 0;
    /**
     * the action to be taken when button is clicked 
     */
    public void actionPerformed (ActionEvent a){ 
        
        // get the years range and search the innventory based on it
        AstartYear = getNumber(true, startYear.getText());
        AendYear = getNumber(true, endYear.getText());
            
        // get keywords required to search inventory 
        Adescription = getString(true,keyword.getText());
            
        // ask if an ID is to be provided 
        AproductID = getString(true, ID.getText());
        // search and match products with users requests 
        incoming.setText("product ID = "+AproductID+" description = "+Adescription+" startyear =  "+AstartYear+" end year = "+AendYear+"\n");
        // match product 
        for(Products product:prod){
            if(product.equals(AproductID, AstartYear,AendYear, Adescription)){
             matches.add(product);
            }
            
        } 
        incoming.setText("Your matches are :");
        printArray(matches);

    }
    /**
     * print the  array to text area 
     * @param matches array of found products 
     */
    public  void printArray(List<Products> matches){ 
    
    // print the sentences in an array
        for(Products a:matches){
            incoming.append(a.toString());
        }
    } // method end
}
/**
 * selection listener for either book or electronic to get the fields related show and make unrelated ones invisible 
 */
class MySelectionListener implements ActionListener {
   
    public void actionPerformed(ActionEvent a) {
        String selected = (String) type.getSelectedItem();
        if (selected != null) {
            if(selected.equals("book")){
                maker.setVisible(false);
                makerLabel.setVisible(false);
                publisher.setVisible(true);
                publisherLabel.setVisible(true);
                author.setVisible(true);
                authorLabel.setVisible(true);
            } else {
                maker.setVisible(true);
                makerLabel.setVisible(true);
                publisher.setVisible(false);
                publisherLabel.setVisible(false);
                author.setVisible(false);
                authorLabel.setVisible(false);

            }
        }

    } // close valueChanged
} // close inner class
/**
 * choice listener to get the user choice from menu add, search or quits
 */
class ChoiceActionListener implements ActionListener {
  
    public void actionPerformed(ActionEvent a) {
        String choice = a.getActionCommand();
        // FindService findsService = new FindService(prod);
        // Service Service = new Service(prod);
        if (choice.equals("Quit")) {
           System.exit(0);
        }else if (choice.equals("Search")) {
            addBackground.setVisible(false);
            welcomeBackground.setVisible(false);
            searchBackground.setVisible(true);
            searchingProduct.setVisible(true);
            addingProduct.setVisible(false);
            buttonsPanel.setVisible(true);
            buttonsPanel2.setVisible(false);
            writeFile(filename, prod);

        }else if(choice.equals("Add")){
            addBackground.setVisible(true);
            welcomeBackground.setVisible(false);
            searchBackground.setVisible(false);
            addingProduct.setVisible(true);
            searchingProduct.setVisible(false);
            buttonsPanel2.setVisible(true);
            buttonsPanel.setVisible(false);
            
        } 
        
    }
}
 /**
     * write to file function for output
     */
    public void writeFile(File filename,List<Products> products){
        try {
            //File filename = new File(outputFileName);
            FileWriter writer = new FileWriter(filename); 
            PrintWriter printWriter = new PrintWriter(writer);
            for(Products prod: products) {
                printWriter.println(prod);
            }
            writer.close();
            printWriter.close();
        } catch (IOException e) {}
    } 
/**
 * get integer from field 
 * @param empty
 * @param input
 * @return
 */
public int getNumber(boolean empty,String input){
    int number = 0;
    boolean error = true;
    // check for various possible errors 
    while (error) {       
        error = false;

        if(input.length() == 0 && empty == true){
            return 0;
        }
        try {
            number = Integer.parseInt(input);

        } catch (Exception e) {
            incoming.append("Wrong number entered, Enter Again");
            error = true;
        }
    }
    return number;
}
/**
 * get double from field
 * @param empty if set true, then number field can be left empty
 * @return double entered by user
 */
public double getdouble(boolean empty,String input){

    double number = 0;
    boolean error = true;
    while (error) {       
        error = false;
        // if set to true, field can be left empty otherwise it's a madatory field
        if(input.length() == 0 && empty == true){
            return 0;
        }
        try {
            number = Double.parseDouble(input);

        } catch (Exception e) {
            incoming.append("Wrong number entered, Enter Number Again ");
           
            error = true;
        }
    }
    return number;
}
 /**
 * get string from field
 * @param empty if set true, then input field can be left empty
 * @return String entered by user 
 */
public String getString(boolean empty,String input){
    boolean error = true;

    while (error) {       
        error = false;
        // get a string, and check if its true then it can be left empty
        if(input.length() == 0 && empty == false){
             incoming.append("Input Required!! Enter Again");
             error = true;
        }
        
    }
    return input;
}
/**
 * match keywords with description
 * @param needle word to be found 
 * @param haystack string that contains the word 
 * @return boolean value, true if word is found
 */
public static boolean matchString(String needle,String haystack){
    
    needle = needle.toLowerCase();
    haystack = haystack.toLowerCase();
    int count = 0;
    String[] needleFactory = needle.split("[ ]+"); //
    String[] haystackFactory = haystack.split("[ ]+"); //
    for (String single : haystackFactory) {
        setDescription(single,count);
        count++;
    }
    for (int strCount = 0; strCount < needleFactory.length; strCount++) {
        String singleNeedle = needleFactory[strCount];
        for (int wrdCount = 0; wrdCount < haystackFactory.length; wrdCount++) {
            if(properties.containsValue(singleNeedle)) {
                properties.get(count);
                return true;
            }
        }
      
    }

    return false;
}
 /**
 * hash map set keyword 
 * @param description
 * @param index
 */
public static void setDescription(String description, Integer index){
    if(properties == null){ 
        properties = new HashMap<Integer, String>();
    }
    properties.put(index,description);
}
   /**
    *  read from file for input in a specific format
    * @param filename name of the file
    * @param estore object for Estore class 
    * @param prods list of products
    * @return list of products 
    */
    public List<Products> readFile(File filename,EStoreSearch estore,List<Products> prods){
        String data = "";
        try {
            Scanner reader = new Scanner(filename);
            String productID = "";
            String description = "";
            
            double price = 0.0;
            int year = 0;
            String Author = "";
            String publisher = "";
            String maker = "";
            String type  = "";
            
            while (reader.hasNextLine()) {
            data = reader.nextLine();
            if(data.length()>0){
                String[] words = data.split("=");
                String word1 = words[0].trim();
                String word2 = words[1].trim();

                if(word1.equals("type") && word2.equals("book")){
                    type = "book";
                }
                if(word1.equals("type") && word2.equals("electronic")){
                    type = "electronic";
                }
                if(words[0].trim().equals("productID")){
                    words[1] = words[1].replace("\"", "");
                    productID = words[1].trim();
                }

                if(words[0].trim().equals("keyword")){
                    words[1] = words[1].replace("\"", "");
                    description = words[1].trim();
                }
                if(words[0].trim().equals("price")){
                    words[1] = words[1].replace("\"", "");
                    price = Double.parseDouble(words[1].trim());
                }
                if(words[0].trim().equals("year")){
                    words[1] = words[1].replace("\"", "");
                    year = Integer.parseInt(words[1].trim());
                }
                if(words[0].trim().equals("author")){
                    words[1] = words[1].replace("\"", "");
                    Author = words[1].trim();
                }
                if(words[0].trim().equals("publisher")){
                    words[1] = words[1].replace("\"", "");
                    publisher = words[1].trim();
                    prods = estore.addBook(productID, description, price, year, Author, publisher);
                }
                if(words[0].trim().equals("maker")){
                    words[1] = words[1].replace("\"", "");
                    maker = words[1].trim();
                    prods = estore.addElectronics(productID, description, price, year, maker);
                }
            }
            
            }
            reader.close();
        } catch (FileNotFoundException e) { 

            System.out.println("Error - "+filename+"file does not exist");
            System.exit(0);
            
        }
        return prods;
    }
    /**
     * check if thhe string is made of chars 
     * @param s string inputed by user 
     * @return boolean value deciding whether a digit was found
     */
    public static boolean containsDigits(String s) {
    
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    
        return false;
    }
}