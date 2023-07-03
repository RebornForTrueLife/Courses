/*
	An abstract class for Drink Item
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.Item;


public abstract class ItemDrink extends Item {
	
	String type;						// hot, cold, blended...
	String size;						// S, M, L, XL
	boolean isAddCream;				// adding cream?
	float basePrice;					// base price to calculate total price
	boolean isAddChocolateSouce;	// adding chocolate sauce?
	int numAddChocolateSauce;		// number of chocolate pumps 


	// Constructor
	ItemDrink(String name) {
		this.name = name;
	}	// close constructor


	// read input info of drink
	public abstract void initializeInformation();


	public float calculateAdditionalPrice() {
		float additionPrice = 0;
												// base on Type
		switch (this.type) {			
		case "hot":
		case "cold":
			break;
		case "blended":
			additionPrice += 1;
			break;
		default:
			System.out.println("Unknown type");
			return -1;
		}	// close switch
												// base on size
		switch (this.size) {			
		case "s":
			break;
		case "m":
			additionPrice += 0.5;
			break;
		case "l":
			if (this.type.equals("hot")) {
				System.out.println("# No size 'L' for 'hot' type");
				return -1;
			}	// close if
			additionPrice += 1;
			break;
		case "xl":
			if (this.type.equals("hot")) {
				System.out.println("# No size 'XL' for 'hot' type");
				return -1;
			}	// close if
			additionPrice += 1.5;
			break;
		default:
			System.out.println("Unkown size");
			return -1;
		}	// close switch
											// base on adding cream?
		if (this.isAddCream)
			additionPrice += 0.5f;
											// base on adding chocolate sauce
		if (this.numAddChocolateSauce > 2)		// 2 first pumps are free
			if (!this.type.equals("hot")) {			
					System.out.println("# Chocolate sauce is only available for hot drink");
					return -1;
				}	// close if
			else 
				additionPrice += (this.numAddChocolateSauce - 2) * 0.5;		// $.5 for each extra pumps

		return additionPrice;
	}	// close calculateAdditionalPrice


	// set type: also check if type is valid
	void setType(String type) {
		type = type.trim().toLowerCase();
		switch (type) {
		case "hot":
		case "cold":
		case "blended":
			this.type = type;
			break;
		default:
			this.type = "";
		}	// close switch
	}	// close setType


	// set size: also check if size is valid
	void setSize(String size) {
		size = size.trim().toLowerCase();
		switch (size) {
		case "s":
		case "m":
		case "l":
		case "xl":
			this.size = size;
			break;
		default:
			this.size = "";
		}	// close switch
	}	// close setSize


	// set base price
	void setBasePrice(float basePrice) {
		if (basePrice > 0)
			this.basePrice = basePrice;
		else
			this.basePrice = 0;
	}	// close setBasePrice


	// set IsAddChocolateSauce: set as 0 if not add, otherwise, represent number of pumps
	void setNumAddChocolateSauce(int numAddChocolateSauce) {
		if (numAddChocolateSauce <= 0) 
			this.numAddChocolateSauce = 0;
		else if (numAddChocolateSauce <= 6)
			this.numAddChocolateSauce = numAddChocolateSauce;
		else
			this.numAddChocolateSauce = 6;		// maximum is 6 pumps
	}	// close setIsAddChocolateSauce

}	// close ItemDrink