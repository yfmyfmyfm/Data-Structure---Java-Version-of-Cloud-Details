///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Cloud.java
// File:             InsufficientCreditException.java
// Semester:         (course) Spring 2016
//
// Author:           Yifan Mei; ymei8@wisc.edu
// CS Login:         yifanmei
// Lecturer's Name:  Meena
// Lab Section:      (your lab section number)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Yuran Liu
// Email:            yuran.liu@wisc.edu
// CS Login:         yuran
// Lecturer's Name:  Meena
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

public class InsufficientCreditException extends Exception {
	/**
	 * Below are the specifications for this class:

Code a checked exception class to be used by your implementation of User to signal 
when the user tries to rent an item but doesn't have enough credit.

Write the default constructor.

You should also write the constructor that enables you to pass a String message.

Your main program should catch this exception when it happens and display the following:
 "For rentingi <MACHINE-NAME>: <EXCEPTION-MESSAGE>". 
 Use the getMessage() function for this purpose. 
 Finally, proceed with processing the user input for the commands.

	 */
	    public InsufficientCreditException() {
	        super();
	    }
	    public InsufficientCreditException(String msg) {
	        super(msg);
	    }
}
