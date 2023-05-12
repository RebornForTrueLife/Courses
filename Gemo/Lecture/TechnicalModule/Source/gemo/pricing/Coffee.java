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
		setBasePrice(2f);		// default base price of coffee
	}	// clsoe ItemDrink


	// Initialize information
	@Override
	public void initializeInformation() {
		// get information
		String type;				// coffee type
		String size;				// size
		int isAddCream;		// adding cream?
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		System.out.println("Please input coffee information: ");
																// set type
		System.out.print("Type (hot / cold / blended) : ");	
		type = scan.next();
		setType(type);
																// set size
		System.out.print("Size (S / M / L / XL): ");
		size = scan.next();
		setSize(size);
																// is adding cream?
		System.out.print("Is adding cream (yes/no): ");
		this.isAddCream = getAnswer(scan.next());
																// adding chocolate sauce?
		int numAddChocolateSauce = 0;
		if (this.type.equals("hot")) {
			System.out.print("How many pumps of chocolate sauce you want?(0 means no): ");
			numAddChocolateSauce = scan.nextInt();
		}	// close if
		setNumAddChocolateSauce(numAddChocolateSauce);
	}	// close initializeInformation
	
	
	@Override
	public float calculatePrice() {
		// CALCULATE Additional PRICE of a coffee order
		float additionPrice = 0;		// additional price
		additionPrice = this.calculateAdditionalPrice();
		if (additionPrice == -1) 		// violate contraints
			return 0;
		// CALCULATE total PRICE
		return (this.basePrice + additionPrice);
	}	// close calculatePrice

}	// close Coffee