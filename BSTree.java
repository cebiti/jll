/*
 *       Class: BSTRee
 *
 * Description: Binary Search Tree class.  Each tree comprises a set of internal
 *              nodes each containing an item (a key/element pair), ordered on their keys
 *              according to the total order relation defined by the comparator comp, and a
 *              set of empty external nodes (leaves).
 * 
 *      Author: Nikolaos Soulios, ns991300@student.paisley.ac.uk
 *
 *        Date: 14 April, 2000
 */

import java.util.*;

public class BSTree implements DictionaryCounter {
	
	protected BSTNode root;
  	protected int size;
  	// Since we are conducting an experiment our comparator must implement
  	// the counter interface
  	protected ComparatorCounter comp;
  	
  	// The BSTree should also be aware of the counting Feature
  	private boolean countFeature;
  	
  	
  	
 	public BSTree(ComparatorCounter c) {
		
		comp = c ;
    	size = 0;
    	countFeature = false;
    	root = new BSTNode();
    	 
  	}
  	
  	// The following is a collection of wrapper methods for the ComparatorCounter
  	
  	// Enable the accounting feature
  	public void enableCount() {
    	comp.enableCount();
    }
    
    // Disable the accounting feature
    public void disableCount() {
    	comp.disableCount();
    }
    
    // Accesor: Get the current number of steps
    public int getCount() {
    	return comp.getCount();
    }
    
    // Accesor: Set the counter to a specific value
    public void setCount(int a) {
    	comp.setCount(a);
    }
    // Accesor: Reset the Counter
    public void rstCount() {
    	comp.rstCount();
    }

    // Returns the number of Nodes in the Binary Search Tree
  	public int size() {
    	return size;
  	}
    
    // Returns true if the Binary Search Tree is empty
  	public boolean isEmpty() {
    	return (size == 0);
  	}
  	
  	// Inserts an Item, consisting of a key/element pair
  		
  	public void insertItem(Object k, Object e) throws InvalidKeyException, InvalidArgumentException {
  
  		if (k == null || e == null) {
  			throw new InvalidArgumentException();
  		}
  		
  		if (!comp.isComparable(k)) {
  			throw new InvalidKeyException();
  		}
		
  		BSTNode insNode = treeSearch(k, root);
  		
  		if (insNode.isExternal()) {
  			insNode.expandExternal(k, e);
  			size++;
  		} else {
  			
  			BSTNode restTree = insNode.getRight();
  			insNode.setRight(new BSTNode());
  			insNode.getRight().expandExternal(k, e);
  			insNode.getRight().setRight(restTree);
  			size++;
  			
  		}
  	}
  	  	
  	
  	// If the Object k, exists as a Key in any of the Nodes in the BST, it deletes that Node
  	// and returns its element 
  	public Object remove (Object k) throws InvalidKeyException, InvalidArgumentException {
	    
	    if (k == null) {
	    	throw new InvalidArgumentException();
	    }
	    
	    if (!comp.isComparable(k)) {
	    	throw new InvalidKeyException();
	    }
	    
	    BSTNode remove = treeSearch(k, root); 
	    
	    if (remove.isExternal()) {
	    	return Dictionary.NO_SUCH_KEY;
	    }
	    	    
	    Object removeElement = remove.element();
	    
	    BSTNode parent = treeSearchParent(k, root);
    
	    if (remove == root) {
	    	root = root.delete();
	    	size--;
	    } else {
	    	
	    	if (remove == parent.getLeft()) {
	      		parent.setLeft(remove.delete());
	    		size--;
	    	} else if (remove == parent.getRight()) {
	       		parent.setRight(remove.delete());
	    		size--;
	    	} else {
	    		System.out.println("No remove branch executed. This a logical error");
	    	}
	    	
	    }
	    
	    return removeElement;
	}
    
    // If the Object k, exists as a Key in any of the Nodes in the BST, it returns its element
  	public Object findElement (Object k) throws InvalidKeyException, InvalidArgumentException {
     	
     	if (k == null) {
     		throw new InvalidArgumentException();
     	}
     	
     	if (!comp.isComparable(k)) {
     		throw new InvalidKeyException();
     	}
    
     	BSTNode searchNode = treeSearch(k, root);
     	
     	if (searchNode.isExternal()) {
     		return Dictionary.NO_SUCH_KEY;
     	} else {
     		return searchNode.element();
     	}
     		
     	
	}
	
	// Returns an Iterator that contains all the elements of the Nodes having Object k as their key.
	// It also deletes the Nodes mentioned above.
  	public Iterator removeAll (Object k) throws InvalidKeyException, InvalidArgumentException {
    
    	  Object element;
    	  Vector thisVector = new Vector(3,3);
    	  
    	  
    	  while ( (element = remove(k)) != Dictionary.NO_SUCH_KEY ) {
    	  	thisVector.add(element);
    	  }
    	      	  
    	  return thisVector.iterator();
    
  	}
    
    // Returns an Iterator that contains all the elements of the Nodes having Object k as their key.
  	public Iterator findAllElements (Object k) throws InvalidKeyException, InvalidArgumentException {
  		if (k == null) {
  			throw new InvalidArgumentException();
  		}
  		
  		if (!comp.isComparable(k)) {
  			throw new InvalidKeyException();
  		}
  		
  		return new TreeIterator(k);
  	}

  	// Returns an Iterator with all the keys in the BST
  	public Iterator keys() {
      	return new TreeIterator(0);
  	}
    
    // Returns an Iterator with all the elements in the BST
  	public Iterator elements() {
       	return new TreeIterator(1); 
  	}
    
    // Searches a tree for the key k. and returns the Node
    // where that key resides(would reside) in
    protected BSTNode treeSearch (Object k, BSTNode node) {
    	
    	BSTNode searchNode = node;
     	
     	while ( !searchNode.isExternal() ) {
     		
     		if ( comp.isEqualTo( k, searchNode.key()) ) {
     			return searchNode;
     		} else if ( comp.isGreaterThan(k, searchNode.key()) ) {
     			searchNode = searchNode.getRight();
     		} else {
     			searchNode = searchNode.getLeft();
     		}
     		
     	}
     	
     	return searchNode;
    
  	}
	
	// Finds the parent of a given Node (if that is an existing node)
	protected BSTNode treeSearchParent (Object k, final BSTNode root) {
    	
    	BSTNode searchNode = root;
     	BSTNode parentNode = root;
     	
     	while ( !searchNode.isExternal() && !comp.isEqualTo(searchNode.key(), k) ) {
     		
     		parentNode = searchNode;
     		
     		if ( comp.isGreaterThan(k, searchNode.key()) ) {
     			searchNode = searchNode.getRight();
     		} else {
     			searchNode = searchNode.getLeft();
     		}
     		
     	}
     	
     	return parentNode;
    
  	}
 	
 	// Inner class
	protected class TreeIterator implements Iterator {

		private Vector myVector;
		private Iterator iter;
		
    	/* sw is 0 for adding keys to the vector
		 * sw is 1 for adding elements to the vector
		 */ 
		   
		private int sw; 
		
		// This will be a valid reference if we are searching for a key.
		private Object searchKey;
	
		public TreeIterator(int arg) { 
	  		sw = arg; 	
	  		myVector = new Vector(3,3);
	  		createVector(root);
	  		iter = myVector.iterator();	  		
	  	}
	  	
	  	public TreeIterator(Object skey) { 
	  		myVector = new Vector(3,3);
	  		createVector(root);
	  		iter = myVector.iterator();	  		
	  	}  

    	public boolean hasNext() {
	    	return iter.hasNext(); 
	  	} 

    	public Object next() throws NoSuchElementException {
      		return iter.next();
    	} 

 	  	public void remove() throws UnsupportedOperationException {
      		throw new UnsupportedOperationException("remove not supported");
    	}
    	    	
    	public Vector getVector() {
    		return myVector;
    	} 
    	
    	private void createVector(BSTNode snode) {
    		
    		// The following is a tree traveral.
    		// Depending on the way that the constructor was called
    		// the vector can be fed with:
    		// -All the elements/keys in the tree
    		// -All the elements which are accompanied by a particular key
    		
    		if (snode.getLeft() != null) {
    			createVector( snode.getLeft() );
    		}
    		
    		if (!snode.isExternal()) {
    			
    			if (searchKey == null) {
    				switch(sw) {
    					case 0:
	    					myVector.add(snode.key());
    						break;
    					case 1:
	    					myVector.add(snode.element());
    						break;
    				}
    			} else {
    				if ( comp.isEqualTo(searchKey, snode.key()) ) {
    					myVector.add(snode.element());
    				}
    			}
    		
    		}
    		
    		if (snode.getRight() != null) {
    			createVector(snode.getRight());
    		}
  		}
	}
}