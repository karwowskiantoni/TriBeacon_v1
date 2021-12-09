package com.example.TriBeacon;

import com.example.TriBeacon.Model.Users;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.MultiPolygon;
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

  @GetMapping("/position")
  public String getPosition(@RequestParam String name) {
    return new GeometryJSON().toString(users.calculatePosition(name));
  }

  @GetMapping("/positions")
  public List<String> getPositions() {
    return users.calculatePositions().stream()
        .map(polygon -> new GeometryJSON().toString(polygon))
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
