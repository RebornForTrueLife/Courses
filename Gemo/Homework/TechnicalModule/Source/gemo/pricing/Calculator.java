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
import gemo.pricing.ItemFood;
import gemo.pricing.Sandwich;
import gemo.pricing.Bagel;

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


	public static float calculatePrice3() {
										// drink options
		float drinkPrice = calculatePrice2();
										// breakfast options
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to have breakfast? (yes/no): ");
		String answer = scan.next();
		if (!Item.getAnswer(answer))			// no breakfast
			return drinkPrice;
										// have breakfast
		ItemFood food;
		float foodPrice = 0;
		System.out.println("what choice of breakfast do you want? (sandwich/bagel): ");
		String name = scan.next();
		if (name.equalsIgnoreCase("sandwich"))
			food = new Sandwich(name);
		else if (name.equalsIgnoreCase("bagel"))
			food = new Bagel(name);
		else {
			System.out.println("# Unknown breakfast!");
			return drinkPrice;
		}  	// close if
		food.initializeInformation();				// get food infor
		foodPrice = food.calculatePrice();		// calculate breakfast price
		return (drinkPrice + foodPrice);
	}	// close calculatePrice3


	public static void main(String[] args) {
		System.out.println("Price of your coffee order is: $" + Calculator.calculatePrice3());
	}	// close main
	
}	// close Calculator