package com.example.TriBeacon.model;

import com.vividsolutions.jts.geom.MultiPolygon;

public class Position {
    private final User user;
    private final MultiPolygon polygon;

    public Position(User user, MultiPolygon polygon) {
        this.user = user;
        this.polygon = polygon;
    }

    public User user() {
        return user;
    }

    public MultiPolygon polygon() {
        return polygon;
    }
}
