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

Stack& Stack::operator=(const Stack &other) {
    next = other.next;
    n = other.n;
    s = new int[next];
    for (int i = 0; i<next; i++){
      s[i] = other.s[i];
    }
    return *this;
}

bool Stack::operator==(const Stack &other) const {
    if((next!=other.next)){
      return false;
    }
    for (int i = 0; i<next; i++){
      if(s[i] != other.s[i]){
        return false;
      }
    }
    return true;
}

Stack & Stack::operator+=(const Stack &other){
    Stack s_other(other);
    while (!s_other.isEmpty()) {
        this->push(s_other.top());
        s_other.pop();
    }
    return *this;
}

Stack operator+(const Stack &s1, const Stack &s2){
    Stack res(s1);
    res += s2;
    return res;
}

std::ostream& operator<<(std::ostream &os, const Stack &s) {
    try {
        s.top();
        int i;
        os << "{ ";
        for(i=0; i<s.next-1; i++){
        os << s.s[i] << ", ";
        }
        os << s.s[i] << " }";
     } catch (EmptyExc e) {
         os << "{ Pile vide }";
      }
    return os;
}

Stack& Stack::operator+=(int elem){
  if(isEmpty() || elem<top()){
    push(elem);
  } else{
      throw IncorrectPush();
  }
  return *this;
}
