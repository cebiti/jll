/*
 *       Class: BSTNode
 *
 * Description: This class includes all the necessary members and methods
 *              a Binary Search tree should have. 
 * 
 *      Author: Nikolaos Soulios, ns991300@student.paisley.ac.uk
 *
 *        Date: 14 April, 2000
 */

public class BSTNode {

	protected Item data;  
	protected BSTNode left;
	protected BSTNode right;
		
	
	// Constructor. By default it creates an external node.
	public BSTNode() {
		 
		data = null;
		left = null;
		right = null;
	}

	// Returns the left child
	public BSTNode getLeft() {
		return left;
	}
    
    // Returns the right child
	public BSTNode getRight() {
		return right;
	}

	// Accesor: Returns the key stored in the node
	public Object key() throws InvalidStateException {
		if (isExternal()) throw new InvalidStateException();
		return data.key();
	}
    
    // Accesor: Returns the element stored in the node
	public Object element() throws InvalidStateException {
		if (isExternal()) throw new InvalidStateException();
		return data.element();
	}
    
    // Accessor: Updates the left child with a new node
	public void setLeft(BSTNode node) throws InvalidStateException, InvalidArgumentException {
		if (isExternal()) throw new InvalidStateException();
		if (node == null) throw new InvalidArgumentException();
		left = node;
	}
    
    // Accessor: Updates the right child with a new node
	public void setRight(BSTNode node) throws InvalidStateException, InvalidArgumentException {
		if (isExternal()) throw new InvalidStateException();
		if (node == null) throw new InvalidArgumentException();
		right = node;
	}
    
    // Accesor: Updates the key
	public void setKey(Object k) throws InvalidStateException, InvalidArgumentException {
		if (isExternal()) throw new InvalidStateException();
		if (k == null) throw new InvalidArgumentException();
		data.setKey(k);
	}
    
    // Accesor: Updates the element
	public void setElement(Object e) throws InvalidStateException, InvalidArgumentException {
		if (isExternal()) throw new InvalidStateException();
		if (e == null) throw new InvalidArgumentException();
		data.setElement(e);
	}
    
    // Returns true if the node is an external node
	public boolean isExternal() {
		return (left == null && right == null);
	}

	// Expands an external node, updating its Item and its left/right childs
	public void expandExternal(Object k, Object e) throws InvalidStateException, InvalidArgumentException {
		if ( !isExternal()) throw new InvalidStateException();
		data = new Item(k, e);
		left = new BSTNode();
		right = new BSTNode();
	}

 	// Deletes the node
 	public BSTNode delete() throws InvalidStateException, InvalidArgumentException {
   		   		   		
   		if (this.isExternal()) throw new InvalidStateException();
   		
   		BSTNode current, previous;
    	
    	if (this.left.isExternal()) {
      		return this.right;
    	} else if (this.right.isExternal()) {
      		return this.left;
    	} else {
      		
      		previous = this;
      		current = this.right;
      		
      		while (!current.left.isExternal()) {
        		previous = current;
        		current  = current.left;
      		}
      	
      		this.data.setKey(current.data.key());
      		this.data.setElement(current.data.element());
      	
      		if (previous != this) { 
	        	previous.left = current.right;
    	  	} else { 
        		this.right = current.right;
      		}
	      	
      		return this;
		}
 	} 
} 
