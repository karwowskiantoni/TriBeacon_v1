package com.example.TriBeacon.Model;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
  public Map<String, Integer> BDSBeaconPaths(User user) {
    HashMap<String, Integer> paths = new HashMap<>();
    Set<UserWithDepth> neighbours =
        user.connections().stream()
            .map(connection -> new UserWithDepth(connection, 1))
            .collect(Collectors.toSet());
    HashSet<UserWithDepth> visited = new HashSet<>();
    visited.add(new UserWithDepth(user, 0));

    LinkedList<UserWithDepth> queue = new LinkedList<>(neighbours);
    visited.addAll(neighbours);
    while (!queue.isEmpty()) {
      UserWithDepth neighbour = queue.getFirst();
      if (neighbour.user.isBeacon()) {
        paths.put(neighbour.user.name(), neighbour.depth);
      }
      queue.removeFirst();
      Set<UserWithDepth> connections =
          neighbour.user.connections().stream()
              .map(connection -> new UserWithDepth(connection, neighbour.depth + 1))
              .collect(Collectors.toSet());
      for (UserWithDepth connection : connections) {
        if (visited.add(connection)) {
          queue.add(connection);
          if (connection.user.isBeacon()) {
            paths.put(connection.user.name(), connection.depth);
          }
        }
      }
    }
    return paths;
  }

  private class UserWithDepth {
    public User user;
    public int depth;

    public UserWithDepth(User user, int depth) {
      this.user = user;
      this.depth = depth;
    }
  }
}
