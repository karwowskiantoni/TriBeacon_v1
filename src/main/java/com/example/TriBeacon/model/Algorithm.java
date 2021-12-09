package com.example.TriBeacon.model;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    //TODO todo todo todo todo todo todo todo todo
    //each unique beacon found should be returned as name(String) + depthLevel(int)
    public Map<String, Integer> BDSBeaconPaths(User user) {
        HashMap<String, Integer> paths = new HashMap<>();
        Set<UserWithDepth>  neighbours = user.connections().stream().map(connection -> new UserWithDepth(connection, 1)).collect(Collectors.toSet());
        HashSet<UserWithDepth> visited = new HashSet<>();
        visited.add(new UserWithDepth(user, 0));

        LinkedList<UserWithDepth> queue = new LinkedList<>(neighbours);
        visited.addAll(neighbours);
        while (!queue.isEmpty()){
            UserWithDepth neighbour = queue.getFirst();
            if(neighbour.user.isBeacon()){
                paths.put(neighbour.user.name(), neighbour.depth);
            }
            queue.removeFirst();
            Set<UserWithDepth> connections = neighbour.user.connections().stream().map(connection -> new UserWithDepth(connection, neighbour.depth + 1)).collect(Collectors.toSet());
            for (UserWithDepth connection: connections) {
                if(visited.add(connection)){
                    queue.add(connection);
                    if(connection.user.isBeacon()){
                        paths.put(connection.user.name(), connection.depth);
                    }
                }
            }
        }
        return paths;
    }

    private class UserWithDepth{
        public User user;
        public int depth;

        public UserWithDepth(User user, int depth) {
            this.user = user;
            this.depth = depth;
        }
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
