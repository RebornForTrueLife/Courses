/*
	A class represent a bagel
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.ItemFood;

import java.util.*;


public class Bagel extends ItemFood {
	
	boolean isButter;			// adding butter?
	boolean isCreamCheese;		// adding cream cheese?


	// constructor
	public Bagel(String name) {
		super(name);
		setBasePrice(3);
	}	// close constructor


	@Override
	public void initializeInformation() {
		Scanner scan = new Scanner(System.in);
		String answer;
		// set information
		System.out.println("Please enter information of the bagel: ");
															// adding butter?
		System.out.print("Do you want to add butter? (yes/no): ");
		answer = scan.next();
		this.isButter = getAnswer(answer);
															// adding cream cheese
		System.out.print("Do you want to add cream cheese? (yes/no)");
		answer = scan.next();
		this.isCreamCheese = getAnswer(answer);
	}	// close initializeInformation


	@Override
	public float calculatePrice()	{
		float additionalPrice = 0;
		// Calculate additional price
															// base on adding butter
		if (this.isButter)
			additionalPrice += 0.5f;
															// base on adding cream cheese
		if (this.isCreamCheese)
			additionalPrice += 0.5f;
		// Calculate price
		return (this.basePrice + additionalPrice);
	}	// close calculatePrice
}	// close Bagel
