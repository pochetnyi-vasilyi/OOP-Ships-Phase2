package com.service3.service3;

import com.service3.entity.Ship;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/serviceThree")
public class Service3Controller {

    private Service3 serviceThree;

    @Autowired
    public void setServiceThree(Service3 serviceThree) {
        this.serviceThree = serviceThree;
    }

    @GetMapping("/report")
    public String getReport() throws InterruptedException, ParseException {
        Unloading unloading = new Unloading(serviceThree.getList());
        List<Ship> ships = unloading.getList();
        JSONObject report = new JSONObject();
        report.put("Fine", unloading.getFine());
        report.put("Mean delay", unloading.getMeanDelay());
        report.put("Max Delay", unloading.getMaxDelay());
        report.put("Number of container cranes", unloading.getNumberOfContainerCranes());
        report.put("Number of liquid cranes", unloading.getNumberOfLiquidCranes());
        report.put("Number of bulk cranes", unloading.getNumberOfBulkCranes());
        report.put("Number of ships", unloading.getNumberOfShips());
        report.put("Waiting time", unloading.getWaitTime());
        JSONArray shipsArray = new JSONArray();
        for (Ship ship : ships) {
            JSONObject json = new JSONObject();
            json.put("Name of ship", ship.getName());
            json.put("Estimated arrival date", ship.getEstimatedBeginTime().toString());
            json.put("Departure date", ship.getEstimatedEndTime().toString());
            json.put("Type of cargo", ship.getNameType());
            json.put("Weight of cargo", ship.getCargoWeight());
            json.put("Real arrival date", ship.getArrivalTime().toString());
            json.put("Begin of unloading time", ship.getRealBeginTime().toString());
            json.put("End of unloading time", ship.getRealEndTime().toString());
            json.put("Waiting time", ship.getWaitString());
            json.put("Fine", ship.getFine());
            shipsArray.add(json);
        }
        JSONObject out = new JSONObject();
        out.put("Report", report);
        out.put("Ships", shipsArray);
        return out.toString();
    }
}
