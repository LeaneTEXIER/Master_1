#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Operateur global addition", "[stack]")
{
    int i;
    Stack s, sm;
    Stack s2, sm2;

    for(i=1; i<3; i++){
      s.push(i);
      sm.push(i);
    }
    REQUIRE(s == sm);
    for(i=3; i<5; i++){
      s2.push(i);
      sm2.push(i);
    }
    REQUIRE(s2 == sm2);

    // Test si a=a+b est equivalent a a+=b
    s = s + s2;
    sm+= sm2;

    REQUIRE(s == sm);
}
