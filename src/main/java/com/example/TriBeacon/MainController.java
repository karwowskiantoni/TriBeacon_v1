package com.example.TriBeacon;

import com.example.TriBeacon.Model.Users;
import org.geotools.geojson.geom.GeometryJSON;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    Users users = new Users();

    @PostMapping("/subscribe")
    public boolean subscribe(@RequestParam String name) {
        return users.add(name);
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
        return users.calculatePositions().stream().map(polygon -> new GeometryJSON().toString(polygon)).collect(Collectors.toList());
    }
}
