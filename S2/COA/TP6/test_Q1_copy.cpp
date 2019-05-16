#include "catch.hpp"
#include "graph.hpp"
#include <string>

using namespace std;

TEST_CASE("copy constructor", "[graph]")
{
    // Copie d'un graph vide
    Graph myGraph0;
    Graph myGraph02 = Graph(myGraph0);
    REQUIRE(myGraph0.get_nb_nodes() == myGraph02.get_nb_nodes());
    REQUIRE(myGraph0.get_nb_edges() == myGraph02.get_nb_edges());


    // Copie d'un graph sans arete (que des noeuds)
    Graph myGraph1;
    myGraph1.add_node("node0");
    myGraph1.add_node("node1");
    myGraph1.add_node("node2");
    myGraph1.add_node("node3");
    myGraph1.add_node("node4");
    myGraph1.add_node("node5");
    Graph myGraph12 = Graph(myGraph1);
    REQUIRE(myGraph1.get_nb_nodes() == myGraph12.get_nb_nodes());
    REQUIRE(myGraph1.get_nb_edges() == myGraph12.get_nb_edges());

    // Copie d'une graph avec noeuds et aretes
    Graph myGraph;
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
    Graph myGraph2 = Graph(myGraph);
    REQUIRE(myGraph.get_nb_nodes() == myGraph2.get_nb_nodes());
    REQUIRE(myGraph.get_nb_edges() == myGraph2.get_nb_edges());

}
