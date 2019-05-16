#include "catch.hpp"
#include "myclass.hpp"
#include "set_operations.hpp"

#include <list>
#include <vector>
#include <map>

using namespace std;

TEST_CASE("intersection of integers in vectors", "[template]")
{
    // Intersection nulle
    vector<int> v1 = { 1, 2 };
    vector<int> v2 = { 3, 4 };
    vector<int> v3;
    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

     // Intersection de deux ensembles vides
    v1 = {};
    v2 = {};

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Intersection non nulle
    v1 = { 1, 2, 3, 4 };
    v2 = { 3, 4, 5, 6 };

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3[0] == 3);
}

TEST_CASE("intersection of integers in lists", "[template]")
{
    // Intersection nulle
    list<int> v1 = { 1, 2 };
    list<int> v2 = { 3, 4 };
    list<int> v3;
    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

     // Intersection de deux ensembles vides
    v1 = {};
    v2 = {};

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Intersection non nulle
    v1 = { 1, 2, 3, 4 };
    v2 = { 3, 4, 5, 6 };

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.front() == 3);
}

TEST_CASE("intersection of integers in vectors of type MyClass", "[template]")
{
    // Intersection nulle
    vector<MyClass> v1 = { MyClass(1), MyClass(2) };
    vector<MyClass> v2 = { MyClass(3), MyClass(4) };
    vector<MyClass> v3;
    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

     // Intersection de deux ensembles vides
    v1 = {};
    v2 = {};

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Intersection non nulle
    v1 = { MyClass(1), MyClass(2), MyClass(3), MyClass(4) };
    v2 = { MyClass(3), MyClass(4), MyClass(5), MyClass(6) };

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3[0] == 3);

}

TEST_CASE("intersection of integers in lists of type MyClass", "[template]")
{
    // Intersection nulle
    list<MyClass> v1 = { MyClass(1), MyClass(2) };
    list<MyClass> v2 = { MyClass(3), MyClass(4) };
    list<MyClass> v3;
    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                   back_inserter(v3));

    REQUIRE(v3.size() == 0);

     // Intersection de deux ensembles vides
    v1 = {};
    v2 = {};

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.size() == 0);

    // Intersection non nulle
    v1 = { MyClass(1), MyClass(2), MyClass(3), MyClass(4) };
    v2 = { MyClass(3), MyClass(4), MyClass(5), MyClass(6) };

    set_intersect(begin(v1), end(v1), begin(v2), end(v2),
                  back_inserter(v3));

    REQUIRE(v3.front() == 3);
}


// TEST_CASE("intersection of int and float in lists", "[template]")
// {
//
//     // Test entre float et int
//
//     list<int> l1 = { 1,2};
//     list<float> l2 = {1.5,2.5 };
//     list<float> l3;
//     set_intersect(begin(l1), end(l1), begin(l2), end(l2),
//                    back_inserter(l3));
//     REQUIRE(l3.size() == 2);
// }



// TEST_CASE("intersection of map<int, string> (question 5)", "[template]")
// {
//     typedef map<int,string> myMap;
//     myMap m1, m2, m3;
//
//     a[1] = "A"; a[2] = "B"; a[3] = "C"; a[4] = "D";
//
//
//      set_intersect(begin(m1), end(m1), begin(m2), end(m2),
//                     back_inserter(m3));
//
//     REQUIRE(m3.size() == 0);
// }

bool my_compare(const std::pair<int, std::string> &x, int y) {
  return x.first == y;
}

TEST_CASE("intersection with function of comparaison (question 6)", "[template]")
{
    map<int, string> a, b;
    vector<int> v = {1, 3, 5};

    a[1] = "A"; a[2] = "B"; a[3] = "C"; a[4] = "D";

    set_intersect_comp(begin(a), end(a), begin(v), end(v), my_compare, inserter(b, b.end()));

    REQUIRE(b.size() == 2);
    REQUIRE(b[1] == "A");
    REQUIRE(b[3] == "C");
    REQUIRE(b[2] == "");
    REQUIRE(b[4] == "");
}
