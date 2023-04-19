
# Shopping manager


## Main features

1. Users are able to list daily/weekly/monthly items they want to buy
2. Check the remaining items at home
3. Users can manage the check-in and expiration date for the product they bought
4. Notify the items nearly exprired
5. Users can select the list of items. The tool will suggest the best place with the cheapest price.


## Specification

1. Platform and language
	- This application is built on both web and mobile application
		> Web application: provide user can access the app information on any devices that have internet

		> Mobile Application: 
			1. since mobile phones are widely used nowaday

			2. help user can check their buying list even when they don't have internet connection

	- Using JVM(Java)
		> To achieve building the application in both platform

		> Object-oriented programming provide the ease of control and secure of user's data.

2. Technical features
	- Data management
		1. Item database

			> In the highest abtract layer, database will be a list of Item Type. For example: rose soap and orchid soap are the same item type.

			> Each Item Type will contain a list of items of that type, which are classified by unique ID.

			> Each Item will have its set of properties, such as: ID, type, product name, manufacturer, check-in date, expiration date, buy date,...

		2. User data
			> User data should contain below information
				- User account: username, password
				- User location: for better suggesting shopping place
	- Classes
		> Using Object-oriented approach to modelize the problem into set of objects. There are 3 basic classes

		1. User class
			- Provide services: 

				> Control user account: store account information, login, logout, sign up, unsubscribe(delete account)

			- Contain: user's manager

		2. Manager class
			- Provide services:
				> Manage user's items: (1)access/modify Item database; (2) list out items user want to buy with specified date/time; (3) notify items nearly exprired; (4) list remaining items at home

				> Communicate with suggester behalf of user: request suggester provide list of "good" places given list of items

		3. Suggester class
			- Provide services:
				> List "good" places in order for a given items, "good" can be consider: best place(customers rate), cheapest prices. 

				> Suggest place for list of items. There are 2 criteria: (1) location; (2) "good" place. Location cretirion is used for user want to buy in near shops, while (2)nd criterion give high priority for rating.

			*Note: suggester class does not work offline, because its data should not be stored in user memory.*

3. Challenges
	1. To satisfy user's desire
		> why user should use the app instead of normal, usual ways: remember the buying list or just simply write down to a note?

	2. Advertisement:
		> Even when the application meets user's desire, how to make them attract to the application enough to install it(without annoy them and using too much budget for advertisement)?

	3. User data
		> The application store user's private information - what they bought. How we ensure the privacy of user's  data and how we prove to user that we can do that?

4. Project estimation
	- Project will operate in 3 phases:
		1. Idealize and build system
			- Description: this phase is for idealize the system process, plan for advertise, and build system ready to run

				> Refine Ideas -> system model -> coding system

				> Plan for advertisement campaign

				> Estimate budget for all activities in phase 1 and phase 2

			- Estimate time: 2 months

		2. Testing phase
			- Description: this phase is for small operating system: let user know the app, and receive feed back to improve system

				> Advertise 

				> Receive user's feedback

				> Maintain and update system base on user's feed back

			- Estimate time: 6 months

		3. Fully Deploying phase
			- Description: This phase will not be deployed until system of phase 2 is not sufficient for the increase of user number.

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

		9 - ?			Deciding if "Fully deploying phase" can be deployed base on testing phase
		```
