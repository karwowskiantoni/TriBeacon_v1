package com.example.TriBeacon;

import com.example.TriBeacon.Model.User;
import com.example.TriBeacon.Model.Users;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    Users users = new Users();

    @PostMapping("/user/subscribe")
    public boolean subscribe(@RequestParam String name) {
        return users.add(name);
    }

    @PostMapping("/user/update")
    public void update(@RequestBody UserDto userDto) {
        users.update(userDto.toUser());
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return users.getUsers().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }
}
