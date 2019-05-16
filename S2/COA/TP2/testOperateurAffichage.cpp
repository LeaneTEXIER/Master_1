#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Operateur affichage", "[stack]")
{
     Stack s;

     std::stringstream s1, s2;

     // Pour une pile vide
     s1 << s;
     s2 << "{ Pile vide }";
     REQUIRE (s1.str() == s2.str());

     // Pour une pile contenant des elements
     s.push(1);
     s.push(2);

     s1 << s;
     s2 << "{ 1, 2 }";
     REQUIRE (s1.str() == s2.str());

}
