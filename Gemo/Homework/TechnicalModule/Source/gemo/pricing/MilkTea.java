/*
	Class of MilkTea drink
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.ItemDrink;
import gemo.pricing.Coffee;
import java.util.*;


public final class MilkTea extends ItemDrink {

	boolean isAlmondMilk;			// is adding almond milk?

	
	public MilkTea(String name) {
		super(name);
		setBasePrice(2.25f);			// base price for milk tea
	}	// close constructor


	@Override
	public void initializeInformation() {
		String type;
		String size;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter milk tea info:");
															// set type
		System.out.print("Type (hot / cold / blended): ");
		type = scan.next();
		setType(type);
															// set size
		System.out.print("Size (S / M / L / XL): ");
		size = scan.next();
		setSize(size);
															// is adding cream?
		System.out.print("Is adding cream (1 for yes / 0 for no): ");
		this.isAddCream = getAnswer(scan.next());
															// us almond milk?
		System.out.print("Is almond milk (1 for yes / 0 for no): ");
		this.isAlmondMilk = getAnswer(scan.next());
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
		float price = 0;				// total price
		float additionalPrice = 0;
		// CALCULATE additional price
		additionalPrice = this.calculateAdditionalPrice();
		if (additionalPrice == -1) 		// violate constraints
			return -1;
										// base on whether it's almond milk?
		if (this.isAlmondMilk)
			additionalPrice += 0.5f;
		// calculate price
		price = this.basePrice + additionalPrice;
		return price;
	}	// close calculatePrice

}	// close MilkTea