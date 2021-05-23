package com.service2.service2;

import com.service2.entity.Ship;
import com.service2.exception.JsonFileNotFoundException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.sql.Timestamp;
import java.util.*;

public class JsonCreator {

    private List<Ship> ships;

    public List<Ship> readService1(String string) throws JsonFileNotFoundException {
        ships = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(string);
            JSONArray shipArray = (JSONArray) jsonObject.get("Ships");
            for (Object obj : shipArray) {
                JSONObject ship = (JSONObject) obj;
                String name = (String) ship.get("Name of ship");
                String arrDate = (String) ship.get("Arrival date");
                Timestamp arrivalDate = Timestamp.valueOf(arrDate);
                String type = (String) ship.get("Type of cargo");
                int number = Integer.parseInt(String.valueOf(ship.get("Weight of cargo")));
                ships.add(new Ship(name, arrivalDate, new Ship.Cargo(type, number)));
            }
            System.out.println("Service 1 was read");
        }
        catch (ParseException e) {
            throw new JsonFileNotFoundException("File not found!");
        }
        return ships;
    }

    public void write() throws InputMismatchException {
        while (true) {
            System.out.println("Add new ship? (Y/N only)\n");
            Scanner in = new Scanner(System.in);
            String answer = in.next();

            while (!answer.equals("Y") && !answer.equals("N")) {
                System.out.println("Add new ship? (Y/N only)\n");
                answer = in.next();
            }
            if (answer.equals("N")) {
                return;
            }
            System.out.print("Enter name of ship: ");
            answer = in.next();
            while (answer.isEmpty()) {
                System.out.print("Name must not be empty!");
                answer = in.next();
            }
            System.out.print("Enter type of cargo (B for bulk, L for liquid, C for container): ");
            String type = in.next();
            while (!type.equals("B") && !type.equals("L") && !type.equals("C")) {
                System.out.print("Enter type of cargo (B for bulk, L for liquid, C for container): ");
                type = in.next();
            }

            switch (type) {
                case "B" -> type = "bulk";
                case "L" -> type = "liquid";
                case "C" -> type = "container";
            }

            System.out.print("Enter weight of cargo: ");
            int number = in.nextInt();
            while (number < 1) {
                System.out.println("Weight must be more than 0!");
                number = in.nextInt();
            }

            System.out.print("Enter the day of begin: ");
            int day = in.nextInt();
            while ((day < 1) || (day > 30)) {
                System.out.println("Day must be in [1; 30] range!");
                day = in.nextInt();
            }
            System.out.print("Enter the hour of begin: ");
            int hour = in.nextInt();
            while ((hour < 0) || (hour > 23)) {
                System.out.println("Hour must be in [0; 23] range!");
                hour = in.nextInt();
            }
            System.out.print("Enter the minute of begin: ");
            int minute = in.nextInt();
            while ((minute < 0) || (minute > 60)) {
                System.out.println("Minute must be in [0; 59] range!");
                minute = in.nextInt();
            }
            int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
            int month = GregorianCalendar.getInstance().get(Calendar.MONTH) + 1;
            String time = "" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00.000";
            Timestamp timestamp = Timestamp.valueOf(time);
            Ship ship = new Ship(answer, timestamp, new Ship.Cargo(type, number));
            ships.add(ship);
        }
    }
}
