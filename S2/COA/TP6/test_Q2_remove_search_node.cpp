#include "catch.hpp"
#include "graphTemplate.hpp"
#include <string>
#include "myclass.hpp"

using namespace std;

TEST_CASE("remove node template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Suppression impossible d'un noeud car il n'existe pas (graph n'a pas de noeuds)
    REQUIRE(myGraph.get_nb_nodes() == 0);
    myGraph.remove_node(0);
    REQUIRE(myGraph.get_nb_nodes() == 0);

    // Suppression impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_node(MyClass(3));
    REQUIRE(myGraph.get_nb_nodes() == 3);
    myGraph.remove_node(5);
    REQUIRE(myGraph.get_nb_nodes() == 3);

    // Suppression d'un noeud
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 1, 0);
    myGraph.add_edge(MyClass(12), 1, 2);
    REQUIRE(myGraph.get_nb_edges() == 3);
    myGraph.remove_node(0);
    REQUIRE(myGraph.get_nb_nodes() == 2);
    REQUIRE(myGraph.get_nb_edges() == 1);
}

TEST_CASE("remove node template String", "[template]")
{
    Graph<string> myGraph;

    // Suppression impossible d'un noeud car il n'existe pas (graph n'a pas de noeuds)
    REQUIRE(myGraph.get_nb_nodes() == 0);
    myGraph.remove_node(0);
    REQUIRE(myGraph.get_nb_nodes() == 0);

    // Suppression impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_node("my_third_node");
    REQUIRE(myGraph.get_nb_nodes() == 3);
    myGraph.remove_node(5);
    REQUIRE(myGraph.get_nb_nodes() == 3);

    // Suppression d'un noeud
    myGraph.add_edge("my_first_edge", 0, 1);
    myGraph.add_edge("my_second_edge", 1, 0);
    myGraph.add_edge("my_third_edge", 1, 2);
    REQUIRE(myGraph.get_nb_edges() == 3);
    myGraph.remove_node(0);
    REQUIRE(myGraph.get_nb_nodes() == 2);
    REQUIRE(myGraph.get_nb_edges() == 1);
}

TEST_CASE("search node template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Recherche impossible d'un noeud car il n'existe pas (graph n'a pas de noeuds)
    try{
      myGraph.search_node(MyClass(50));
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Recherche impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    try{
      myGraph.search_node(MyClass(50));
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Recherche d'un noeud
    REQUIRE(myGraph.search_node(1) == 0);
}

TEST_CASE("search node template String", "[template]")
{
    Graph<string> myGraph;

    // Recherche impossible d'un noeud car il n'existe pas (graph n'a pas de noeuds)
    try{
      myGraph.search_node("dont_exist");
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Recherche impossible d'un noeud car il n'existe pas (graph a des noeuds)
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    try{
      myGraph.search_node("dont_exist");
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Recherche d'un noeud
    REQUIRE(myGraph.search_node("my_first_node") == 0);
}
