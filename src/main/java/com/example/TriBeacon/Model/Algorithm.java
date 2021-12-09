package com.example.TriBeacon.Model;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
  public Map<String, Integer> findAllConnectedBeacons(User user) {
    HashMap<String, Integer> beaconsDepth = new HashMap<>();
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
      addIfBeacon(beaconsDepth, neighbour);
      queue.removeFirst();
      List<UserWithDepth> connections =
          neighbour.user.connections().stream()
              .map(connection -> new UserWithDepth(connection, neighbour.depth + 1))
              .collect(Collectors.toList());
      for (UserWithDepth connection : connections) {
        if (notExists(connection, visited)) {
          visited.add(connection);
          queue.add(connection);
          addIfBeacon(beaconsDepth, connection);
        }
      }
    }
    return beaconsDepth;
  }

  private void addIfBeacon(HashMap<String, Integer> map, UserWithDepth dUser){
    if(dUser.user.isBeacon()){
      map.put(dUser.user.name(), dUser.depth);
    }
  }

  private boolean notExists(UserWithDepth userToCompare, List<UserWithDepth> users){
    for (UserWithDepth user:
            users) {
      if(Objects.equals(user.user.name(), userToCompare.user.name())){
        return false;
      }
    }
    return true;
  }

  private static class UserWithDepth {
    public User user;
    public int depth;

    public UserWithDepth(User user, int depth) {
      this.user = user;
      this.depth = depth;
    }
  }
}
