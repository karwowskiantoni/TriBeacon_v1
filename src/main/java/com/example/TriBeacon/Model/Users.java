package com.example.TriBeacon.Model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

import java.util.*;
import java.util.stream.Collectors;

public class Users {
  private final Map<String, User> map = new HashMap<>();
  private final double MIN_RANGE = 3;
  private final double MAX_RANGE = 5;

  public Polygon calculatePosition(String name) {
    Set<User> connections = map.get(name).connections();
    //    List<List<User>> connectionsToBeacons =
    User connection = connections.stream().findAny().get();
    return polygonInRange(0, 0, MIN_RANGE);
  }

  public Set<Polygon> calculatePositions() {
    return map.keySet().stream().map(this::calculatePosition).collect(Collectors.toSet());
  }

  public void addBeacon(String name, double x, double y) {
    map.put(name, new User(name, x, y));
  }

  public void addUser(String name) {
    map.put(name, new User(name));
  }

  public void update(String name, Set<String> connections) {
    map.get(name).updateConnections(getByNames(connections));
  }

  public List<User> getUsers() {
    return new ArrayList<>(map.values());
  }

  private Set<User> getByNames(Set<String> connections) {
    return map.values().stream()
        .filter(user -> connections.contains(user.name()))
        .collect(Collectors.toSet());
  }

  private Polygon polygonInRange(double x, double y, double radius) {
    double left = x - radius;
    double right = x + radius;
    double bottom = y - radius;
    double top = y + radius;
    Coordinate[] coordinates =
        new Coordinate[] {
          new Coordinate(left, top),
          new Coordinate(left, bottom),
          new Coordinate(right, bottom),
          new Coordinate(right, top),
          new Coordinate(left, top)
        };
    GeometryFactory fact = new GeometryFactory();
    LinearRing linear = new GeometryFactory().createLinearRing(coordinates);
    return new Polygon(linear, null, fact);
  }
}
