
Origin problem
===

1. Write a requirement document for student learning portal of an online university
	The main purpose of the system is to help student learns throw all 3 methods of learning
	
	1. Self experience, experiment and exploratory
	
	2. Social learning with peers
	
	3. Learn from professors, experts, etc

2. Only need to define features to support the end-users who are students
	Don’t need to define features for staffs, teachers, etc

3. May include mock-ups, drawing, etc

4. It’s ok to use screenshots of others system as examples

5. Record a short videos to walk engineers through your vision of the system


# Problem analysis
- The goal is to make an student-learning-portal of **online university** that **help student to learn** **throws 3 learning methods**

-> Priority key is to [help student learn]; then [without 3 learning methods]


See as a student
---

1. How to help student learn?

- why student learn? - To expand their edge based on cource edge of knowledge

- What matter to help student learn?

	1. Material

	2. Validation of the course edge

	3. Feedback between the course and student
		> why? - to expand student edge, it's neccessary to know what is student edge now

	4. The guide from teacher

> How to do this? - Now the second priority comes:

	-> How to help student learn without the 3 learning methods

2. How to help student learn without 3 learning methods

	The 3 methods:

		1. Self experience, experiment and exploratory

		2. Social learning with peers

		3. Learn from professors, experts, etc

	> Base on "what matter in the first problem":

	1. Material: online material (no download)

		- why? - student don't need to worry of out-of-date, too long book, or search on data ocean(internet) -> no self-exploratory

	2. Validation of the course

	3. Feedback between course and student: feedback on the material and validation + ask for guide from teacher


See as the university(relate to student)
---

- University provide knowledge base on course, a student can enroll list of courses.

- The problem: "student learning portal of an online university that help student learns throw all 3 methods of learning",
	now divided into:
	"help student learns without 3 learning methods in a course" + function to navigate among the list course

- Subproblem: "help student learns in a course without 3 learning methods".
	"help studetn learns without 3 learning methods in a lesson" +  function to navigate among lessons

- There are many student in the same course -> to remove learning social with peers -> no function to join learning with classmates.

- A student can learn many course at the same time. To help student easily know what going on with each course -> need a dashboard


> In conclusion, the requirements of student-learning-portal should satisfy all sub-problems identified.

Requirements 
===

1. All material is stored online, connect through with the portal server

2. The structure of student-learning-portal contains:
	```
	Mainpage
		Dashboad
		CourseNavigation
		ChatIcon

	CoursePage
		LessonNavigation
		ChatIcon

	LessonPage
		MaterialContainer
		ValidationContainer
		ChatIcon
	```
- Dashboard: summary what close activities that student need to fulfill.

- ChatIcon: based on the scope, the responsor maybe different

	1. In main-page: the reponsor is uni-helper

	2. In CoursePage: the responsors are teachers of the course

	3. In lesson-page: the responsors are teacher of the course

- Material-container: where student take material needs

- Validate-container: each lesson can have its own validation, or the validation can be in separate lesson



