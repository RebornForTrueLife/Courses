/*
	This is the class perform use cases:
		- calculatePrice1: calculate price of given coffee order
	@problem: pricing
	@author: minh
 */

package gemo.pricing;

import gemo.pricing.ItemDrink;
import gemo.pricing.Coffee;
import gemo.pricing.MilkTea;

import java.util.*;



public class Calculator {

	/* Calculate price of given coffee cup */
	public static float calculatePrice1() {
		Coffee cup = new Coffee("morning coffee");
		cup.initializeInformation();
		float price = cup.calculatePrice();
		if (price == -1)			// some wrong info
			price = 0;
		return price;
	}	// close calculatePrice1


	/* Calcualte price of given drink: coffee or milk */
	public static float calculatePrice2() {
		ItemDrink drink;
		// ask for drink kind
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the kind of drink (coffee / milk tea): ");
		String name = scan.next().toLowerCase();
		if (name.contains("coffee"))
			drink = new Coffee(name);
		else if (name.contains("milk"))
			drink = new MilkTea(name);
		else {
			System.out.println("# Unknown drink");
			return 0;
		} 	// close if
		// ask for drink info
		drink.initializeInformation();
		float price = drink.calculatePrice();
		if (price == -1)
			price = 0;
		return price;
	}	// close calculatePrice2


	public static void main(String[] args) {
		System.out.println("Price of your coffee is: $" + Calculator.calculatePrice2());
	}	// close main
	
}	// close Calculator