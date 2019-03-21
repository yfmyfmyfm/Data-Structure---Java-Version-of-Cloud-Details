///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Cloud.java
// File:             User.java
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

import java.util.Random;

/**
 * The User class uses DLinkedList to build a price ordered list called 
 * 'machineList' of machines.
 * Machines with higher price fields should come earlier in the list.
 */
public class User {
    //Random number generator, used for generateMachineStock. DO NOT CHANGE
    private static Random randGen = new Random(1234);
    
    private String username;
    private String passwd;
    private double credit;
    private ListADT<Machine> machineList;
    
    /**
     * Constructs a User instance with a name, password, credit and an empty 
     * machineList. 
     * 
     * @param username name of user
     * @param passwd password of user
     * @param credit amount of credit the user had in $ 
     */
    public User(String username, String passwd, double credit) {
        this.username = username;
        this.passwd = passwd;
        this.credit = credit;
        machineList = new DLinkedList<Machine>();
    }
    
    /**
     * Checks if login for this user is correct.
     *
     * @param username the name to check
     * @param passwd the password to check
     * @return true if credentials correct, false otherwise
     * @throws IllegalArgumentException if arguments are null 
     */
    public boolean checkLogin(String username, String passwd) {
    	if (username == null || passwd == null)
            throw new IllegalArgumentException();
        return username.equals(this.username) && passwd.equals(this.passwd);
    }
    
    /**
     * Adds a machine to the user's machineList. 
     * Maintain the order of the machineList from highest priced to lowest 
     * priced machines.
     * @param machine the Machine to add
     * @throws IllegalArgumentException if arguments are null 
     */
    public void addToMachineList(Machine machine) {
    	if (machine == null)
            throw new IllegalArgumentException();
        boolean inserted = false;
        for (int i = 0; i < machineList.size(); i++) {
            if (machine.getPrice() >= machineList.get(i).getPrice()) {
                machineList.add(i, machine);
                inserted = true;
                return;
            }
        }
        if (!inserted)
            machineList.add(machine);
    }
    
    /**
     * Removes a machine from the user's machineList. 
     * Do not charge the user for the price of this machine
     * @param machineName the name of the machine to remove
     * @return the machine on success, null if no such machine found
     * @throws IllegalArgumentException if arguments are null
     */
    public Machine removeFromMachineList(String machineName) {
    	 if (machineName == null)
             throw new IllegalArgumentException();
         for (int i = 0; i < machineList.size(); i++) {
             if (machineName.equals(machineList.get(i).getName())) {
                 return machineList.remove(i);
             }
         }
         return null;
    }
    
    /**
     * Print each machine in the user's machineList in its own line by using
     * the machine's toString function.
     */
    public void printMachineList() {
    	for (int i = 0; i < machineList.size(); i++) {
            System.out.println(machineList.get(i).toString());
        }
    }
    
    /**
     * Rents the specified machine in the user's machineList.
     * Charge the user according to the price of the machine by updating the 
     * credit.
     * Remove the machine from the machineList as well.
     * Throws an InsufficientCreditException if the price of the machine is 
     * greater than the credit available. Throw the message as:
     * Insufficient credit for <username>! Available credit is $<credit>.
     * 
     * @param machineName name of the machine
     * @return true if successfully bought, false if machine not found 
     * @throws InsufficientCreditException if price > credit 
     */
    public boolean rent(String machineName) throws InsufficientCreditException{
    	 if (machineName == null)
             throw new IllegalArgumentException();
         for (int i = 0; i < machineList.size(); i++) {
             if (machineName.equals(machineList.get(i).getName())) {
                 if (credit < machineList.get(i).getPrice()) {
                     String msg = "Insufficient credit for " + username;
                     msg += "! Available credit is $" + credit + ".";
                     throw new InsufficientCreditException(msg);
                 }
                 else {
                     credit -= machineList.get(i).getPrice();
                     removeFromMachineList(machineName);
                     return true;
                 }
             }
         }
         return false;
    }
    
    /** 
     * Returns the credit of the user
     * @return the credit
     */
    public double getCredit() {
    	return credit;
    }

    /**
     * This method is already implemented for you. Do not change.
     * Declare the first n machines in the currentUser's machineList to be 
     * available.
     * n is generated randomly between 0 and size of the machineList.
     * 
     * @returns list of machines in stock 
     */
    public ListADT<Machine> generateMachineStock() {
        ListADT<Machine> availableMachines = new DLinkedList<Machine>();

        int size = machineList.size();
        if(size == 0)
            return availableMachines;
       
       //N items in stock where n >= 0 and n < size
        int n = randGen.nextInt(size+1); 

        //pick first n items from machineList
        for(int ndx = 0; ndx < n; ndx++)
            availableMachines.add(machineList.get(ndx));
        
        return availableMachines;
    }

}

