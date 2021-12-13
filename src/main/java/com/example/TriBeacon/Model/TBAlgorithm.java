package com.example.TriBeacon.Model;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.util.GeometricShapeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TBAlgorithm {
  private static final double MIN_RANGE = 100;
  private static final double MAX_RANGE = 150;

  public static List<MultiPolygon> positions(Users users, boolean reduceBeacons, int reduceUsers) {
    List<Position> positions =
        users.getUsers().stream()
            .map(TBAlgorithm::positionFromBeacons)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    if (reduceBeacons) {
      positions =
          positions.stream()
              .map(position -> reduceByBeaconsRange(position, users))
              .collect(Collectors.toList());
    }

    for (int i = 0; i < reduceUsers; i++) {
      List<Position> finalPositions = new ArrayList<>(positions);
      positions =
          positions.stream()
              .map(position -> reduceByUsersRange(position, finalPositions))
              .collect(Collectors.toList());
    }

    return positions.stream().map(Position::polygon).collect(Collectors.toList());
  }

  private static Position reduceByBeaconsRange(Position position, Users users) {
    MultiPolygon polygon = position.polygon();
    for (User user : users.getUsers()) {
      if (user.isBeacon() && !position.user().connections().contains(user)) {
        polygon =
            secureMultiPolygon(polygon.difference(circleInRange(user.x(), user.y(), MAX_RANGE)));
      }
    }
    return new Position(position.user(), polygon);
  }

  private static Position reduceByUsersRange(Position position, List<Position> positions) {
    MultiPolygon polygon = position.polygon();
    for (Position otherPosition : positions) {
      if (!otherPosition.user().isBeacon()
          && !position.equals(otherPosition)
          && !position.user().connections().contains(otherPosition.user())) {
        Envelope envelope = otherPosition.polygon().getEnvelopeInternal();
        Point centroid = otherPosition.polygon().getCentroid();
        double radius = MAX_RANGE - (Math.max(envelope.getWidth(), envelope.getHeight()) / 2);
        if (radius > 0) {
          polygon =
              secureMultiPolygon(
                  polygon.difference(circleInRange(centroid.getX(), centroid.getY(), radius)));
        }
      }
    }
    return new Position(position.user(), polygon);
  }

  private static Optional<Position> positionFromBeacons(User user) {
    if (user.isBeacon()) {
      return Optional.empty();
    } else {
      List<MultiPolygon> ranges = beaconsRange(user);
      if (ranges.isEmpty()) {
        return Optional.empty();
      } else if (ranges.size() == 1) {
        return Optional.of(new Position(user, ranges.get(0)));
      } else {
        MultiPolygon polygon = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
          polygon = secureMultiPolygon(ranges.get(i).intersection(polygon));
        }
        return Optional.of(new Position(user, polygon));
      }
    }
  }

  private static List<MultiPolygon> beaconsRange(User user) {
    return BFSAlgorithm.findAllConnectedBeacons(user).entrySet().stream()
        .map(
            entry ->
                circleInRange(entry.getKey().x(), entry.getKey().y(), MAX_RANGE * entry.getValue()))
        .collect(Collectors.toList());
  }

  private static MultiPolygon polygonInRange(double x, double y, double radius) {
    GeometryFactory factory = new GeometryFactory();
    Polygon polygon =
        new Polygon(
            factory.createLinearRing(
                new Coordinate[] {
                  new Coordinate(x - radius, y + radius),
                  new Coordinate(x - radius, y - radius),
                  new Coordinate(x + radius, y - radius),
                  new Coordinate(x + radius, y + radius),
                  new Coordinate(x - radius, y + radius)
                }),
            null,
            factory);
    return secureMultiPolygon(polygon);
  }

  private static MultiPolygon circleInRange(double x, double y, double radius) {
    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    shapeFactory.setNumPoints(12);
    shapeFactory.setCentre(new Coordinate(x, y));
    shapeFactory.setSize(radius * 2);
    return secureMultiPolygon(shapeFactory.createCircle());
  }

  private static MultiPolygon secureMultiPolygon(Geometry geometry) {
    if (geometry.getClass().equals(Polygon.class)) {
      return new MultiPolygon(new Polygon[] {(Polygon) geometry}, new GeometryFactory());
    } else if (geometry.getClass().equals(MultiPolygon.class)) {
      return (MultiPolygon) geometry;
    } else {
      throw new RuntimeException("not supported geometry type");
    }
  }
}
