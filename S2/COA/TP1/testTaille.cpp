#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Test the size of the stack when we push elements", "[stack]")
{
    Stack s;
    int n = s.maxsize();
    for (int i=0; i<n; i++){
        s.push(i);
    }
    REQUIRE(s.size() == s.maxsize());
    s.push(1);
    REQUIRE(s.size() == n+1);
    REQUIRE(s.maxsize() > n);
}

