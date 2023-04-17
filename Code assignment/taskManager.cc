/********************************************
 * Purpose:
 *  - The program is used to help a person to arrange his
 * list of tasks
 * Uage: 
	- Run the object file
	- Enter the list of test case in format(specified in section data in 
	file codeDesign.md)
	- The program will print out the an posible order of 
	tasks for each test case 
 ********************************************/

#include <cstdio>
#include <iostream>
#include <string>


// MODE
#define CODING		
// #undef CODING


// DATA STRUCTUREs
typedef struct _TestCase TestCase;
typedef struct _ListTestCase ListTestCase;
typedef struct _Node Node;
typedef struct _ListNode ListNode;
typedef struct _Tree Tree;
typedef struct _Soluton Solution;

/* TestCase */
struct _TestCase {
	int numTask; 		// number of tasks
	int numRelation; 	// number of pair tasks relations
	int **relation;		// list of relations, second task depends on first task
	struct _TestCase *next;		// pointer to next testcase(Just used in list)
	// Operators
	// validate(void): validate this testCase
};

/* Linked List TestCase */
struct _ListTestCase{
	TestCase *head;		// current TestCase
	int size;				// number of TestCase
};

/* Node: this is node of Tree */
struct _Node{
	int task;						// task representing, 0 Mean BLANK node
	struct _Node *parent;		// pointer to parent node
	int level;					// number of node needed to travel to Root from this node
	ListNode *listChildren;	// list of pointer to chidren of this node
	ListNode *listDepend;	// list of dependent node that are not the children of this node
	// Operators
	// born(Node*): initialize a node with specific parent
	// reborn(int step): move node, its children, its dependent nodes down to given step
	// findAncestor(Node*): find the earliest common ancestor of it and the given node
};


/* Tree: model of a posible solution 
	- Tree contain a graph of nodes, where a node represent
	1 task.
		- A node have its level, which is the number of nodes needed to travel
		to Root(so Root's level is 0). Root does not represent any task
		- Relation between nodes: 
			(1) a node can only be child of 1 parent node 
			(2) a node can have many chidren
	- Priority assumption:
		> The task (represented by higher level node) happens
		after the other task (represented by lower level node)
 */
struct _Tree {
	Node *root;
	// Operator
	// init(): initialize Root
	// grow(TestCase): build tree from a TestCase
	// result(): return a solution in form of linked list
	// destroy(): free tree from memory
};

// init
void init(Tree *tree);

// grow tree
void grow(Tree *tree, TestCase *testCase);

// result
Solution *result(Tree *tree);

// destroy tree
void destroy(Tree *tree);


/* Linked list solution */
struct _Soluton {
	int task;
	int *next;
};

/* Linked list for Node */
struct _ListNode {
	Node *node;	// current node
	Node *next;		// next node
};


// FUNC DECLARE

/*	Func to read input data and return list of TestCase */
ListTestCase *get_data(void);

/*	func to solve one test case 
	* NOTE: the function does not validate the input testCase */
Solution *solve(TestCase *testCase);


// // // // MAIN
int main(int argc, char const *argv[]) {
	// Read data
		// when coding
	#ifdef CODING
		std::freopen("data.in", "r", stdin);
	#endif	// CODING
	ListTestCase *listTest = get_data();
	TestCase *test = listTest->head;
	while (test != NULL) {
		std::cout << "m = " << test->numTask << " n = " << test->numRelation << "\n";
		if (test->relation != NULL)		// not head
			for (int i = 0; i < test->numRelation; i ++ ) {
				std::cout << test->relation[i][0] << " " << test->relation[i][1] << "\n";
			}	// close for
		test = test->next;
	}	// close while
	std::cout << "size = " << listTest->size << "\n";	
	// Solve problem
	Tree tree;
	init(&tree);

	return 0;
}	// end  main 


// FUNC DEFINE

// read data
ListTestCase *get_data(void) {
	// declare list TestCase
	static ListTestCase *listTest = new ListTestCase;
	listTest->size = 0;
	listTest->head = new TestCase;
	TestCase *currentTest = listTest->head;
	currentTest->next = NULL;
	// Read each TestCase
	while (true) {
		// check if stdin is not EOF
		if (!std::cin.good()) {
			std::cerr << "Seem that there is no end test case: 0 0!\n";
			return NULL;
		}	// close if
		// read m and n
		int int1, int2;
		std::cin >> int1 >> int2;
		// check if this is end case
		if ((int1 == 0) && (int2 == 0))
			break;
		// Extract TestCase
		TestCase *testCase = new TestCase;
		testCase->numTask = int1;
		testCase->numRelation = int2;
		testCase->relation = new int* [int2];
		for (int i = 0; i < int2; i ++ ) {
			testCase->relation[i] = new int [2];
			std::cin >> testCase->relation[i][0] >> testCase->relation[i][1];
		}	// close for
		// Add testCase to list
		currentTest->next = testCase;
		currentTest = testCase;
		currentTest->next = NULL;
		listTest->size += 1;
	}	// close while
	return listTest;
}	// close get_data


// solve 1 TestCase
Solution *solve(TestCase *testCase) {
	Tree *tree = new Tree;
	init(tree);
	grow(tree, testCase);
	static Solution *sol = result(tree);
	destroy(tree);
	return sol;
}	// close solve 1 test case


// TREE CLASS IMPLEMENT

// init
void init(Tree *tree) {
	tree->root = new Node;
	tree->root->parent = NULL;
	tree->root->level = 0;
	tree->root->listChildren = new ListNode;
	tree->root->listDepend = new ListNode;
}	// close init


// grow tree
void grow(Tree *tree, TestCase *testCase) {

}	// close grow


// result
Solution *result(Tree *tree) {
	static Solution *sol = new Solution;
	return sol;
}	// close result


// destroy tree
void destroy(Tree *tree) {

}	// close destroy