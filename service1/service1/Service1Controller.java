package com.service1.service1;

import com.service1.entity.Ship;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/serviceOne")
public class Service1Controller {

    private Service1 service1;

    @Autowired
    public void setServiceOne(Service1 service) {
        this.service1 = service;
    }

    @GetMapping("/generate")
    public String list() {
        List<Ship> ships = service1.getList();
        JSONArray shipArray = new JSONArray();
        for (Ship ship : ships) {
            JSONObject schedule = new JSONObject();
            schedule.put("Name of ship", ship.getName());
            schedule.put("Arrival date", ship.getEstimatedBeginTime().toString());
            schedule.put("Departure date", ship.getEstimatedEndTime().toString());
            schedule.put("Type of cargo", ship.getNameType());
            schedule.put("Weight of cargo", ship.getCargoWeight());
            shipArray.add(schedule);
        }
        JSONObject out = new JSONObject();
        out.put("Ships", shipArray);
        return out.toString();
    }
}
