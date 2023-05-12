
# Outline of Pricing problem


## Approach

- Using object-oriented: 
	(1) easier to model the problem data
	(2) easier to scale accorrding to the use case


## Design choices

1. Build the most common class: which provide price calculation operation, which is Item class

2. From requirements, the leaf classes(coffee, milk tea, sandwich, bagel) are detailed
	- However, the middle classes(ItemDrink, ItemFood), it's take time(modification...) to modelize them to make them reuse for leaf classes


## Implementation

*Detail classes structure is represented in UML diagrams*

- All calculatePrice[#] take no argument, there are prompts from program to guide user
	
	> all functions return a float, which is price, except calculatePrice5, which returns list of price

- all information is entered by standard input(System.in)


## Lesson learned
- I don't learn any lesson yet, but I notice some issues when I try to solve the requirements:
	
	1. It's hard for me to design exactly or even closed what should included into middle classes(ItemDrink, ItemFood), only when I build leaf classes, I realized what I needs, then I added into middle classes. So what approach can I go so that it's more efficient for saving time?


