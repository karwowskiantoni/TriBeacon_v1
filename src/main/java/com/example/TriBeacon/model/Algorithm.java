package com.example.TriBeacon.model;

import java.util.*;

public class Algorithm {
    //TODO todo todo todo todo todo todo todo todo
    //each unique beacon found should be returned as name(String) + depthLevel(int)
    public static Map<String, Integer> BDSBeaconPaths(User user) {
        HashMap<String, Integer> paths = new HashMap<>();
        Set<User> neighbours = user.connections();
        HashSet<User> visited = new HashSet<>();
        visited.add(user);

        int counter = 0;
        LinkedList<User> queue = new LinkedList<>(neighbours);
        visited.addAll(neighbours);
        while (!queue.isEmpty()){
            User neighbour = queue.getFirst();
            if(neighbour.isBeacon()){
                paths.put(neighbour.name(), counter);
            }
            queue.removeFirst();
            Set<User> connections = neighbour.connections();
            for (User connection: connections) {
                if(visited.add(connection)){
                    queue.add(connection);
                    if(connection.isBeacon()){
                        paths.put(connection.name(), counter);
                    }
                }
            }
            counter++;
        }
        return paths;
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
//          c = q.get()
//          children = labyrinth.neighbours(c)
//          for child in children:
//              if child not in visited_nodes:
//                  visited_nodes.append(child)
//                  parents.push(child, c)
//                  q.put(child)
//                  if labyrinth.is_end(child):
//                      return parents.path(child)
//        return "Brak ścieżki do celu"
