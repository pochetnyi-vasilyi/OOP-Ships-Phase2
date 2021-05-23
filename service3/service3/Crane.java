package com.service3.service3;

import com.service3.entity.Ship;

import java.sql.Timestamp;
import java.util.Calendar;

class Crane {

    private final int powerPerHour;

    public Crane (Ship.Cargo.cargoType type) {
        powerPerHour = type.getHour();
    }

    public void unloading (Ship ship) {
        Timestamp ts = ship.getRealBeginTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ts.getTime());
        int weight = ship.getCargoWeight();
        while (weight != 0) {
            calendar.add(Calendar.HOUR, powerPerHour);
            weight--;
        }
        int minutes = ship.getDelay();
        calendar.add(Calendar.MINUTE, minutes);
        ts = new Timestamp(calendar.getTime().getTime());
        ship.setRealEndTime(ts);
    }
}
