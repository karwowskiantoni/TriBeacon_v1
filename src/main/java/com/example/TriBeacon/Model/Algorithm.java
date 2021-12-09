package com.example.TriBeacon.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithm {
    //TODO todo todo todo todo todo todo todo todo
    //each unique beacon found should be returned as name(String) + depthLevel(int)
    public List<Map<String, Integer>> BDSBeaconPaths(User user) {
        Set<User> children = user.connections();
        return null;
    }
}

//    def breadth_first_search(labyrinth):
//        q = Queue()
//        parents = Parents()
//
//        q.put(labyrinth.begin_node)
//        visited_nodes = [labyrinth.begin_node]
//
//        while not q.empty():
//        c = q.get()
//        children = labyrinth.neighbours(c)
//        for child in children:
//        if child not in visited_nodes:
//        visited_nodes.append(child)
//        parents.push(child, c)
//        q.put(child)
//        if labyrinth.is_end(child):
//        return parents.path(child)
//        return "Brak ścieżki do celu"
