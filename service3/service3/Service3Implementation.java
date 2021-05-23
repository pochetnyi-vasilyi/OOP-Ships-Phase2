package com.service3.service3;

import com.service3.entity.Ship;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class Service3Implementation implements Service3 {
    private static final int SERVICE_TWO_PORT_NUMBER = 8082;

    @Override
    public List<Ship> getList ()   {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + SERVICE_TWO_PORT_NUMBER + "/serviceTwo/get/schedule";
        String responseEntity = restTemplate.getForObject(url, String.class);
        //JSONParser jsonParser = new JSONParser();
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        String str = responseEntity;

//        System.out.println(str);

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
            JSONArray shipsArray = (JSONArray) jsonObject.get("Ships");
            List<Ship> ships = new ArrayList<>();
            for (Object obj : shipsArray) {
                JSONObject ship = (JSONObject) obj;
                String name = (String) ship.get("Name of ship");
                String time = (String) ship.get("Arrival date");
                Timestamp arrivalDate = Timestamp.valueOf(time);
                String type = (String) ship.get("Type of cargo");
                int weight = Integer.parseInt(String.valueOf(ship.get("Weight of cargo")));
                ships.add(new Ship(name, arrivalDate, new Ship.Cargo(type, weight)));
            }
            return ships;
        }
        catch(Exception e) {
            System.out.println("File parsing error " + e);
        }
        return null;
    }
}
