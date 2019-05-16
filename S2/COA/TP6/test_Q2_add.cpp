#include "catch.hpp"
#include "graphTemplate.hpp"
#include <string>
#include "myclass.hpp"

using namespace std;

TEST_CASE("add node template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Ajout d'un noeud dans un nouveau graph (donc qui n'a pas encore de noeud)
    REQUIRE(myGraph.get_nb_nodes() == 0);
    myGraph.add_node(MyClass(1));
    REQUIRE(myGraph.get_nb_nodes() == 1);

    // Ajout d'un noeud dans un graph qui a deja des noeuds
    myGraph.add_node(MyClass(2));
    REQUIRE(myGraph.get_nb_nodes() == 2);

    // Ajout impossible d'un noeud dans un graph qui a deja un noeud de meme nom
    try{
      myGraph.add_node(MyClass(2));
    }catch (const char * Msg) {}
    REQUIRE(myGraph.get_nb_nodes() == 2);
}

TEST_CASE("add node template String", "[template]")
{
  Graph<string> myGraph;

  // Ajout d'un noeud dans un nouveau graph (donc qui n'a pas encore de noeud)
  REQUIRE(myGraph.get_nb_nodes() == 0);
  myGraph.add_node("my_first_node");
  REQUIRE(myGraph.get_nb_nodes() == 1);

  // Ajout d'un noeud dans un graph qui a deja des noeuds
  myGraph.add_node("my_second_node");
  REQUIRE(myGraph.get_nb_nodes() == 2);

  // Ajout impossible d'un noeud dans un graph qui a deja un noeud de meme nom
  try{
    myGraph.add_node("my_second_node");
  }catch (const char * Msg) {}
  REQUIRE(myGraph.get_nb_nodes() == 2);
}

TEST_CASE("add edge template MyClass", "[template]")
{
    Graph<MyClass> myGraph;

    // Ajout d'une arete dans un graph avec noeuds source et dest existant
    REQUIRE(myGraph.get_nb_edges() == 0);
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_edge(MyClass(10), 0, 1);
    REQUIRE(myGraph.get_nb_edges() == 1);

    // Ajout impossible d'une arete dans un graph avec noeud source inexistant
    try{
      myGraph.add_edge(MyClass(11), 2, 1);
    }catch (const char * Msg) {}
    REQUIRE(myGraph.get_nb_edges() == 1);
    //
    // // Ajout impossible d'une arete dans un graph avec noeud dest inexistant
    try{
      myGraph.add_edge(MyClass(12), 0, 2);
    }catch (const char * Msg) {}
    REQUIRE(myGraph.get_nb_edges() == 1);
}

TEST_CASE("add edge template String", "[template]")
{
    Graph<string> myGraph;

    // Ajout d'une arete dans un graph avec noeuds source et dest existant
    REQUIRE(myGraph.get_nb_edges() == 0);
    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_edge("my_first_edge", 0, 1);
    REQUIRE(myGraph.get_nb_edges() == 1);

    // Ajout impossible d'une arete dans un graph avec noeud source inexistant
    try{
      myGraph.add_edge("my_first_edge", 2, 1);
    }catch (const char * Msg) {}
    REQUIRE(myGraph.get_nb_edges() == 1);
    //
    // // Ajout impossible d'une arete dans un graph avec noeud dest inexistant
    try{
      myGraph.add_edge("my_first_edge", 0, 2);
    }catch (const char * Msg) {}
    REQUIRE(myGraph.get_nb_edges() == 1);
}
