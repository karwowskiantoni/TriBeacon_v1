package com.example.TriBeacon.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {
    private final Map<String, User> map = new HashMap<>();

    public boolean add(String name) {
        if (map.containsKey(name)) {
            return false;
        } else {
            map.put(name, new User(name));
            return true;
        }
    }

    public void update(User user) {
        map.get(user.getName()).updateConnections(user.getConnections());
    }

    public List<User> getUsers() {
        return new ArrayList<>(map.values());
    }

}
