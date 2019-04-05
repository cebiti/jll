public class Item {

    private Object key, elem;
  
  	public Item (Object k, Object e) throws InvalidArgumentException {
  		if (k == null || e == null) {
        	throw new InvalidArgumentException();
   		}
       	key = k;
    	elem = e;
  	}
	
 	public Object key() {
  		 return key;
 	}
  
 	public Object element() { 
  		return elem;
 	}
  
 	public void setKey(Object k) throws InvalidArgumentException { 
      	if (k == null) {
    		throw new InvalidArgumentException();
	    }
      	key = k;
    } 
  
 	public void setElement(Object e)throws InvalidArgumentException { 
 		if (e == null) {
 			throw new InvalidArgumentException();
 		}
 		elem = e;
 	}
} 
