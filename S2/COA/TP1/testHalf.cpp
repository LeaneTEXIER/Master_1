#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Test the global function half", "[stack]")
{
    int n, i, top;

    // Nombre nul d'élements dans le tableau
    Stack s0;
    half(s0);
    REQUIRE(s0.size() == 0);

    // Nombre pair d'élements dans le tableau
    Stack s;
    n = 10;
    for (i=0; i<n; i++){
        s.push(i);
    }
    top = s.top();
    half(s);
    REQUIRE(s.size() == 5);
    REQUIRE(s.top() != top);

    // Nombre impair d'élements dans le tableau
    Stack si;
    n = 9;
    for (i=0; i<n; i++){
        si.push(i);
    }
    top = si.top();
    half(si);
    REQUIRE(si.size() == 4);
    REQUIRE(si.top() != top);
}
