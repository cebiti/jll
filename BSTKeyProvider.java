/*
 *       Class: BSTKeyProvider
 * Description: Provides step accounting features for the algorithms
 *              contained in any data structure that implements the
 *              dictionary interface. 
 * 
 *      Author: Nikolaos Soulios, ns991300@student.paisley.ac.uk
 *
 *        Date: 14 April, 2000
 */

import Dictionary;
import java.util.*;

public class BSTKeyProvider {
	
	private DictionaryCounter myDict; // The dictionary on which the algorithms will be applied to
	private Random     ranGen;        // Random Number Generator (int primitives will be used)
	private Integer    randomFruit;   // RandomSeeds lead to flowers 
    private int        randomFlower;  // and then to fruits
    
    // I am going use a random number generator to create random int primitives(flower)
    // and use them to create equivalently random Integers (fruit)	
		 
	
	// Constructor
	public BSTKeyProvider(DictionaryCounter newDict) {
		
		myDict = newDict;
		ranGen = new Random();
		
	}	
	
	// Returns a random int primitive in the range [min, max]
	private int getRanIntInRange(int min, int max) {
			
		/*		
		 * Because the output of Random.nextInt(int maxExclusive) lies in the range
		 * [0, maxExclusive), I setup a temporary range in order to get proper
		 * results. Apart from that, the Java Language also has a limited range of int 
		 * primitives.
		 * 
		 */  
		
		int range = max - min + 1;
		int intFruit = ranGen.nextInt(range);
		return (min + intFruit);
		
	}
	
	// Returns a random unique Integer Key in the range [min, max] 
	// as an upward cast to Object
	public Object getRanKeyInRange(int min, int max) {
				
		do {
			randomFlower = getRanIntInRange(min, max);
			randomFruit = new Integer(randomFlower);
							
		} while ( myDict.findElement(randomFruit) != Dictionary.NO_SUCH_KEY );
				
		return (Object)randomFruit;		
	}
    
    // Returns a random unique Integer key in the Range [-2147483648, 2147483647]
    // as an upward cast to Object 		
	public Object getRanKey() {
		
		do {
			randomFlower = ranGen.nextInt();
			randomFruit = new Integer(randomFlower);
							
		} while ( myDict.findElement(randomFruit) != Dictionary.NO_SUCH_KEY );
		
		return (Object)randomFruit;
		
	}
	
	// Returns a random duplicate Integer key as an upward cast to Object
	public Object getDupKey() throws InvalidStateException {
		
		int iteratorSize = myDict.size();
		
		if (iteratorSize == 0) {
			throw new InvalidStateException("Requested Dup, size=0");
		}
		
		int randomIndex = getRanIntInRange(1, iteratorSize);
				
		Iterator duplicateIter = myDict.keys(); 
		
		for (int i = 1; i <= randomIndex; i++) {
			
			randomFruit = (Integer)duplicateIter.next();
			
		}
		
		myDict.enableCount();
		
		return (Object)randomFruit;
		
	}

}
