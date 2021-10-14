package com.example.TriBeacon;

import com.example.TriBeacon.Model.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    Board board = new Board();
    @GetMapping("/subscribe")
    public String subscribe(@RequestParam String bluetoothId) {
        return "subscribed " + bluetoothId;
    }

    @GetMapping("/update")
    public void update(@RequestParam String bluetoothId) {

    }
}
