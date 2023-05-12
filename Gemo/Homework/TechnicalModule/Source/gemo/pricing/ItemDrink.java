/*
	An abstract class for Drink Item
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.Item;


public abstract class ItemDrink extends Item {
	
	String type;					// hot, cold, blended...
	String size;					// S, M, L, XL
	int isAddCream;				// 1: yes, 0: no
	float basePrice;				// base price to calculate total price
	int isAddChocolateSouce;		// 1: yes, 0: no


	// Constructor
	ItemDrink(String name) {
		this.name = name;
	}	// close constructor


	// read input info of drink
	public abstract void initializeInformation();


	public float calculateAdditionalPrice() {
		float additionPrice = 0;
		switch (this.type) {			// base on Type
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
		switch (this.size) {			// base on Size
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
		switch (this.isAddCream) {	// base on adding cream?
		case 1:
			additionPrice += 0.5;
		case 0:
			break;
		default:
			System.out.println("Unkown whether adding cream or not");
			return -1;
		}	// close switch
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


	// set isAddCream: check or convert into standard form
	void setIsAddCream(int isAddCream) {
		if (isAddCream > 0)
			this.isAddCream = 1;
		else
			this.isAddCream = 0;
	}	// close setIsAddCream


	// set IsAddChocolateSouce
	void setIsAddChocolateSouce(int isAddChocolateSouce) {
		if (isAddChocolateSouce > 0) 
			this.isAddChocolateSouce = 1;
		else 
			this.isAddChocolateSouce = 0;
	}	// close setIsAddChocolateSouce


	// set base price
	void setBasePrice(float basePrice) {
		if (basePrice > 0)
			this.basePrice = basePrice;
		else
			this.basePrice = 0;
	}	// close setBasePrice

}	// close ItemDrink