///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            program 1
// Files:            Cloud.java, InsufficientCreditException.java, Machine.java, User.java, DLinkedList.java
// Semester:         (course) Summer 2017
//
// Author:           Yifan Mei
// Email:            ymei8@wisc.edu
// CS Login:         yifanmei
// Lecturer's Name:  Meena
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yuran Liu
// Email:            yuran.liu@wisc.edu
// CS Login:         yuran
// Lecturer's Name:  Meena
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.util.Scanner;

/**
 * Main class which simulates the cloud environment.
 */
public class Cloud {

    //Store record of users and machines
    private static ListADT<Machine> machines = new DLinkedList<Machine>();
    private static ListADT<User> users = new DLinkedList<User>();
    private static User currentUser = null;//current user logged in

    //scanner for console input
    public static final Scanner stdin = new Scanner(System.in);

    //main method
    public static void main(String args[]) {

        //Populate the two lists using the input files: Machines.txt User1.txt 
        //User2.txt ... UserN.txt
        if (args.length < 2) {
            System.out.println("Usage: java Cloud [MACHINE_FILE] [USER1_FILE] [USER2_FILE] ...");
            System.exit(0);
        }

        //load store machines
        loadMachines(args[0]);

        //load users one file at a time
        for(int i = 1; i< args.length; i++) {
            loadUser(args[i]);
        }

        //User Input for login
        boolean done = false;
        while (!done) 
        {
            System.out.print("Enter username : ");
            String username = stdin.nextLine();
            System.out.print("Enter password : ");
            String passwd = stdin.nextLine();

            if(login(username, passwd) != null)
            {
                //generate random items in stock based on this user's machine 
                //list
                ListADT<Machine> inStock = currentUser.generateMachineStock();
                //show user menu
                userMenu(inStock);
            }
            else
                System.out.println("Incorrect username or password");

            System.out.println("Enter 'exit' to exit program or anything else to go back to login");
            if(stdin.nextLine().equals("exit"))
                done = true;
        }

    }

    /**
     * Tries to login for the given credentials. Updates the currentUser if 
     * successful login
     * 
     * @param username name of user
     * @param passwd password of user
     * @returns the currentUser 
     */
    public static User login(String username, String passwd) {
    	for (int i = 0; i < users.size(); i++) {
            if (users.get(i).checkLogin(username, passwd)) {
                currentUser = users.get(i);
                return currentUser;
            }
        }
        return null;
    }

    /**
     * Reads the specified file to create and load machines into the store.
     * Every line in the file has the format: <NAME>#<CATEGORY>#<PRICE>#<RATING>
     * Create new machines based on the attributes specified in each line and 
     * insert them into the machines list
     * Order of machines list should be the same as the machines in the file
     * For any problem in reading the file print: 'Error: Cannot access file'
     * 
     * @param fileName name of the file to read
     */
    public static void loadMachines(String fileName) {
    	try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] slines = line.split("#");
                Machine newm = new Machine(slines[0], Integer.parseInt(slines[1]),
                        Integer.parseInt(slines[2]), Integer.parseInt(slines[3]),
                        Double.parseDouble(slines[4]));
                machines.add(newm);
            }
        } catch (Exception e) {
            System.out.println("Error: Cannot access file");
        }
    }

    /**
     * Reads the specified file to create and load a user into the store.
     * The first line in the file has the format:<NAME>#<PASSWORD>#<CREDIT>
     * Every other line after that is a name of a machine in the user's 
     * machinelist, format:<NAME>
     * For any problem in reading the file print: 'Error: Cannot access file'
     * 
     * @param fileName name of the file to read
     */
    public static void loadUser(String fileName) {
    	 try {
             File f = new File(fileName);
             Scanner sc = new Scanner(f);
             String line = sc.nextLine();
             String[] slines = line.split("#");
             User newu = new User(slines[0], slines[1], Double.parseDouble(slines[2]));
             currentUser = newu;
             users.add(newu);
             while (sc.hasNext()) {
                 String line1 = sc.nextLine();
                 String[] slines1 = line1.split("#");
                 Machine machinelist = new Machine(slines1[0], Integer.parseInt(slines1[1]),
                         Integer.parseInt(slines1[2]), Integer.parseInt(slines1[3]),
                         Double.parseDouble(slines1[4]));
                 currentUser.addToMachineList(machinelist);
                 users.add(currentUser);
             }
         } catch (Exception e) {
             System.out.println("Error: Cannot access file");
         }
    }

    /** 
     * Prints the entire machine inventory.
     * The order of the machines should be the same as in input machines file.
     * The output format should be the consolidated String format mentioned
     * in Machine class.
     */

    public static void printMachines() {
    	for (int i = 0; i < machines.size(); i++) {
            System.out.println(machines.get(i).toString());
        }
    }

    
    /**
     * Interacts with the user by processing commands
     * 
     * @param inStock list of machines that are in stock
     */
    public static void userMenu(ListADT<Machine> inStock) {

         boolean done = false;
        while (!done) 
        {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();

            //only do something if the user enters at least one character
            if (input.length() > 0) 
            {
                String[] commands = input.split(":");//split on colon, because 
                                                     //names have spaces in them
                if(commands[0].length()>1)
                {
                    System.out.println("Invalid Command");
                    continue;
                }
                switch(commands[0].charAt(0)) {
                case 'v':
                	if (commands[1].equals("all")) {
                        printMachines();
                        done = false;
                    }
                    else if (commands[1].equals("machinelist")) {
                        currentUser.printMachineList();
                        done = false;
                    }
                    else if (commands[1].equals("instock")) {
                        for (int i = 0; i < inStock.size(); i++) {
                            System.out.println(inStock.get(i).toString());
                        }
                        done = false;
                    }
                    else {
                        System.out.println("Invalid Command");
                        done = false;
                    }
                    break;

                case 's':
                    for (int i = 0; i < machines.size(); i++) {
                    	String nn = new String ("");
                    	for (int j = 0; j < machines.get(i).getName().length(); j++) {
                    		nn += machines.get(i).getName().charAt(j);
                    		if (nn.equals(commands[1])) {
                    			System.out.println(machines.get(i).toString());
                    			j = machines.get(i).getName().length();
                    		}
                    	}
                    	done = false;
                    }
                    break;

                case 'a':
                	 boolean found = false;
                     for (int i = 0; i < machines.size(); i++) {
                         if (machines.get(i).getName().equals(commands[1])) {
                             found = true;
                             //Machine newm = machines.remove(i);
                             currentUser.addToMachineList(machines.get(i));
                         }
                     }
                     if (!found) {
                         System.out.println("Machine not found");
                         done = false;
                     } else {
                         done = false;
                     }
                    break;

                case 'r':
                	Machine newm = currentUser.removeFromMachineList(commands[1]);
                    if (newm == null) {
                        System.out.println("Machine not found");
                        done = false;
                    } else {
                        done = false;
                    }
                    break;

                case 'b':
                	for (int i = 0; i < inStock.size(); i++) {
                        try {
                            String name = inStock.get(i).getName();
                            if (!currentUser.rent(name)) {
                                System.out.println("Machine not needed: " + name);
                                done = false;
                            } else {
                            	System.out.println("Rented "+ name );
                                done = false;
                            }
                        } catch (InsufficientCreditException e) {
                            done = false;
                            System.out.println("For renting " + inStock.get(i).getName() + ": " + e.getMessage());
                        }
                    }
                    break;

                case 'c':
                	System.out.println("$" + currentUser.getCredit());
                    done = false;
                    break;

                case 'l':
                    done = true;
                    System.out.println("Logged Out");
                    break;

                default:  //a command with no argument
                    System.out.println("Invalid Command");
                    done = false;
                    break;
                }
            }
        }
    }

}
