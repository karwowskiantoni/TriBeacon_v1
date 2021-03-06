package com.example.TriBeacon.api;

import com.example.TriBeacon.model.Users;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MainController {
    Users users = new Users();

    @PostMapping("/subscribe/beacon")
    public void subscribeBeacon(
            @RequestParam String name, @RequestParam double x, @RequestParam double y) {
        users.addBeacon(name, x, y);
    }

    @PostMapping("/subscribe/user")
    public void subscribeUser(@RequestParam String name) {
        users.addUser(name);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserDto userDto) {
        users.update(userDto.getName(), userDto.getConnections());
    }

    @GetMapping("/positions")
    public List<PositionDTO> getPositions(@RequestParam boolean reduceBeacons, int reduceUsers) {
        return users.calculatePositions(reduceBeacons, reduceUsers).stream().filter(position -> !position.polygon().isEmpty())
                .map(position -> new PositionDTO(position.user(), position.polygon().getCentroid(), position.polygon()))
                .collect(Collectors.toList());
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return users.getUsers().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @GetMapping("/delete")
    public void deleteAll() {
        users.deleteAll();
    }
}
