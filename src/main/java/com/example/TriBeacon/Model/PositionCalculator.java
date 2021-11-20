package com.example.TriBeacon.Model;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class PositionCalculator {

    private final double MIN_RANGE = 3;
    private final double MAX_RANGE = 5;

    private Polygon range(double x, double y, double radius) {
        double left = x - radius;
        double right = x + radius;
        double bottom = y - radius;
        double top = y + radius;
        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(left, top),
                new Coordinate(left, bottom),
                new Coordinate(right, bottom),
                new Coordinate(right, top),
                new Coordinate(left, top)};
        GeometryFactory fact = new GeometryFactory();
        LinearRing linear = new GeometryFactory().createLinearRing(coordinates);
        return new Polygon(linear, null, fact);
    }

    public Polygon calculatePosition(User user, Users users) {
        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(1, 0),
                new Coordinate(0, 0)};
        GeometryFactory fact = new GeometryFactory();
        LinearRing linear = new GeometryFactory().createLinearRing(coordinates);
        return new Polygon(linear, null, fact);
    }
}
