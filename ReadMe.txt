---------------------
PROJECT TITLE: eStoreSearch 
----------------------

AUTHOR'S NAME
--------------
Harir Al-Rubaye

CONTENTS OF THIS PROJECT
---------------------
- CLASSES INCLUDED 
- JUNIT TEST FILES 
- GENERAL PROBLEM 
- SOLUTIONS LIMITATIONS
- USER GUIDE 
- TEST PLAN 
- POSSIBLE IMPROVEMENT 

PACKAGES INCLUDED
---------------------
eStoreSearch

ALL CLASSES INCLUDED 
---------------------
- Products.Java
- Book.Java
- Electronics.java
- EStoreSearch.java 
- Service.java 

JUNITS TESTS 
---------------------
eStoreTest.java 
ElectronicTest.java 
BookTest.java
ProductTest.java

GENERAL PROBLEM 
---------------------
The General problem i am trying to solve is developing an online store to search and find the product you want after you add them to the inventory. This store will have two types of products, books and electronics.

SOLUTIONS LIMITATIONS
---------------------
REMEMBER!!
* be aware that the Tester has the main class, while the package name is eStoreSearch!
* reads filename from command line, make sure that the text file you wanna read is in build/classes/java/main or else it wont be read by console  

When you run the program you will be given a menu that includes the following options:
1 - add
2 - search
3 - quit. 

you can type your choice in letters. The program will reboot and ask for input again if an invalid choice was made.
you are allowed to enter shorter input, (eg. "quit"), you may enter "q", "Q", "QUIT", or "Quit". However, the program will ask for input again if you enter something invalid like "bye" or "close" instead.

For add choice:
- you will be asked wether you want to add a book or an electronic, then you will get to enter the produtc attributes one by one, as a you will be asked to enter productID first then Description, after that price and so on..
- you must enter a unique productID, and the following fields cannot be left empty when adding a book: productID, Description, and year. On the other hand, the other attributes can be left empty if you wish.
- you cannot enter a number for a publisher, author or a maker name. likewise, a string of letters will be rejected when enteringa price, the program will give an invalid error message and ask you to enter again.

For search choice: 
- uses hashmaps to search for keywords
- it will contain up to three fields that consist of ay combination of ProductID, year and description.
- you may leave them all empty or fill just the ones you want to add 
- when entering a year, it will be in the following format : 
            1- startYear-endYear 
            2- startYear-
            3- -endYear
            4- year
you can choose whatever format you please, but do not add space before or after hyphen.
The first one will find all the books and electronics that are listed between startYear and endYear.
while the second one will return all the books and electronics that are listed starting from startYear and above. 
The third one will return all the books and electronics that are listed from endYear and below that
the fourth one will return all books and electronics listed in that specific year alone

when searching for description, it will be case insensitive, therefore you may enter your keywords in uppercase or lowercase either works fine
- "Programming in Java" and "Java programming" will return a match.
- "JAVA" and "java" will also be a match
- while "program" and "programming" will not match


USER GUIDE
-----------------
to build the program: ./gradlew build
to run the program go to build/classes/java/main then run it as the following : java eStoreSearch.Service <filename>
to run the JunitTests with the console: ./gradlew test


TEST PLAN
-----------------
- all the methods in my project has been tested for correctness throughly using JUnit tests.
- all the tests used are presented in eStoreTest.java file

POSSIBLE IMPROVEMENT
-----------------
if i have extra time in the future, i would like to make it possible to search using price or include a way for user to provide a rating on a product

