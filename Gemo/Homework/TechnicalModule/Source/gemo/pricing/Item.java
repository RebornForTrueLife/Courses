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

}	// close Item

