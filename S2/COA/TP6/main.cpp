#include <vector>
#include <iostream>

#include "graph.hpp"

using namespace std;

int main()
{
    Graph myGraph = Graph();

    myGraph.add_node("my_first_node");
    myGraph.add_node("my_second_node");
    myGraph.add_node("my_third_node");
    myGraph.add_node("my_fourth_node");
    myGraph.add_node("my_fifth_node");

    myGraph.add_edge("edge1", 0, 1);
    myGraph.add_edge("edge2", 1, 0);
    myGraph.add_edge("edge3", 1, 2);
    myGraph.add_edge("edge4", 2, 3);
    myGraph.add_edge("edge5", 0, 4);
    myGraph.add_edge("edge6", 4, 3);
    myGraph.add_edge("edge7", 3, 4);

    myGraph.remove_node(0);

    // Get successors
    // vector<int> v = myGraph.get_successors(0);
    // for (int i : v){
    //     cout << i << endl;
    // }

    // Get predecessors
    // v = myGraph.get_predecessors(2);
    // for (int i : v){
    //     cout << i << endl;
    // }

    // Get node
    // cout << myGraph.get_node(0) << endl;
    // try {
    //     myGraph.get_node(100);
    // }
    // catch ( const char * Msg ) {
    //     std::cerr << Msg << endl;
    // }

    // Search node
    // cout << myGraph.search_node("my_second_node") << endl;
    // try {
    //     cout << myGraph.search_node("my_node_dont_exists") << endl;
    // }
    // catch ( const char * Msg ) {
    //     std::cerr << Msg << endl;
    // }

    // Get all paths
    // cout << "All paths : " << endl;
    // for (auto path : myGraph.all_paths(0,3)){
    //   cout << "Path : ";
    //   for (auto node : path){
    //     cout << node << " ";
    //   }
    //   cout << "" << endl;
    // }

    // Get shortest path
    // cout << "Shortest path : ";
    // for (auto node : myGraph.shortest_path(0,3)){
    //   cout << node << " ";
    // }
    // cout << "" << endl;

}
