package com.example.TriBeacon.model;

import java.util.*;
import java.util.stream.Collectors;

public class Users {
  private final Map<String, User> map = new HashMap<>();

  public List<Position> calculatePositions(boolean reduceBeacons, int reduceUsers) {
    return TBAlgorithm.positions(this, reduceBeacons, reduceUsers);
  }

  public void addBeacon(String name, double x, double y) {
    map.put(name, new User(name, x, y));
  }

  public void addUser(String name) {
    map.put(name, new User(name));
  }

  public User get(String name) {
    return map.get(name);
  }

  public int size() {
    return map.size();
  }

  public void update(String name, Set<String> connections) {
    try {
      map.get(name).updateConnections(getByNames(connections));
    } catch (NullPointerException e) {
      System.out.println("update of nonexistent user: " + name);
    }
  }

  public List<User> getUsers() {
    return new ArrayList<>(map.values());
  }

  public List<String> getNames() {
    return new ArrayList<>(map.keySet());
  }

  public void deleteAll() {
    map.clear();
  }

  private Set<User> getByNames(Set<String> connections) {
    return map.values().stream()
        .filter(user -> connections.contains(user.name()))
        .collect(Collectors.toSet());
  }
}
