package com.example.TriBeacon.Model;

import java.util.*;
import java.util.stream.Collectors;

public class BFSAlgorithm {
  public static Map<User, Integer> findAllConnectedBeacons(User user) {
    HashMap<User, Integer> beaconsDepth = new HashMap<>();
    Set<UserWithDepth> neighbours =
        user.connections().stream()
            .map(connection -> new UserWithDepth(connection, 1))
            .collect(Collectors.toSet());
    Set<UserWithDepth> visited = new HashSet<>();
    visited.add(new UserWithDepth(user, 0));

    LinkedList<UserWithDepth> queue = new LinkedList<>(neighbours);
    visited.addAll(neighbours);
    while (!queue.isEmpty()) {
      UserWithDepth neighbour = queue.getFirst();
      addIfBeacon(beaconsDepth, neighbour);
      queue.removeFirst();
      Set<UserWithDepth> connections =
          neighbour.user.connections().stream()
              .map(connection -> new UserWithDepth(connection, neighbour.depth + 1))
              .collect(Collectors.toSet());
      for (UserWithDepth connection : connections) {
        if (visited.add(connection)) {
          queue.add(connection);
          addIfBeacon(beaconsDepth, connection);
        }
      }
    }
    return beaconsDepth;
  }

  private static void addIfBeacon(HashMap<User, Integer> map, UserWithDepth dUser) {
    if (dUser.user.isBeacon()) {
      map.put(dUser.user, dUser.depth);
    }
  }

  private static class UserWithDepth {
    public User user;
    public int depth;

    public UserWithDepth(User user, int depth) {
      this.user = user;
      this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof UserWithDepth)) return false;
      UserWithDepth that = (UserWithDepth) o;
      return user.equals(that.user);
    }

    @Override
    public int hashCode() {
      return Objects.hash(user);
    }
  }
}
