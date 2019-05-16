#include <iostream>
#include <vector>
#include <list>
#include <map>
#include <string>
#include <sstream>
#include "graph.hpp"


    Graph::Graph(const Graph &other) {
      _nodes = other._nodes;
      _edges = other._edges;
      _nextIdNode = other._nextIdNode;
      _nextIdEdges = other._nextIdEdges;
    }

    int Graph::get_nb_nodes(){
      return _nodes.size();
    }

    int Graph::get_nb_edges(){
      return _edges.size();
    }

    int Graph::add_node(const std::string &m) {
        try{
          search_node(m);
          return -1;
        }catch (const char * Msg) {
            Node n;
            n.node_id = _nextIdNode++;
            n.data = m;
            _nodes.push_back(n);
            return n.node_id;
        }
    }

    int Graph::add_edge(const std::string &m, int source_id, int dest_id) {
        try {
            get_node(source_id);
            get_node(dest_id);

            Edge e;
            e.edge_id = _nextIdEdges++;
            e.data = m;
            e.source_id = source_id;
            e.dest_id = dest_id;
            _edges.push_back(e);
            return e.edge_id;
        }
        catch (const char * Msg) {
            throw "Edge not add because source or dest node id doesn't exist.";
        }
    }

    int Graph::remove_node(int node_id){
        for (auto it = _nodes.begin(); it != _nodes.end(); it++){
            if (it->node_id == node_id){
                _nodes.erase(it);
                // Remove edges linked
                for (auto it2 = _edges.begin(); it2 != _edges.end(); it2++){
                    if ((it2->source_id == node_id) || (it2->dest_id == node_id)){
                      _edges.erase(it2);
                      it2--;
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    int Graph::search_node(const std::string &m) const {
        for (auto node : _nodes){
            if (node.data == m){
                return node.node_id;
            }
        }
        throw "Node doesn't exist";
    }

    std::string Graph::get_node(int node_id) const {
        for (auto node : _nodes){
            if (node.node_id == node_id){
                return node.data;
            }
        }
        throw "Node doesn't exist";
    }

    std::string Graph::get_edge(int edge_id) const {
        for (auto edge : _edges){
          if (edge.edge_id == edge_id){
              return edge.data;
          }
        }
        throw "Edge doesn't exist";
    }

    int Graph::get_source(int edge_id) const {
        for (auto edge : _edges){
          if (edge.edge_id == edge_id){
              return edge.source_id;
          }
        }
        throw "Edge doesn't exist";
    }

    int Graph::get_dest(int edge_id) const {
        for (auto edge : _edges){
          if (edge.edge_id == edge_id){
              return edge.dest_id;
          }
        }
        throw "Edge doesn't exist";
    }

    std::vector<int> Graph::get_successors(int node_id) const {
        try{
          get_node(node_id);
          std::vector<int> res;
          for (auto edge : _edges) {
              if(edge.source_id == node_id){
                  res.push_back(edge.dest_id);
              }
          }
          return res;
        }catch (const char * Msg) {
            throw Msg;
        }
    }

    std::vector<int> Graph::get_predecessors(int node_id) const {
        try{
          get_node(node_id);
          std::vector<int> res;
          for (auto edge : _edges) {
              if(edge.dest_id == node_id){
                  res.push_back(edge.source_id);
              }
          }
          return res;
        }catch (const char * Msg) {
            throw Msg;
        }
    }

    using Path = std::vector<int>;

    std::vector<Path> Graph::all_paths(int from, int to) const {
      try{
        get_node(from);
        get_node(to);

        int last_node;
        bool visited;
        std::vector<Path> resPaths;
        std::vector<Path> pathsToCheck;
        Path p, p2;

        p.push_back(from);
        pathsToCheck.push_back(p);

        while(!pathsToCheck.empty()){
          p = pathsToCheck.front();
          pathsToCheck.erase(pathsToCheck.begin());
          last_node = p.back();

          if(to == last_node){
            resPaths.push_back(p);
          }

          for(auto s : get_successors(last_node)){
            // check si noeud deja dans chemin
            visited = false;
            for (auto node : p){
              if(node == s){
                visited = true;
              }
            }
            if(!visited){
              p2 = p;
              p2.push_back(s);
              pathsToCheck.push_back(p2);
            }
          }
        }
        return resPaths;
      }catch (const char * Msg) {
          throw "One or the two nodes don't exist";
      }
    }


    Path Graph::shortest_path(int from, int to) const {
      try{
        std::vector<Path> paths = all_paths(from, to);
        if(paths.size() == 0){
          return Path();
        }
        Path path_min = paths[0];
        unsigned int size_min = path_min.size();
        for(auto path : paths){
          if(path.size() < size_min){
            path_min = path;
            size_min = path_min.size();
          }
        }
        return path_min;
      }catch (const char * Msg) {
          throw Msg;
      }
    }
