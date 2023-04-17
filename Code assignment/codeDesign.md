
# Code design


## Data
1. Input
	- Get data from standard input file
	- Input data has multiple test cases
	- Format of ONE test case:
		> Line 0: 2 integers m and n

			> m: number of tasks: 1 to m

			> n: number of relationships

		> Line 1 -> n: 2 integer a and b, means: task b must be done after a

	- Last test case is: 0 0 (m = n = 0)

2. Output
	- For each test cases: print number of task of that test case in a posible order

3. Used data
	1. TestCase: (a structure)
		- m: number of tasks 
		- n: number of relationship
		- relation: 2 dimension array, each row contain task a and b at col 0 and col 1, respectively

	2. Tree
		> to model solution, using a tree with the below nodes relationship
			- The higher level node happens after the lower level node
		- Fields
			1. Root: a node, where parent is NULL, listDepend is NULL, level = 0
		- Behavior
			1. init(void): create a Root
			2. grow(TestCase): build tree branch due to a TestCase
			3. result(): return a solution in form of an array
			4. delete(): release tree's memory
	3. Node
		- Fields
			0. task: task it representing, 0 mean BLANK NODE
			1. parent: the pointer to its parent node
			2. level: the level number 
			3. listChildren: the list of pointer to its children
			4. listDepend: list of pointer to all nodes depends on it
		- Behaviors
			1. born(node* parent): create new node which is child of parents
			2. reborn(int step): move node and all its dependednt nodes down to [step] level
			3. findAncestor(otherNode): find common ancestor of it and otherNode

## Modelize the solution by building the tree:
- Update relation given node(a) and node(b)

	<!-- for node(a) -->
	> `Seach` node(a) in tree

		> `If` node(a) NOT exist

			> `Declare` node(a) is child of Root

	> `Search` for node(b) in tree

		> `If` node(b) NOT exist

			> `Declare` node(b) is child of node(a)
				

		> `If` node(b) is exist
			<!-- Moving node(b) if necessary -->
			> `If` node(b).level > node(a).level

				> `Do nothing`

			> `If` node(b).level <= node(a).level

				> `Find` [commonAncestor] = The earliest common ancestor of node(a) and node(b)

					> `If` [commonAncestor] is Not node(b)

						<!-- `Replace` previous position of node(b) with BLANK node -->
						> `Move` node(b) branch to be child of node(a)

						> `Assign` [step] = node(a).level  - node(b).level + 1

						<!-- using recursively -->
						> `Move` all nodes and their dependent node down [step] of level

					> `If` [commonAncestor] is node(b)

						> `Warning` Cirlular routine

						> `Return` NULL


## Code flow

1. Main
	> `Get` data from input standard

	> `Compute` a posible order of task for each test case

	> `Print` the result

	> `Exit`

2. get_data(void)
	- to read input information, and extract it into list of TestCase

	> `Declare` list of TestCase: [listTest]

	> `While` TRUE

		<!-- Read 1 test Case -->
		> `Read` 2 integer [int1] and [int2] from a line

		<!-- ternimate condition -->
		> `If` [int1] == [int2] == 0 `Or` stdin reachs EOF:

			> `Break`

		<!-- Extract data -->
		> `Declare` TestCase* testCase

		> `Assign`  testCase.m = [int1], testCase.n = [int2]

		> `Read` next [testCase.n ] lines

			> `Assign` each lines(2 integers) into testCase.relation list

		> `Add` testCase to [listTest]

3. solve(TestCase)
	- to find an posible order of work in one test Case
	- input: a test case
	- output: a array of order 
	> `take` a [testCase]

	> `Declare` a [tree]

	> `Build` [tree] from [testCase]

	> `return` solution in from of array

4. solve(list of TestCase)
	- to solve all testCase in the input

	> `Take` list of TestCase [listTest]

	> `Declare` [listSol]

	> `For each` [testCase] in [listTest]

		> `Validate` testCase: value of number of tasks, number of relations, list of relation of testCase

		> `solve` [testCase], `Add` [result] to [listSol]

		<!-- to make instance see the result -->
		> `If` [result] is NULL

			> `Warning` Circular Routine

		> `Else`

			> `Print` [result]

	> `return` [listSol]

