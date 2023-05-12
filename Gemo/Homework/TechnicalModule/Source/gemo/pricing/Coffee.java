/*
	A coffee item
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.Item;
import gemo.pricing.ItemDrink;
import java.util.*;


public final class Coffee extends ItemDrink {

	public Coffee(String name) {
		super(name);
		this.basePrice = 2f;		// default base price of coffee
	}	// clsoe ItemDrink


	// Initialize information
	@Override
	public void initializeInformation() {
		// get information
		String type;				// coffee type
		String size;				// size
		int isAddCream;		// adding cream?
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input coffee information: ");
		System.out.print("Type (hot / cold / blended) : ");	
		type = scan.next();
		System.out.print("Size (S / M / L / XL): ");
		size = scan.next();
		System.out.print("Is adding cream (1 for yes / 0 for no): ");
		isAddCream = scan.nextInt();
		// set info
		setType(type);
		setSize(size);
		setIsAddCream(isAddCream);
	}	// close initializeInformation
	
	
	@Override
	public float calculatePrice() {
		float price = 0;				// total price
		// CALCULATE Additional PRICE of a coffee order
		float additionPrice = 0;		// additional price
		additionPrice = this.calculateAdditionalPrice();
		if (additionPrice == -1) 		// violate contraints
			return -1;
		// CALCULATE total PRICE
		price += this.basePrice + additionPrice;
		return price;
	}	// close calculatePrice

}	// close Coffee