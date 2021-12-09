package com.example.TriBeacon.Model;

import com.vividsolutions.jts.geom.Polygon;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private final String name;
    private final Set<User> connections;
    private final boolean isBeacon;
    private final double x;
    private final double y;

    public User(String name) {
        this.name = name;
        connections = new HashSet<>();
        isBeacon = false;
        x = 0;
        y = 0;
    }

    public User(String name, double x, double y) {
        this.name = name;
        connections = new HashSet<>();
        isBeacon = true;
        this.x = x;
        this.y = y;
    }

    public void updateConnections(Set<User> newConnections) {
        connections.clear();
        connections.addAll(newConnections);
    }

    public Set<User> connections() {
        return connections;
    }

    public String name() {
        return name;
    }


    public boolean isBeacon() {
        return isBeacon;
    }

    public double x() {
        return x;
    }

    public  double y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isBeacon == user.isBeacon && Double.compare(user.x, x) == 0 && Double.compare(user.y, y) == 0 && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isBeacon, x, y);
    }
}
