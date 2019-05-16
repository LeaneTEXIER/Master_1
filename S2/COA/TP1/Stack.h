#ifndef __STACK_H__
#define __STACK_H__

class EmptyExc {};

class Stack {
 public:
    int *s;
    int n; 
    int next;
    Stack();               // default constructor, empty stack
    Stack(const Stack &s); // copy constructor
    ~Stack();              // destructor
    
    bool isEmpty() const;  // returns true if empty
    int top() const;       // returns the element at the top
    void pop();            // removes element from the top
    void push(int elem);    // puts an element on top
    void clear();          // removes all elements
    int size() const;      // number of elements currently in the stack
    int maxsize() const;   // size of the internal representation
    void reduce();
};

void half(Stack &s);
Stack half_copy(Stack &s);

#endif
