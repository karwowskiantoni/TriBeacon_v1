package com.example.TriBeacon;

import com.example.TriBeacon.Model.User;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

public class PositionDTO {
    private UserDto userDto;
    private Point center;
    private MultiPolygon polygon;

    public PositionDTO() {
    }
    public PositionDTO(User user, Point center, MultiPolygon polygon) {
        this.userDto = UserDto.fromUser(user);
        this.center = center;
        this.polygon = polygon;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public MultiPolygon getPolygon() {
        return polygon;
    }

    public void setPolygon(MultiPolygon polygon) {
        this.polygon = polygon;
    }
}
