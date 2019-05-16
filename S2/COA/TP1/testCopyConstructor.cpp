#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Create a constructor and copy it", "[stack]")
{
    // Copie d'une pile vide
    Stack s0;
    Stack s02 = Stack(s0);
    REQUIRE(s0.size() == s02.size());
    REQUIRE(s0.maxsize() == s02.maxsize());
    s0.push(2);
    REQUIRE(s0.size() == s02.size()+1);
    s0.pop();
    s02.push(2);
    REQUIRE(s0.size()+1 == s02.size());

    // Copie d'une pile non vide
    Stack s;
    s.push(1);
    Stack s2 = Stack(s);
    REQUIRE(s.size() == s2.size());
    REQUIRE(s.top() == s2.top());
    REQUIRE(s.maxsize() == s2.maxsize());
    s.push(2);
    REQUIRE(s.size() == s2.size()+1);
    REQUIRE(s.top() != s2.top());
    s.pop();
    s2.push(2);
    REQUIRE(s.size()+1 == s2.size());
    REQUIRE(s.top() != s2.top());
}

