#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Operateur d affectation", "[stack]")
{
    // Test l equivalence avec le constructeur par copie
    // Pile vide
    Stack sv;
    Stack sv2 = sv;
    Stack sv3 = Stack(sv);
    REQUIRE(sv2.size() == sv3.size());
    REQUIRE(sv2.maxsize() == sv3.maxsize());

    // Pile non vide
    Stack s;
    for(int i=1; i<3; i++){
      s.push(i);
    }
    Stack s2 = s;
    Stack s3 = Stack(s);
    REQUIRE(s2.size() == s3.size());
    REQUIRE(s2.top() == s3.top());
    REQUIRE(s2.maxsize() == s3.maxsize());
    for(int i=0; i<s2.size(); i++){
      REQUIRE(s2.s[i] == s3.s[i]);
    }
}
