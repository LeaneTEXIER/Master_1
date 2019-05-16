#include "catch.hpp"
#include "graphTemplate.hpp"
#include <string>
#include "myclass.hpp"

using namespace std;

TEST_CASE("all paths template MyClass", "[template]")
{
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

    using Path = std::vector<int>;
    // All paths dans un graph avec noeuds source et dest existant
    vector<Path> paths;
    paths = myGraph.all_paths(0, 5);
    REQUIRE(paths.size() == 4);

    paths = myGraph.all_paths(5, 5);
    REQUIRE(paths.size() == 1);

    paths = myGraph.all_paths(2, 5);
    REQUIRE(paths.size() == 3);


    // All paths dans un graph impossible car noeud source n'existe pas
    try{
      myGraph.all_paths(100, 0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // All paths dans un graph impossible car noeud dest n'existe pas
    try{
      myGraph.all_paths(0, 100);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }
}

TEST_CASE("all paths template String", "[template]")
{
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

    using Path = std::vector<int>;
    // All paths dans un graph avec noeuds source et dest existant
    vector<Path> paths;
    paths = myGraph.all_paths(0, 5);
    REQUIRE(paths.size() == 4);

    paths = myGraph.all_paths(5, 5);
    REQUIRE(paths.size() == 1);

    paths = myGraph.all_paths(2, 5);
    REQUIRE(paths.size() == 3);


    // All paths dans un graph impossible car noeud source n'existe pas
    try{
      myGraph.all_paths(100, 0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // All paths dans un graph impossible car noeud dest n'existe pas
    try{
      myGraph.all_paths(0, 100);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }
}

TEST_CASE("shortest path template MyClass", "[template]")
{
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

    using Path = std::vector<int>;
    // Shortest path dans un graph avec noeuds source et dest existant
    Path p;
    p = myGraph.shortest_path(0, 5);
    REQUIRE(p.size() == 3);

    p = myGraph.shortest_path(5, 5);
    REQUIRE(p.size() == 1);

    p = myGraph.shortest_path(2, 5);
    REQUIRE(p.size() == 2);


    // Shortest path dans un graph impossible car noeud source n'existe pas
    try{
      myGraph.all_paths(100, 0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Shortest path dans un graph impossible car noeud dest n'existe pas
    try{
      myGraph.all_paths(0, 100);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }
}


TEST_CASE("shortest path template String", "[template]")
{
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

    using Path = std::vector<int>;
    // Shortest path dans un graph avec noeuds source et dest existant
    Path p;
    p = myGraph.shortest_path(0, 5);
    REQUIRE(p.size() == 3);

    p = myGraph.shortest_path(5, 5);
    REQUIRE(p.size() == 1);

    p = myGraph.shortest_path(2, 5);
    REQUIRE(p.size() == 2);


    // Shortest path dans un graph impossible car noeud source n'existe pas
    try{
      myGraph.all_paths(100, 0);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }

    // Shortest path dans un graph impossible car noeud dest n'existe pas
    try{
      myGraph.all_paths(0, 100);
      REQUIRE (2==1);
    }catch (const char * Msg) {
      REQUIRE (1==1);
    }
}
