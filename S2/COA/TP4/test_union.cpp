#include "catch.hpp"
#include "myclass.hpp"
#include "set_operations.hpp"

#include <list>
#include <map>

using namespace std;

TEST_CASE("union of integers in vectors", "[template]")
{
    // Union de deux ensembles vides
    vector<int> v1 = {};
    vector<int> v2 = {};
    vector<int> v3;
    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Union de deux ensembles non vides
    v1 = { 1, 2, 3, 4 };
    v2 = { 3, 4, 5, 6 };

    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3[0] == 1);
    REQUIRE(v3.size() == 6);
}

TEST_CASE("union of integers in lists", "[template]")
{
    // Union de deux ensembles vides
    list<int> v1 = {};
    list<int> v2 = {};
    list<int> v3;
    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Union de deux ensembles non vides
    v1 = { 1, 2, 3, 4 };
    v2 = { 3, 4, 5, 6 };

    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.front() == 1);
    REQUIRE(v3.size() == 6);
}

TEST_CASE("union of integers in vectors of type MyClass", "[template]")
{
    // Union de deux ensembles vides
    vector<MyClass> v1 = {};
    vector<MyClass> v2 = {};
    vector<MyClass> v3;
    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Union de deux ensembles non vides
    v1 = { MyClass(1), MyClass(2), MyClass(3), MyClass(4) };
    v2 = { MyClass(3), MyClass(4), MyClass(5), MyClass(6) };

    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3[0] == 1);
    REQUIRE(v3.size() == 6);
}

TEST_CASE("union of integers in lists of type MyClass", "[template]")
{
    // Union de deux ensembles vides
    list<MyClass> v1 = {};
    list<MyClass> v2 = {};
    list<MyClass> v3;
    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Union de deux ensembles non vides
    v1 = { MyClass(1), MyClass(2), MyClass(3), MyClass(4) };
    v2 = { MyClass(3), MyClass(4), MyClass(5), MyClass(6) };

    set_union(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.front() == 1);
    REQUIRE(v3.size() == 6);
}

// TEST_CASE("union of map<int, string> (question 5)", "[template]")
// {
//     typedef map<int,string> myMap;
//     myMap m1, m2, m3;
//
//     a[1] = "A"; a[2] = "B"; a[3] = "C"; a[4] = "D";
//
//
//      set_union(begin(m1), end(m1), begin(m2), end(m2),
//                     back_inserter(m3));
//
//     REQUIRE(m3.size() == 4);
// }
