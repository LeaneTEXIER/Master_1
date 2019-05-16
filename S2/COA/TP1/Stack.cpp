#include "Stack.h"
/**
   Complete the member functions below
 */

Stack::Stack()
{
    n = 10;
    s = new int[n];
    next = 0;
}

Stack::Stack(const Stack &s2)
{
    n = s2.n;
    next = s2.next;
    s = new int[n];
    for (int i = 0; i<n; i++){
        s[i] = s2.s[i];
    }
}

Stack::~Stack()
{
    delete [] s;
}
    
bool Stack::isEmpty() const
{
    if (next == 0){
        return true;
    }
    else {
        return false;
    }  
}

int Stack::top() const 
{
    if (isEmpty()){
        throw EmptyExc();
    }
    else {
        return *(s+next-1);
    }
}

void Stack::pop()
{
    if (!isEmpty()){
       next--;
    }
}

void Stack::push(int elem)
{
    if (n == next){
        n+=10;
        int * tmp = new int[n];
        for (int i = 0; i<n-10; i++){
            tmp[i] = s[i];
        }
        delete [] s;
        s = tmp;
    }
    s[next] = elem;
    next++;
}

void Stack::clear()
{
    next = 0;
}

int Stack::size() const
{
    return next;
}

int Stack::maxsize() const
{
    return n;
}

void Stack::reduce() {
    if (n!=next){
        int * tmp = new int[next];
        for (int i = 0; i<next; i++){
            tmp[i] = s[i];
        }
        delete [] s;
        s = tmp;
        n = next;
    }
}

void half(Stack &s) {
    s.next = s.next/2;
}

Stack half_copy(Stack &s) {
    Stack s2(s);
    half(s2);
    return s2;
}

