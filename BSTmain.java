import java.io.*;
import java.util.*;

public class BSTmain
{
	public static void displayMenu()
	{
    System.out.println();
		System.out.println("Menu");
		System.out.println("====");
		System.out.println(" 1. Insert An Item");
		System.out.println(" 2. Find An Element");
		System.out.println(" 3. Find All Elements");
		System.out.println(" 4. Remove an Item");
		System.out.println(" 5. Remove All Items");
		System.out.println(" 6. Iterator for keys");
		System.out.println(" 7. Iterator for elements");
		System.out.println(" 8. Benchmark");
		System.out.println(" 9. Start with a new Tree");
		System.out.println("10. Exit");
		System.out.println();
	}
	
	public static void main (String[] args)throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader bf=new BufferedReader (isr);
		
		ComparatorCounter compareKeys = new CompInteger();
		DictionaryCounter theDictionary = new BSTree(compareKeys);
		Integer currentKey = null;
		String currentElement = null;
		
		int loopNumber = 0;
		boolean exitLoop = false;
		String stringKey = null;
		Object element = null;
		
		while (! exitLoop)
		{
			loopNumber++;
			displayMenu();
		
			System.out.print("Enter option and press enter: ");
			String option = bf.readLine();
			
			switch( (new Integer(option)).intValue() )	
			{
			case 1:
				System.out.print("Enter an integer key to insert: ");
				stringKey = bf.readLine();
				currentKey = new Integer(stringKey);
				currentElement = stringKey + "_" + loopNumber;
				System.out.println();
				System.out.println("Element for this key is : " + currentElement);
				theDictionary.insertItem(currentKey, currentElement);
				break;
			case 2:
				System.out.print("Enter an integer key to find: ");
				stringKey = bf.readLine();
				currentKey = new Integer(stringKey);
				element = theDictionary.findElement(currentKey);
				if (element instanceof String) {
					currentElement = (String) element;
					System.out.println("Element returned: " + currentElement);
				} else {
					if (element == Dictionary.NO_SUCH_KEY) {
						System.out.println("Key not present");
					} else {
						System.out.println("ERROR: expected NO_SUCH_KEY");
					}
				}
				break;
			case 3:
				System.out.print("Enter an integer key to find: ");
				stringKey = bf.readLine();
				currentKey = new Integer(stringKey);
				Iterator elements = theDictionary.findAllElements(currentKey);
				while (elements.hasNext()) {
					currentElement = (String)(elements.next());
					System.out.println("Retrieved: " + currentElement);
				}
				System.out.println("No more elements with key " + currentKey);
				break;
			case 4:
				System.out.print("Enter an integer key to remove: ");
				stringKey = bf.readLine();
				currentKey = new Integer(stringKey);
				element = theDictionary.remove(currentKey);
				if (element instanceof String) {
					currentElement = (String) element;
					System.out.println("Element returned: " + currentElement);
				} else {
					if (element == Dictionary.NO_SUCH_KEY) {
						System.out.println("Key not present");
					} else {
						System.out.println("ERROR: expected NO_SUCH_KEY");
					}
				}				
				break;
			case 5:
				System.out.print("Enter integer key to remove all: ");
				stringKey = bf.readLine();
				currentKey = new Integer(stringKey);
				Iterator removed = theDictionary.removeAll(currentKey);
				while (removed.hasNext()) {
					currentElement = (String)(removed.next());
					System.out.println("Removed: " + currentElement);
				}
				System.out.println("No more elements with key " + currentKey);
				break;
			case 6:
				System.out.println("Tree contains following keys: ");
				Iterator theKeys = theDictionary.keys();
				while (theKeys.hasNext()) {
					currentKey = (Integer)(theKeys.next());
					System.out.println(currentKey);
				}
				break;
			case 7:
				System.out.println("Tree contains following elements: ");
				Iterator theElements = theDictionary.elements();
				while (theElements.hasNext()) {
					currentElement = (String)(theElements.next());
					System.out.println(currentElement);
				}
				break;
			case 8:
				System.out.print("\nEnter the number of insertions: ");
				String numInsString = bf.readLine();
				System.out.print("\nEnter the percentage of duplicate insertions in float (0-1)");
								
				String percFloatString = bf.readLine();
				
				int numIns = new Integer(numInsString).intValue();
				float percFloat = new Float(percFloatString).floatValue();
				
				System.out.println("Which algorithm would you like to test?\nYou will only see information for that algorithm.\n");
				System.out.println("1. Random Non Existing Key Generation");
				System.out.println("2. Random Duplicate Key Generation");
				System.out.println("3. Both");
				
				String algorithmChoiseString = bf.readLine();
			
				int algorithmChoise =  (new Integer(algorithmChoiseString)).intValue(); 
				
				theDictionary = new BSTree(compareKeys);
								
				benchmark(theDictionary, compareKeys, numIns, percFloat, algorithmChoise);
				break;
				
			case 9:
				theDictionary = new BSTree(compareKeys);
				break;
				
			case 10:
				exitLoop = true;
				break;
				
		}
	  }
	} //end main
	
	public static void benchmark(DictionaryCounter dict, ComparatorCounter comp, int n, float p, int choice) throws FileNotFoundException, IOException {
		
        if (n < 1 || p < 0 || p >= 1) {
			System.out.println("You entered invalid data. Try Again");
			return;
		}
		
		FileOutputStream getDupFOS = new FileOutputStream("getDup.txt");
		FileOutputStream getRanFOS = new FileOutputStream("getRan.txt");
		FileOutputStream insertFOS = new FileOutputStream("insert.txt");
		FileOutputStream removeFOS = new FileOutputStream("remove.txt");
		FileOutputStream     seqRD = new FileOutputStream("seq.txt");
		FileOutputStream keysInsFOS = new FileOutputStream("inskeys.txt");
		FileOutputStream keysRemFOS = new FileOutputStream("remkeys.txt");
						
		PrintWriter getDupPwr = new PrintWriter(getDupFOS, true);
		PrintWriter getRanPwr = new PrintWriter(getRanFOS, true);
		PrintWriter insertPwr = new PrintWriter(insertFOS, true);
		PrintWriter removePwr = new PrintWriter(removeFOS, true);
		PrintWriter  seqRDPwr = new PrintWriter(seqRD, true);
		PrintWriter   keysInsPwr = new PrintWriter(keysInsFOS, true);
		PrintWriter   keysRemPwr = new PrintWriter(keysRemFOS, true);		
		
		float inDuplic = n * p;
		float inRandom = n - inDuplic;
		int countDup = 0;
		int countRan = 0;
		Random coin = new Random();
		int coinResult = 0;
		
		getDupPwr.println("Steps taken to generate " + inDuplic + " duplicate keys\n" );
		getRanPwr.println("Steps taken to generate " + inRandom + " random keys\n");
		insertPwr.println("Steps taken to insert "   + inDuplic + " duplicate and " + inRandom +" random keys\n");
		removePwr.println("Steps taken to remove "   + n        + " duplicate keys\n");
		 seqRDPwr.println("Sequence of keys\n");
		  keysInsPwr.println("Keys During Insertion\n");
		  keysRemPwr.println("Keys during Removal");
		 
		System.out.println("R:     " + inRandom + "D: " + inDuplic);
		BSTKeyProvider engine = new BSTKeyProvider(dict);
		
		if (p != 0) { 
			
			do {
				
				coinResult = coin.nextInt(2);
				
				
				if (coinResult == 0 && countRan < inRandom) {
					
					seqRDPwr.println("R");
					
					dict.rstCount();
					
					dict.enableCount();
						Object randomKey = engine.getRanKeyInRange(0, n*100);
					dict.disableCount();
					
					keysInsPwr.println(((Integer)randomKey).toString());
					
					int generationSteps = dict.getCount();
					getRanPwr.println(generationSteps);

					dict.rstCount();
					
					dict.enableCount();
						String element = "Random Insertion " + (countRan+1);
						dict.insertItem(randomKey, element);
					dict.disableCount();
					
					int actionSteps = dict.getCount();
					
					insertPwr.println(actionSteps);
					
					if (choice == 1 || choice == 3) {
						System.out.println("Random Insertion: " + formatNum(countRan+1, 5) + "Key Inserted->" + formatNum( ((Integer)randomKey).intValue(), 10) + " Algo=" + formatNum(generationSteps, 10) + " BST=" + actionSteps);
						
					}
					
					countRan++;
					
				} else if (countDup < inDuplic) {
					
					try {
					
						dict.rstCount();
						
						dict.enableCount();
							Object duplicKey = engine.getDupKey();
						dict.disableCount();
						
						seqRDPwr.println("D");
						
						keysInsPwr.println(((Integer)duplicKey).toString());
					
						int generationSteps = dict.getCount();
						getDupPwr.println(generationSteps);
					
						dict.rstCount();
						
						dict.enableCount();
							String element = "Duplic Insertion " + (countDup+1);
							dict.insertItem(duplicKey, element);
						dict.disableCount();
						
						int actionSteps = dict.getCount();
						insertPwr.println(actionSteps);
						
						if (choice == 2 || choice == 3) {
							System.out.println("Duplic Insertion: " + formatNum(countDup+1, 5) + "Key Inserted->" + formatNum( ((Integer)duplicKey).intValue(), 10) + " Algo=" + formatNum(generationSteps, 10) + " BST=" + actionSteps);
							
						}
						countDup++;
						
					} catch (InvalidStateException e) {
						System.out.println("GetDuplicate was requested but size==0");
					}
				
				}
						
			} while ( (countDup + countRan) < n );
			
	
		} else {
			
			for (int i = 0; i < n; i++) {
				
				seqRDPwr.println("R");
				
				dict.rstCount();
				
				dict.enableCount();
					Object randomKey = engine.getRanKeyInRange(0, n*100);
				dict.disableCount();
				
				keysInsPwr.println(((Integer)randomKey).toString());
				
				
					
				int generationSteps = dict.getCount();
				
				getRanPwr.println(generationSteps);
					
				dict.rstCount();
					
				dict.enableCount();
					String element = "Random Insertion: " + (i+1);
					dict.insertItem(randomKey, element);
				dict.disableCount();
					
				int actionSteps = dict.getCount();
				
				insertPwr.println(actionSteps);
				
				System.out.println("Random Insertion: " +     formatNum((i+1), 5) + "Key Inserted->" + formatNum( ((Integer)randomKey).intValue(), 10) + " Algo=" + formatNum(generationSteps, 10) + " BST=" + actionSteps);
				
			}
		}
		
		System.out.println("Total number of insertions: " + n);
		
		System.out.println("Press enter to remove the " + n + " items which have just been inserted");
		String confirmString =  (new BufferedReader(new InputStreamReader(System.in))).readLine();
		
		for (int i = 0; i < n; i++) {
		  
            
            dict.rstCount();
            dict.enableCount();
                Object duplicKey = engine.getDupKey();
            dict.disableCount();
            
            keysRemPwr.println(((Integer)duplicKey).toString());
		  
            int generationSteps = dict.getCount();
		  
            dict.rstCount();
			
            // The element does not get printed on the screen, but it is nice
            // to have it trapped inside a String object. It also helps in benchmarking
            // the BSTRee remove(Object key) method.
            
            dict.enableCount();
                String element = (String)dict.remove(duplicKey);
            dict.disableCount();
            
            
            
            int actionSteps = dict.getCount();
            
            removePwr.println(actionSteps);
            
            System.out.println("Duplic  Deletion: " +     formatNum((i+1), 5) + "Key Deleted->" + formatNum( ((Integer)duplicKey).intValue(), 10) + " Gen=" + formatNum(generationSteps, 10) + "Action=" + actionSteps);
		 
		 }
		 
		 getDupFOS.close();
		 getRanFOS.close();
		 insertFOS.close();
		 removeFOS.close();
		 seqRD.close();
		 keysInsFOS.close();
		 keysRemFOS.close();
 
	}
	
	public static String formatNum(int num, int width) {
		
		String retString = (new Integer(num)).toString();
		
		int spaces = width - ( (new Integer(num)).toString().length() );
		
		for (int i=0; i < spaces; i++) {
			retString += " ";
			
		}
		
		return retString;
		
	}
	
}
