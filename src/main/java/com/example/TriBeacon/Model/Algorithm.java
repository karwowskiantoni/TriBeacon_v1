package com.example.TriBeacon.Model;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
  public Map<String, Integer> BDSBeaconPaths(User user) {
    HashMap<String, Integer> paths = new HashMap<>();
    List<UserWithDepth> neighbours =
        user.connections().stream()
            .map(connection -> new UserWithDepth(connection, 1))
            .collect(Collectors.toList());
    List<UserWithDepth> visited = new ArrayList<>();
    visited.add(new UserWithDepth(user, 0));

    LinkedList<UserWithDepth> queue = new LinkedList<>(neighbours);
    visited.addAll(neighbours);
    while (!queue.isEmpty()) {
      UserWithDepth neighbour = queue.getFirst();
      if (neighbour.user.isBeacon()) {
        paths.put(neighbour.user.name(), neighbour.depth);
      }
      queue.removeFirst();
      List<UserWithDepth> connections =
          neighbour.user.connections().stream()
              .map(connection -> new UserWithDepth(connection, neighbour.depth + 1))
              .collect(Collectors.toList());
      for (UserWithDepth connection : connections) {
        if (!exists(connection, connections)) {
          visited.add(connection);
          queue.add(connection);
          if (connection.user.isBeacon()) {
            paths.put(connection.user.name(), connection.depth);
          }
        }
      }
    }
    return paths;
  }

  private boolean exists(UserWithDepth userToCompare, List<UserWithDepth> users){
    for (UserWithDepth user:
            users) {
      if(Objects.equals(user.user.name(), userToCompare.user.name())){
        return true;
      }
    }
    return false;
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
