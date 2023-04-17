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
typedef struct _ListTask ListTask;

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
	ListTask *listDepend;	// list of dependent TASK that are not the children of this node
	// Operators
	// born(Node*, int): initialize a node with specific parent, and task number
	// reborn(int step): move node, its children, its dependent nodes down to given step
	// findAncestor(Node*): find the earliest common ancestor of it and the given node
};

// create node
Node *born(Node *parent, int task);

// reborn
bool reborn(Tree *tree, Node* self, int step, bool moveFlag);

// find common ancestor
Node* findAncestor(Node *self, Node *other);


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
	Node *root;			// root node
	Node **access;		// array contain address of all nodes, index represent task number
	// array contain state of each nodes in access array
		// this array support moving nodes
		// the state can be 0: movable; 1: unmovable
	int *state;		
	// Operator
	// init(): initialize Root
	// grow(TestCase): build tree from a TestCase, return true if successfully grow
	// result(): return a solution in form of linked list
	// destroy(): free tree from memory
};

// init
void init(Tree *tree);

// grow tree
bool grow(Tree *tree, TestCase *testCase);

// result
ListTask *result(Tree *tree, TestCase *testCase);

// destroy tree
void destroy(Tree *tree);


/* Linked list ListTask */
struct _ListTask {
	int task;
	ListTask *next;
};

// add task into list
void addListTask(ListTask *self, int task);


/* Linked list for Node */
struct _ListNode {
	Node *node;	// current node
	ListNode *next;		// next ListNode
	// Operator
	// addListNode(Node *): add new node to list
};

// add new node to list node
void addListNode(ListNode* self, Node *node);


// FUNC DECLARE

/*	Func to read input data and return list of TestCase */
ListTestCase *get_data(void);

/*	func to solve one test case 
	* NOTE: the function does not validate the input testCase */
ListTask *solve(TestCase *testCase);


// // // // MAIN
int main(int argc, char const *argv[]) {
	// Read data
		// when coding
	#ifdef CODING
		std::freopen("data.in", "r", stdin);
	#endif	// CODING
	ListTestCase *listTest = get_data();
	if (listTest == NULL)
		exit(-1);
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
	test = listTest->head->next;
	Tree *tree = new Tree;
	init(tree);
	bool isGrow = grow(tree, test);
	if (isGrow)
		std::cout << "Grew!\n";
	else
		std::cout << "False\n";
	for (int i = 0; i <= test->numTask; i ++ ) {
		if (tree->access[i] != NULL) {
			std::cout << "Task: " << tree->access[i]->task << "; ";
			if (tree->access[i]->parent != NULL)
				std::cout << "parent: " << tree->access[i]->parent->task << "; ";
			std::cout << "Level: " << tree->access[i]->level << "\n";
		}	// close if
	}	// close for
	// solve one test
	ListTask *sol = solve(test);
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
ListTask *solve(TestCase *testCase) {
	Tree *tree = new Tree;
	init(tree);
	bool isGrow = grow(tree, testCase);
	if (isGrow == false)		// no solution for this algo
		return NULL;
	ListTask *sol = result(tree, testCase);
	// print sol
	while (sol != NULL) {
		std::cout << sol->task << " ";
		sol = sol->next;
	}	// close while
	std::cout << "\n";
	// destroy(tree);
	return sol;
}	// close solve 1 test case


// TREE CLASS IMPLEMENT

// init
void init(Tree *tree) {
	tree->root = born(NULL, -1);
}	// close init


// grow tree
bool grow(Tree *tree, TestCase *testCase) {
	Node *root = tree->root;
	tree->access = new Node* [(testCase->numTask) + 1];
	(tree->access)[0] = NULL;
	tree->state = new int [(testCase->numTask) + 1];
	tree->state[0] = 0;		// This state is for BLANK node, BLANK node is movable
	// Update tree for each pair task relationship
	for (int i = 0; i < testCase->numRelation; i ++ ) {
		// change state of all node to movable
		for (int i = 0; i <= testCase->numTask; i ++ ) 
			tree->state[i] = 0;	
		int taskA = testCase->relation[i][0];
		int taskB = testCase->relation[i][1];	// task B depends on A
		// Search node of task A
		Node *nodeA = tree->access[taskA];
		if (nodeA == NULL) {
			// create new node from Root
			nodeA = born(root, taskA);
			// add nodeA to access array
			tree->access[taskA] = nodeA;
		}	// close if
		tree->state[taskA] = 1;		// unmovable
		// Search for node B
		Node *nodeB = tree->access[taskB];
		if (nodeB == NULL) {
			// create new node, child of nodeA
			nodeB = born(nodeA, taskB);
			// add nodeB to access array
			tree->access[taskB] = nodeB;
			// Go to next relation
			continue;
		}	 // close if
		tree->state[taskB] = 0;		// movable
		// add taskB into listDepend of nodeA when nodeB is not a child of nodeA
		addListTask(nodeA->listDepend, taskB);
		// When nodeA, nodeB already existed
		// Need to moving nodeB if necessary
		if (nodeB->level > nodeA->level)
			continue;	// satisfy -> move to next relation
		// When nodeB.level <= nodeA.level
		Node *ancestor = findAncestor(nodeB, nodeA);
		// if ancestor is nodeB, then there is a circular routine
		if (ancestor == nodeB) 
			return false;	// which mean no solution by this algorithms
		// if ancestor is NOt nodeB
		int step = nodeA->level - nodeB->level + 1;		// step to move node B and its dependent nodes
		// move node B and its dependent nodes down to [step] of node
		bool rebornDone = reborn(tree, nodeB, step, true);
		if (rebornDone == false) 
			return false; 	// there is a circular routine
	}	// close for
	return true;		// successfully grow
}	// close grow


// result
ListTask *result(Tree *tree, TestCase *testCase) {
	ListTask *sol = new ListTask;
	int numTask = testCase->numTask;
	// sort the access array in accending order of level
	// selection sort
	for (int i = 1; i < numTask; i ++ ) { 
		for (int j = i + 1; j <= numTask; j ++ ) {
			if (tree->access[i]->level > tree->access[j]->level) {
				Node *node = tree->access[i];
				tree->access[i] = tree->access[j];
				tree->access[j] = node;
			}	// close if
		}	// close for 2
	}	// close for
	if (numTask >= 1) {
		sol->task = tree->access[1]->task;
		sol->next = NULL;
	}	// close if
	ListTask *current = sol;
	for (int i = 2; i <= numTask; i ++ ) {
		ListTask *next = new ListTask;
		next->task = tree->access[i]->task;
		next->next = NULL;
		current->next = next;
		current = next;
	}	// close for
	return sol;
}	// close result


// destroy tree
void destroy(Tree *tree) {

}	// close destroy


// NODE CLASS IMPLEMENT

// create node
Node *born(Node *parent, int task) {
	Node *node = new Node;
	node->task = task;
	node->parent = parent;
	if (parent != NULL) {
		node->level = (parent->level) + 1;
		addListNode(parent->listChildren, node);
	} else
		node->level = 0;
		// close if
	node->listChildren = new ListNode;
	node->listDepend = new ListTask;
	node->listDepend->task = 0;		// listDepend is empty
	return node;
}	// close born


// find common ancestor
Node* findAncestor(Node *self, Node *other) {
	Node *ancestor;
	// assign self to be the lower level node between self and other
	if (self->level > other->level) {
		ancestor = self;	
		self = other;
		other = ancestor;
	}	// close if
	ancestor = other;
	// move ancestor from other to the node having same level with self
	while (true) {
		if (ancestor->level == self->level)
			break;
		ancestor = ancestor->parent;
	}	// close while
	// move both ancestor and self until getting to common node
	while (true) {
		if (ancestor == self)
			break;
		ancestor = ancestor->parent;
		self = self->parent;
	}	// close while
	// Note: alway find the common node(at least: Root)
	return ancestor;
}	// close findAncestor


// reborn
bool reborn(Tree *tree, Node* self, int step, bool moveFlag) {
	// check if this node is movable
	if (tree->state[self->task] == 1)
		// circulate taks
		return false;
	else {
		if (self->task != 0)		// let BLANK node movable
			tree->state[self->task] = 1;		// mark it to unmovable
	}	// close if
	// update self 
		// make newSelf = copy(self) only when moveFlag is set
	if (moveFlag) {
		Node *newSelf = new Node;
		newSelf->task = self->task;
		newSelf->level = self->level;
		newSelf->listChildren = self->listChildren;
		newSelf->listDepend = self->listDepend;
			// update tree.access list
		tree->access[self->task] = newSelf;
			// turn self into BLANK node
		self->task = 0;
		self->listChildren = new ListNode;
		self->listDepend = new ListTask;
			// from self, make branch grow (step - 1 ) level
		for (int i = 0; i < (step - 1); i ++ ) {
			self = born(self, 0);
		}	// close for
			// from lastNewNode, add newSelf to be its child
		addListNode(self->listChildren, newSelf);
			// change parent of newSelf to be lastNewNode
		newSelf->parent = self;
		self = newSelf;		
	} // close if
	// update level
	self->level = self->level + step;
	// update list children - recursive

	ListNode *listChildren = self->listChildren;
	while (true) {
		// terminate when reach the end of listChidren
		if (listChildren == NULL)
			break;
		if (listChildren->node == NULL)
			break;
		bool moveChild = reborn(tree, listChildren->node, step, false);
		if (moveChild == false)
			return false;
		listChildren = listChildren->next;
	}	// close while
	// update list depend - recursive
	ListTask *listDepend = self->listDepend;
	while (true) {
		// terminate when reach the end of listDepend
		if (listDepend == NULL)
			break;
		if (listDepend->task == 0)
			break;
		// compare level of dependent task and current node
		if (tree->access[listDepend->task]->level <= self->level) {
			// only move dependent task when its level lower or equal current node level
			bool moveDepend = reborn(tree, tree->access[listDepend->task], step, true);
			if (moveDepend == false)
				return  false;
		}	// close if
		listDepend = listDepend->next;
	}	// close while
	return true;
}	// close reborn


// LISTNODE CLASS IMPLEMENT

// add new node to list node
void addListNode(ListNode* self, Node *node) {
	// if list is empty
	if (self->node == NULL) {
		self->node = node;
		self->next = NULL;
	} else {
		ListNode *newListNode = new ListNode;
		newListNode->node = node;
		newListNode->next = NULL;
		// travel to the end of list
		while (self->next != NULL)
			self = self->next;
		self->next = newListNode;
	}	// close if
}	// close addListNode


// LISTTASK CLASS IMPLEMENT

// add task into list
void addListTask(ListTask *self, int task) {
	if (self->task == 0) {
		self->task = task;
		self->next = NULL;
	} else {
		// travel to end node
		while (self->next != NULL)
			self = self->next;
			// close while
		self->next = new ListTask;
		self->next->task = task;
		self->next->next = NULL;
	}	// close if
}	// close addListTask