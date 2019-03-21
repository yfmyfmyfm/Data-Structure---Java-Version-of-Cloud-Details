///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Cloud.java
// File:             Machine.java
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

/**
 * Stores the name, specification and price of a machine
 */
public class Machine {
    
    private String machineName;     //stores the machine name
    private int numCPUs;            //stores the number of CPU's
    private int memorySize;         //stores RAM size in GB's
    private int diskSize;           //stores disk size in GB's
    private double price;           //price of the machine
    
    /**
     * Constructs a machine with a name, specifications and price. 
     * 
     * @param machineName name of machine
     * @param numCPUs number of CPU's
     * @param memorySize number of GB's of RAM
     * @param diskSize number of GB's of disk
     * @param price price of product in $ 
     */
    public Machine(String machineName, int numCPUs, int memorySize, 
                    int diskSize, double price) {
    	this.machineName = machineName;
        this.numCPUs = numCPUs;
        this.memorySize = memorySize;
        this.diskSize = diskSize;
        this.price = price;
    }
    
    /** 
     * Returns the name of the machine
     * @return the machineName
     */
    public String getName() {
    	return this.machineName;
    }

    /**                
     * Returns the number of CPU's of the machine
     * @return the numCPUs
     */                
    public int getCPUs() {
    	return this.numCPUs;
    }   
    
    /**                
     * Returns the memory size of the machine
     * @return the memorySize
     */                
    public int getMemorySize() {
    	return this.memorySize;
    }     

   /**                       
     * Returns the disk size of the machine
     * @return the diskSize 
     */                       
    public int getDiskSize() {
    	return this.diskSize;
    	
    }      
    
    /** 
     * Returns the price of the product
     * @return the price
     */
    public double getPrice() {
    	return this.price;
    }
    
    /** 
     * Returns the machine's information in the following format: 
     *
     * Note: The below line exceeds the 80 character limit just to make sure
     * that you have a clear understanding of the output format
     * <NAME> [Number of CPU's: <CPUs>, RAM size: <RAM>, Disk size: <DISK>] [Price:$<PRICE>]
     * @return the string
     */
    public String toString() {
    	String s = "";
        s += machineName;
        s += " [Number of CPU's: " + numCPUs + ", ";
        s += "RAM size: " + memorySize + ", ";
        s += "Disk size: " + diskSize + "] ";
        s += "[Price: $" + price + "]";
        return s;
    	
    }

}
