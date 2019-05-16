#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Operateur de comparaison", "[stack]")
{
    // Pile vide
    Stack sv;
    Stack sv2 = sv;
    REQUIRE (sv == sv2);

    // Pile non vide
    Stack s;
    s.push(1);
    s.push(2);
    Stack s2 = s;
    REQUIRE(s == s2);
    s.pop();
    s2.pop();
    REQUIRE(s == s2);    
}
