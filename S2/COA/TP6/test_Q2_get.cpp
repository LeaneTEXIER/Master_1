#include "catch.hpp"
#include "graphTemplate.hpp"
#include <string>
#include "myclass.hpp"

using namespace std;

TEST_CASE("get node template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_node(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    try{
      myGraph.get_node(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get d'un noeud
    REQUIRE(myGraph.get_node(0) == 1);
    REQUIRE(myGraph.get_node(1) == 2);
}

TEST_CASE("get node template String", "[template]")
{
    Graph<string> myGraph;

    // Get impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_node(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    try{
      myGraph.get_node(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get d'un noeud
    REQUIRE(myGraph.get_node(0) == "my_first_node");
    REQUIRE(myGraph.get_node(1) == "my_second_node");
}


TEST_CASE("get edge template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get impossible d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_edge(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    try{
      myGraph.get_edge(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get d'une arete
    REQUIRE(myGraph.get_edge(0) == 10);
    REQUIRE(myGraph.get_edge(1) == 11);
}

TEST_CASE("get edge template String", "[template]")
{
    Graph<string> myGraph;

    // Get impossible d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_edge(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    try{
      myGraph.get_edge(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get d'une arete
    REQUIRE(myGraph.get_edge(0) == "my_first_edge");
    REQUIRE(myGraph.get_edge(1) == "my_second_edge");
}


TEST_CASE("get source template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get impossible de la source d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_source(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible de la source d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    try{
      myGraph.get_source(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get de la source d'une arete
    REQUIRE(myGraph.get_source(0) == 0);
    REQUIRE(myGraph.get_source(1) == 1);
}

TEST_CASE("get source template String", "[template]")
{
    Graph<string> myGraph;

    // Get impossible de la source d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_source(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible de la source d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    try{
      myGraph.get_source(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get de la source d'une arete
    REQUIRE(myGraph.get_source(0) == 0);
    REQUIRE(myGraph.get_source(1) == 1);
}


TEST_CASE("get dest template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get impossible de la dest d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_dest(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible de la dest d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    try{
      myGraph.get_dest(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get de la dest d'une arete
    REQUIRE(myGraph.get_dest(0) == 1);
    REQUIRE(myGraph.get_dest(1) == 0);
}

TEST_CASE("get dest template String", "[template]")
{
    Graph<string> myGraph;

    // Get impossible de la dest d'une arete car elle n'existe pas (graph n'a pas d'arete)
    try{
      myGraph.get_dest(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get impossible de la dest d'une arete car elle n'existe pas (graph a des aretes)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    try{
      myGraph.get_dest(2);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get de la dest d'une arete
    REQUIRE(myGraph.get_dest(0) == 1);
    REQUIRE(myGraph.get_dest(1) == 0);
}


TEST_CASE("get successors template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get successors impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_successors(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get successors impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_node(MyClass(3));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    myGraph.add_edge(MyClass(12), 1, 2);
    try{
      myGraph.get_successors(3);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get successors
    vector<int> v;
    v = myGraph.get_successors(0);
    REQUIRE(v.size() == 1);
    REQUIRE(v[0] == 1);

    v = myGraph.get_successors(1);
    REQUIRE(v.size() == 2);
    REQUIRE(v[0] == 0);
    REQUIRE(v[1] == 2);

    v = myGraph.get_successors(2);
    REQUIRE(v.size() == 0);
}

TEST_CASE("get successors template String", "[template]")
{
    Graph<string> myGraph;

    // Get successors impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_successors(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get successors impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_node("my_third_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    myGraph.add_edge("my_third_edge", 1, 2);
    try{
      myGraph.get_successors(3);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get successors
    vector<int> v;
    v = myGraph.get_successors(0);
    REQUIRE(v.size() == 1);
    REQUIRE(v[0] == 1);

    v = myGraph.get_successors(1);
    REQUIRE(v.size() == 2);
    REQUIRE(v[0] == 0);
    REQUIRE(v[1] == 2);

    v = myGraph.get_successors(2);
    REQUIRE(v.size() == 0);
}


TEST_CASE("get predecessors template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Get predecessors impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_predecessors(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get predecessors impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_node(MyClass(3));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    myGraph.add_edge(MyClass(12), 2, 1);
    try{
      myGraph.get_predecessors(3);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get predecessors
    vector<int> v;
    v = myGraph.get_predecessors(0);
    REQUIRE(v.size() == 1);
    REQUIRE(v[0] == 1);

    v = myGraph.get_predecessors(1);
    REQUIRE(v.size() == 2);
    REQUIRE(v[0] == 0);
    REQUIRE(v[1] == 2);

    v = myGraph.get_predecessors(2);
    REQUIRE(v.size() == 0);
}

TEST_CASE("get predecessors template String", "[template]")
{
    Graph<string> myGraph;

    // Get predecessors impossible d'un noeud car il n'existe pas (graph n'a pas de noeud)
    try{
      myGraph.get_predecessors(0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get predecessors impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_node("my_third_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    myGraph.add_edge("my_third_edge", 2, 1);
    try{
      myGraph.get_predecessors(3);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Get predecessors
    vector<int> v;
    v = myGraph.get_predecessors(0);
    REQUIRE(v.size() == 1);
    REQUIRE(v[0] == 1);

    v = myGraph.get_predecessors(1);
    REQUIRE(v.size() == 2);
    REQUIRE(v[0] == 0);
    REQUIRE(v[1] == 2);

    v = myGraph.get_predecessors(2);
    REQUIRE(v.size() == 0);
}
