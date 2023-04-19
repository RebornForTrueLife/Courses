
# Shopping manager


## Main features

1. Users are able to list daily/weekly/monthly items they want to buy
2. Check the remaining items at home
3. Users can manage the check-in and expiration date for the product they bought
4. Notify the items that nearly expired
5. Users can select the list of items. The tool will suggest the best place with the cheapest price.


## Specification

1. Platform and language
	- This application is built on both web and mobile platforms
		> Web application: provide user can access to app information on any device that have internet

		> Mobile Application: 

			1. since mobile phones are widely used nowadays

			2. help user can check their buying list even when they don't have any internet connection

	- Using JVM(Java)
		> To achieve building the application on both platforms

		> Object-oriented programming provides the ease of control and security of user's data.

2. Technical features
	- Data management
		1. Item database

			> In the highest abstract layer, the database will be a list of Item Types. For example: rose soap and orchid soap are the same item type.

			> Each Item Type will contain a list of items of that type, which are classified by unique ID.

			> Each Item will have its set of properties, such as, ID, type, product name, manufacturer, check-in date, expiration date, buy date,...

		2. User data
			> User data should contain the below information

				- User account: username, password

				- User location: for a better-suggested shopping place

	- Classes
		> Using Object-oriented approach to modelize the problem into a set of objects. There are 3 basic classes

		1. User class
			- Provide services: 

				> Control user account: store account information, login, logout, sign up, unsubscribe(delete account)

			- Contain: user's manager

		2. Manager class
			- Provide services:
				> Manage user's items: (1)access/modify Item database; (2) list out items the user wants to buy with specified date/time; (3) notify items nearly expired; (4) list remaining items at home

				> Communicate with suggester on behalf of a user: request suggester provides a list of "good" places given list of items

		3. Suggester class
			- Provide services:
				> List "good" places in order for a given item, "good" can be considered: the best place(customers rate), the cheapest prices. 

				> Suggest place for list of items. There are 2 criteria: (1) location; (2) "good" place. The location criterion is used for users who want to buy in near shops, while the second criterion gives high priority for rating.

			*Note: suggester class does not work offline, because its data should not be stored in user memory.*

3. Challenges
	1. To satisfy the user's desire
		> Why user should use the app instead of normal, usual ways: remember the buying list or just simply write down a note?

	2. Advertisement:
		> Even when the application meets the user's desire, how to make them attracted to the application enough to install it(without annoying them and using too much budget for advertisement)?

	3. User data
		> The application store the user's private information - what they bought. How we ensure the privacy of the user's data and prove to user that we can do that?

4. Project estimation
	- The project will operate in 3 phases:
		1. Idealize and build system
			- Description: this phase is for idealizing the system process, planning for advertising, and building the system ready to run

				> Refine Ideas -> system model -> coding system

				> Plan for advertising campaign

				> Estimate budget for all activities in phase 1 and phase 2

			- Estimate time: 2 months

		2. Testing phase
			- Description: this phase is for a small scale of the system: let users know the app, and receive feedback to improve the system

				> Advertise 

				> Receive user's feedback

				> Maintain and update system based on user's feed back

			- Estimate time: 6 months

		3. Fully Deploying phase
			- Description: This phase will not be deployed until receiving enough proof from feedback, the number of users that the application is useful and should be scaled.

				> Keep Advertising

				> Receive feedback

				> Scale up data storage, system

				> Scale up maintain teams

			- Estimate time: until the application no longer exists

	- Timeline:
		```
		Month 			Phase

		1 - 2			Idealize and building phase

		3 - 8 			Testing phase

		9 - ?			Deciding if "Fully deploying phase" can be deployed based on the testing phase
		```
