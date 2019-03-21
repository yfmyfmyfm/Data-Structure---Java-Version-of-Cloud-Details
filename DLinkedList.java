///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Cloud.java
// File:             DLinkedList.java
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

public class DLinkedList<E> implements ListADT<E> {
	private DblListnode<E> head;
	private DblListnode<E> tail;
	private int numItems;
	
	public DLinkedList(){
		head = null;
		numItems = 0;
		tail = null;
	}
	
	/**
     * Adds item to the end of the List.
     * 
     * @param item the item to add
     * @throws IllegalArgumentException if item is null 
     */
	public void add(E item){
		//add at the end
		if (item == null) {
			throw new IllegalArgumentException();
		}
			
		//create a new Listnode	
		DblListnode<E> newNode = new DblListnode<E>(item);
			
		//special case: Empty list	
		if (head == null) {	
			head = newNode;
			tail = newNode;
		}
		else {	
			//traverse	
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		numItems++;	
	}
	
	
    /**
     * Adds item at position pos in the List, moving the items originally in 
     * positions pos through size() - 1 one place to the right to make room.
     * 
     * @param pos the position at which to add the item
     * @param item the item to add
     * @throws IllegalArgumentException if item is null 
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater 
     * than size()
     */
    public void add(int pos, E item){
    	//throws IllegalArgumentException if item is null 
    	if (item == null) {
			throw new IllegalArgumentException();
		}
    	//throws IndexOutOfBoundsException if pos is less than 0 or greater
    	if (pos < 0 || pos > numItems){
			throw new IndexOutOfBoundsException();
		}
  
    	DblListnode<E> newNode = new DblListnode<E> (item);
    	if (pos ==0){
    		if (head == null){
    			add(item);
    			numItems--;
    		}
    		else {
    			newNode.setNext(head);
    			head.setPrev(newNode);
    			head = newNode;
    		}
    	}
    	else {
    		if (pos == numItems) {
    		add(item);
    		numItems--;
    		}
        	else {
        		DblListnode<E> curr = head; 
            	for (int i=0; i<pos; i++){
        			curr = curr.getNext();
        		}	
            	DblListnode<E> currprev = curr.getPrev();
            	newNode.setNext(curr);
            	curr.setPrev(newNode);
            	newNode.setPrev(currprev);
            	currprev.setNext(newNode);
    		}
    	}
    	numItems++;
    }
    	
	
    /**
     * Returns the item at position pos in the List.
     * 
     * @param pos the position of the item to return
     * @return the item at position pos
     * @throws IllegalArgumentException if list is null
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
     * or equal to size()
     */
	public E get(int pos){
		//traverse
		if (head == null) {
			throw new IllegalArgumentException();
		}
		if (pos < 0 || pos >= numItems){
			throw new IndexOutOfBoundsException();
		}
		DblListnode<E> curr = head;
		for (int i=0; i<pos; i++){
			curr = curr.getNext();
		}
		return curr.getData();
	}
	
	

    

    
    /**
     * Returns true iff item is in the List (i.e., there is an item x in the 
     * List such that x.equals(item))
     * 
     * @param item the item to check
     * @return true if item is in the List, false otherwise
     * @throws IllegalArgumentException if item is null or list is null
     */
    public boolean contains(E item){
    	for (int i=0; i<=numItems; i++){
    		if(get((int) i) == item){
    			return true;
    		}
    	}
    	return false;
    }
    

 
    /**
     * Returns true iff the List is empty.
     * 
     * @return true if the List is empty, false otherwise
     */
    public boolean isEmpty(){
    	while (head == null)
    		return true;
    	return false;
    }
    
    /**
     * Removes and returns the item at position pos in the List, moving the 
     * items originally in positions pos+1 through size() - 1 one place to the 
     * left to fill in the gap.
     * 
     * @param pos the position at which to remove the item
     * @return the item at position pos
     * @throws IllegalArgumentException if list is null
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
     * or equal to size()
     */
    public E remove(int pos){
    	if (isEmpty()) {
			throw new IllegalArgumentException();
		}
    	if (pos < 0 || pos >= numItems){
			throw new IndexOutOfBoundsException();
		}
    	
    	DblListnode<E> curr = head;
    	for (int i=0; i<pos; i++) {
    		curr = curr.getNext();
    	}
    	if (pos == 0){
    		//only one item and removed
    		if (numItems ==1){
    			head = null;
    			tail = null;
    		}
    		//remove original head
    		else {
    			head = head.getNext();
    			head.setPrev(null);
    		}
    	}

    	else {
    		//remove tail
    		if (pos == numItems-1) {
    			tail = tail.getPrev();
    			tail.setNext(null);
    			curr = tail;
    		}
    		else {
    		// Step 1: change the prev field of the node after n
    		DblListnode<E> tmp = curr.getNext();
    		tmp.setPrev(curr.getPrev());
    	
    		// Step 2: change the next field of the node before n
    		tmp = curr.getPrev();
    		tmp.setNext(curr.getNext());
    		}
    	}
    	numItems--; 
    	return curr.getData();
    }
    
    /**
     * Returns the number of items in the List.
     * 
     * @return the number of items in the List
     */
    public int size(){
    	return numItems;
    }
}


	


//public static void main(String[] args) {
//DLinkedList<Integer> lt = new DLinkedList<>();
//lt.add(1);
//lt.add(3);
//
//lt.remove(0);
//
//
//System.out.println(lt.size());
//}

