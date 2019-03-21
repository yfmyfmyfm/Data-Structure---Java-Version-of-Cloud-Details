# Data-Structure---Java-Version-of-Company-Hierarchy

Cloud platforms that allow users to rent / use machines are become very famous. These rentable machines are maintained by the cloud provider. Cloudlab is an example of one such service; UW-Madison is one of the cloud providers for cloudlab. As the user, you will have a set of machines in your list, you can rent them if they are available. You will be using multiple linked lists to track the users of such a system, the machines that are available for renting, current list of machines that a user wants to rent and so on.

The program simulates the basic user experience allowing users to select what they'd like to add to their machinelist. 
Following are description of components of the program that was initialized at program start up and wouldn't be modified further:

    The list of machines.
    The list of users.
    Fixed credit for each user to rent machines.

Users are then allowed to add and remove machines from their list. The items in a users machinelist are ordered by price from highest to lowest. The program enables a users to login to their account, view all the items in the cloud, add items to or remove items from their machinelists, and rent items on their machinelists that are in stock.

The main class Cloud prompts the user to login. It then enables the user to enter the required operation that can manipulate the machinelist of machines. Machinelists will be represented using a DLinkedList class that you will also write. Your DLinkedList class implements the ListADT interface using a doubly-linked chain of nodes. You will also implement a checked exception - InsufficientCreditException class which will be used to signal and handle problems when a user doesn't have enough credit to rent a machine. You will also be writing the User and Machine classes that track users and machines. 
