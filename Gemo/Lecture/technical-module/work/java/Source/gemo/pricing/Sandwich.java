/*
	A class to represent a sandwich
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.ItemFood;

import java.util.*;


public class Sandwich extends ItemFood {
	
	boolean isEgg;			// is adding egg?
	boolean isTurkey;		// is adding turkey?


	// constructor
	public Sandwich(String name) {
		super(name);
		setBasePrice(3);
	}	// close constructor


	@Override
	public void initializeInformation() {
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		String answer;
		// get information
		System.out.println("Please enter the information of the sandwich: ");
																		// adding egg?
		System.out.print("Do you want to add egg? (yes/no): ");
		answer = scan.next();
		this.isEgg = getAnswer(answer);
																		// adding turkey?
		System.out.print("Do you want to add turkey? (yes/no): ");
		answer = scan.next();
		this.isTurkey = getAnswer(answer);
	}	// close initializeInformation


	@Override
	public float calculatePrice() {
		float additionalPrice = 0;
		// Calculate additional price
											// base on adding egg
		if (this.isEgg)
			additionalPrice += 1;
											// base on adding turkey
		if (this.isTurkey)
			additionalPrice += 1;	
		// calcualte price
		return (this.basePrice + additionalPrice);
	}	// close calculatePrice

}	// close Sandwich