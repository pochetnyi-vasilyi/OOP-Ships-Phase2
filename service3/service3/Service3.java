package com.service3.service3;

import com.service3.entity.Ship;

import java.text.ParseException;
import java.util.List;

public interface Service3 {
    List<Ship> getList() throws ParseException;
}
