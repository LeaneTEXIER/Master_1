#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Reduce stack's size", "[stack]")
{
    int top;
    // Pile vide
    Stack s;
    s.reduce();
    REQUIRE(s.size() == s.maxsize());
    REQUIRE(s.size() == 0);

    // Pile non pleine (car par défaut le tableau initial peut contenir jusqu'à 10 éléments)
    Stack s1;
    s1.push(1);
    top = s1.top();
    s1.reduce();
    REQUIRE(s1.size() == s1.maxsize());
    REQUIRE(s1.size() == 1);
    REQUIRE(s1.top() == top);
    
    // Pile pleine
    Stack s2;
    for(int i=0; i<s2.maxsize(); i++){
        s2.push(i);
    }
    top = s2.top();
    int size = s2.size();
    REQUIRE(s2.size() == s2.maxsize());
    s2.reduce();
    REQUIRE(s2.size() == s2.maxsize());
    REQUIRE(s2.size() == size);
    REQUIRE(s2.top() == top);
}
