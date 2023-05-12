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
		return price;
	}	// close calculatePrice1


	/* My mistake, I modified directly into classes so the new feature is also added into calculatePrice2 */
	public static float calculatePrice2() {
		return calculatePrice3();
	}	// calcualtePrice3


	/* Calcualte price of given drink: coffee or milk */
	public static float calculatePrice3() {
		ItemDrink drink;
		// ask for drink kind
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
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
		return price;
	}	// close calculatePrice3


	public static float calculatePrice4() {
										// drink options
		float drinkPrice = calculatePrice2();
										// breakfast options
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Do you want to have breakfast? (yes/no): ");
		String answer = scan.next();
		if (!Item.getAnswer(answer))			// no breakfast
			return drinkPrice;
										// have breakfast
		ItemFood food;
		float foodPrice = 0;
		System.out.print("what choice of breakfast do you want? (sandwich/bagel): ");
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
	}	// close calculatePrice4


	/* 
		- calculate list of items 
		- return list of prices, total price is the last element
	 */
	public static List<Float> calculatePrice5() {
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		List<Item> listItem = new ArrayList<Item>();
		List<Float> listPrice = new ArrayList<Float>();
		// interface
		while (true) {
			System.out.println("Enter your item: (coffee / milk tea / sandwich / bagel): ");
			String name = scan.next();
			String choice = getChoice(name);
			switch (choice) {
														// coffee
			case "coffee":
				Coffee coff = new Coffee(name);
				coff.initializeInformation();
				listItem.add(coff);
				break;
														// milk
			case "milk":
				MilkTea milk = new MilkTea(name);
				milk.initializeInformation();
				listItem.add(milk);
				break;
														// Sandwich
			case "sandwich":
				Sandwich sand = new Sandwich(name);
				sand.initializeInformation();
				listItem.add(sand);
				break;
														// bagel
			case "bagel":
				Bagel bage = new Bagel(name);
				bage.initializeInformation();
				listItem.add(bage);
				break;
			default:
				System.out.println("# Unkown choice!");
				break;
			}	// close switch
			// continue?
			System.out.print("Do you want to add more item?(yes/no): ");
			boolean isContinue = Item.getAnswer(scan.next());
			if (!isContinue)
				break;
		}	// close while
		// calculate price
		float totalPrice = 0;
		for (Item item: listItem) {
			float price = item.calculatePrice();
			totalPrice += price;
			listPrice.add(price);
			System.out.println("Price of " + item.name + " is: $" + price);
		}	// close for
		// Add tax
		totalPrice *= 1.0725;
		listPrice.add(totalPrice);
		return listPrice;
	}	// close calculatePrice5


	// convert choice into standard choice
	private static String getChoice(String choice) {
		choice = choice.toLowerCase().trim();
		String keywords[] = {"coffee", "milk", "sandwich", "bagel"};
		for (String word: keywords) {
			if (choice.contains(word))
				return word;
		}	// close for
		return "unknown";		// no word matched
	}	// close getChoice


	public static void main(String[] args) {
		List<Float> listPrice = calculatePrice5();
		float totalPrice = listPrice.get(listPrice.size() - 1);
		System.out.println("Price(taxed) of your order is: $" + totalPrice);
	}	// close main
	
}	// close Calculator