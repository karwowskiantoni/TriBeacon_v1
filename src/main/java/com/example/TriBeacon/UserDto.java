package com.example.TriBeacon;

import com.example.TriBeacon.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private String name;
    private Set<String> connections;

    public static UserDto fromUser(User user) {
        return new UserDto(user.name(), user.connections().stream().map(User::name).collect(Collectors.toSet()));
    }

    public UserDto() {
    }

    public UserDto(String name, Set<String> connections) {
        this.name = name;
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getConnections() {
        return connections;
    }

    public void setConnections(Set<String> connections) {
        this.connections = connections;
    }
}
