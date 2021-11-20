package com.example.TriBeacon;

import com.example.TriBeacon.Model.User;

import java.util.Set;

public class UserDto {
    private String name;
    private Set<String> connections;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getName(), user.getConnections());
    }

    public User toUser() {
        return new User(name, connections);
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
