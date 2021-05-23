package com.service1.service1;

import com.service1.entity.Ship;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service1Implementation implements Service1 {

    private static final int NUMBER_OF_SHIPS = 100;

    private final ShipGenerator shipGenerator = new ShipGenerator(NUMBER_OF_SHIPS);

    @Override
    public List<Ship> getList () {
        return shipGenerator.getShips();
    }
}
