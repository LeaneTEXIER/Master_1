#include "catch.hpp"
#include "graphTemplate.hpp"
#include <string>
#include "myclass.hpp"

using namespace std;

TEST_CASE("copy constructor template MyClass", "[graph]")
{
    // Copie d'un graph vide
    Graph<MyClass> myGraph0;
    Graph<MyClass> myGraph02 = Graph<MyClass>(myGraph0);
    REQUIRE(myGraph0.get_nb_nodes() == myGraph02.get_nb_nodes());
    REQUIRE(myGraph0.get_nb_edges() == myGraph02.get_nb_edges());


    // Copie d'un graph sans arete (que des noeuds)
    Graph<MyClass> myGraph1;
    myGraph1.add_node(MyClass(0));
    myGraph1.add_node(MyClass(1));
    myGraph1.add_node(MyClass(2));
    myGraph1.add_node(MyClass(3));
    myGraph1.add_node(MyClass(4));
    myGraph1.add_node(MyClass(5));
    Graph<MyClass> myGraph12 = Graph<MyClass>(myGraph1);
    REQUIRE(myGraph1.get_nb_nodes() == myGraph12.get_nb_nodes());
    REQUIRE(myGraph1.get_nb_edges() == myGraph12.get_nb_edges());

    // Copie d'une graph avec noeuds et aretes
    Graph<MyClass> myGraph;
    myGraph.add_node(MyClass(0));
    myGraph.add_node(MyClass(1));
    myGraph.add_node(MyClass(2));
    myGraph.add_node(MyClass(3));
    myGraph.add_node(MyClass(4));
    myGraph.add_node(MyClass(5));
    myGraph.add_edge(MyClass(10), 0, 1);
    myGraph.add_edge(MyClass(11), 0, 4);
    myGraph.add_edge(MyClass(12), 1, 2);
    myGraph.add_edge(MyClass(13), 2, 3);
    myGraph.add_edge(MyClass(14), 2, 4);
    myGraph.add_edge(MyClass(15), 2, 5);
    myGraph.add_edge(MyClass(16), 3, 5);
    myGraph.add_edge(MyClass(17), 4, 5);
    myGraph.add_edge(MyClass(18), 3, 2);
    myGraph.add_edge(MyClass(19), 4, 0);
    Graph<MyClass> myGraph2 = Graph<MyClass>(myGraph);
    REQUIRE(myGraph.get_nb_nodes() == myGraph2.get_nb_nodes());
    REQUIRE(myGraph.get_nb_edges() == myGraph2.get_nb_edges());
}

TEST_CASE("copy constructor template String", "[graph]")
{
    // Copie d'un graph vide
    Graph<string> myGraph0;
    Graph<string> myGraph02 = Graph<string>(myGraph0);
    REQUIRE(myGraph0.get_nb_nodes() == myGraph02.get_nb_nodes());
    REQUIRE(myGraph0.get_nb_edges() == myGraph02.get_nb_edges());


    // Copie d'un graph sans arete (que des noeuds)
    Graph<string> myGraph1;
    myGraph1.add_node("node0");
    myGraph1.add_node("node1");
    myGraph1.add_node("node2");
    myGraph1.add_node("node3");
    myGraph1.add_node("node4");
    myGraph1.add_node("node5");
    Graph<string> myGraph12 = Graph<string>(myGraph1);
    REQUIRE(myGraph1.get_nb_nodes() == myGraph12.get_nb_nodes());
    REQUIRE(myGraph1.get_nb_edges() == myGraph12.get_nb_edges());

    // Copie d'une graph avec noeuds et aretes
    Graph<string> myGraph;
    myGraph.add_node("node0");
    myGraph.add_node("node1");
    myGraph.add_node("node2");
    myGraph.add_node("node3");
    myGraph.add_node("node4");
    myGraph.add_node("node5");
    myGraph.add_edge("edge0", 0, 1);
    myGraph.add_edge("edge1", 0, 4);
    myGraph.add_edge("edge2", 1, 2);
    myGraph.add_edge("edge3", 2, 3);
    myGraph.add_edge("edge4", 2, 4);
    myGraph.add_edge("edge5", 2, 5);
    myGraph.add_edge("edge6", 3, 5);
    myGraph.add_edge("edge7", 4, 5);
    myGraph.add_edge("edge8", 3, 2);
    myGraph.add_edge("edge9", 4, 0);
    Graph<string> myGraph2 = Graph<string>(myGraph);
    REQUIRE(myGraph.get_nb_nodes() == myGraph2.get_nb_nodes());
    REQUIRE(myGraph.get_nb_edges() == myGraph2.get_nb_edges());
}
