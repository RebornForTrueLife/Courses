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

	int isAlmondMilk;					// is adding almond milk?

	
	public MilkTea(String name) {
		super(name);
		this.basePrice = 2.25f;		// base price for milk tea
	}	// close constructor


	@Override
	public void initializeInformation() {
		String type;
		String size;
		int isAddCream;
		int isAlmondMilk;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter milk tea info:");
		System.out.print("Type (hot / cold / blended): ");
		type = scan.next();
		System.out.print("Size (S / M / L / XL): ");
		size = scan.next();
		System.out.print("Is adding cream (1 for yes / 0 for no): ");
		isAddCream = scan.nextInt();
		System.out.print("Is almond milk (1 for yes / 0 for no): ");
		isAlmondMilk = scan.nextInt();
		setType(type);
		setSize(size);
		setIsAddCream(isAddCream);
		setIsAlmondMilk(isAlmondMilk);
	}	// close initializeInformation


	@Override
	public float calculatePrice() {
		float price = 0;				// total price
		float additionalPrice = 0;
		// CALCULATE additional price
		additionalPrice = this.calculateAdditionalPrice();
		if (additionalPrice == -1) 		// violate constraints
			return -1;
		
		switch (this.isAlmondMilk) {			// base on whether it's almond milk?
		case 1:
			additionalPrice += 0.5f;
			break;
		case 0:
			break;
		default:
			System.out.println("# Unknown whether this is almond milk or normal milk");
			return -1;
		}	// close switch
		// calculate price
		price = this.basePrice + additionalPrice;
		return price;
	}	// close calculatePrice


	// set isAlmondMilk
	void setIsAlmondMilk(int isAlmondMilk) {
		if (isAlmondMilk > 0)
			this.isAlmondMilk = 1;
		else 
			this.isAlmondMilk = 0;
	}	// close setIsAlmondMilk
}	// close MilkTea