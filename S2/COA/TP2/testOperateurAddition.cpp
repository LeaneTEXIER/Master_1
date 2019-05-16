#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Operateur d addition", "[stack]")
{
    int i;
    Stack s, sm;
    Stack s2;
    for(i=1; i<3; i++){
      s.push(i);
      sm.push(i);
    }
    REQUIRE(s == sm);
    for(i=3; i<5; i++){
      s2.push(i);
    }

    s += s2;
    Stack s_copy(s2);
    while (!s_copy.isEmpty()) {
      sm.push(s_copy.top());
      s_copy.pop();
    }

    REQUIRE(s == sm);
}
