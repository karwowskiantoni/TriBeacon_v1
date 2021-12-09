package com.example.TriBeacon.Model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Set<User> connections;

    public User(String name) {
        this.name = name;
        connections = new HashSet<>();
    }

    public User(String name, Set<User> connections) {
        this.name = name;
        this.connections = connections;
    }

    public void updateConnections(Set<User> newConnections) {
        connections.clear();
        connections.addAll(newConnections);
    }

    public Set<User> getConnections() {
        return connections;
    }

    public String getName() {
        return name;
    }
}
