#include <iostream>
#include <vector>
#include <list>
#include <map>

class Graph {
    struct Node {
        int node_id;
        std::string data;
    };

    struct Edge {
        int edge_id;
        std::string data;
        int source_id;
        int dest_id;
    };

    /* data structures */

    // Liste des noeuds
    std::list<Node> _nodes;

    // Liste des arêtes
    std::list<Edge> _edges;

    int _nextIdNode = 0;

    int _nextIdEdges = 0;

    public:

        Graph() {}

        Graph(const Graph &other);

        int get_nb_nodes();

        int get_nb_edges();

        int add_node(const std::string &m);

        int add_edge(const std::string &m, int source_id, int dest_id);

        int remove_node(int node_id);

        int search_node(const std::string &m) const;

        std::string get_node(int node_id) const;

        std::string get_edge(int edge_id) const;

        int get_source(int edge_id) const;

        int get_dest(int edge_id) const;

        std::vector<int> get_successors(int node_id) const;

        std::vector<int> get_predecessors(int node_id) const;

        using Path = std::vector<int>;
        std::vector<Path> all_paths(int from, int to) const;

        Path shortest_path(int from, int to) const;
};
