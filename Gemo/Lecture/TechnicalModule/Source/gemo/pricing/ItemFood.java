/* 
	An abstract class for breakfast(food) item
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.Item;


public abstract class ItemFood extends Item {
	
	float basePrice;			// base price of the food


	// constructor
	public ItemFood(String name) {
		this.name = name;
	}	// close constructor


	/* to initialize information of the food */
	public abstract void initializeInformation();


	// setBasePrice
	public void setBasePrice(float basePrice) {
		if (basePrice > 0)
			this.basePrice = basePrice;
		else 
			this.basePrice = 0;
	}	// close setBasePrice

}	// close ItemFood