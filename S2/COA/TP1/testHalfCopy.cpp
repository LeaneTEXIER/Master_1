#include "catch.hpp"
#include "Stack.h"

TEST_CASE("Test the global function half_copy", "[stack]")
{
    int n, i, top, maxsize;

    // Nombre nul d'élements dans le tableau
    Stack s0;
    maxsize = s0.maxsize();
    Stack s02 = half_copy(s0);
    // Pas de changement sur la stack initiale
    REQUIRE(s0.size() == 0);
    REQUIRE(s0.maxsize() == maxsize);
    // Changement sur la stack copiée
    REQUIRE(s02.size() == 0);

    // Nombre pair d'élements dans le tableau
    Stack s;
    n = 10;
    for (i=0; i<n; i++){
        s.push(i);
    }
    top = s.top();
    maxsize = s.maxsize();
    Stack s2 = half_copy(s);
    // Pas de changement sur la stack initiale
    REQUIRE(s.size() == n);
    REQUIRE(s.top() == top);
    REQUIRE(s.maxsize() == maxsize);
    // Changement sur la stack copiée
    REQUIRE(s2.size() == 5);
    REQUIRE(s2.top() != top);

    // Nombre impair d'élements dans le tableau
    Stack si;
    n = 9;
    for (i=0; i<n; i++){
        si.push(i);
    }
    top = si.top();
    maxsize = si.maxsize();
    Stack si2 = half_copy(si);
    // Pas de changement sur la stack initiale
    REQUIRE(si.size() == n);
    REQUIRE(si.top() == top);
    REQUIRE(si.maxsize() == maxsize);
    // Changement sur la stack copiée
    REQUIRE(si2.size() == 4);
    REQUIRE(si2.top() != top);
}
