package com.example.TriBeacon.Model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Set<String> connections;

    public User(String name) {
        this.name = name;
        connections = new HashSet<>();
    }

    public User(String name, Set<String> connections) {
        this.name = name;
        this.connections = connections;
    }

    public void updateConnections(Set<String> newConnections) {
        connections.clear();
        connections.addAll(newConnections);
    }

    public Set<String> getConnections() {
        return connections;
    }

    public String getName() {
        return name;
    }
}
