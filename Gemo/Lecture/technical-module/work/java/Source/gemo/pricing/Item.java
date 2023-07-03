/*
	An astract class for an Item
	@problem: pricing
	@author: minh
 */

package gemo.pricing;


public abstract class Item {
	
	String kind;			// drink or food?
	String name;		// eg: small hot coffee?...

	/* to calcualte the price of this item */
	public abstract float calculatePrice();


	// convert yes/no to boolean
	public static boolean getAnswer(String answer) {
		answer = answer.toLowerCase().trim();
		if (answer.equals("yes"))
			return true;
		else 
			return false;
	}	// close getAnswer

}	// close Item

