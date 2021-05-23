package com.service2.service2;

import com.service2.exception.JsonFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/serviceTwo")
public class Service2Controller {

    private Service2 serviceTwo;

    @Autowired
    public void setService2(Service2 serviceTwo) {
        this.serviceTwo = serviceTwo;
    }

    @GetMapping("/get")
    public String getList() {
        return serviceTwo.getShips();
    }

    @GetMapping("/get/{filename}")
    public String getList(@PathVariable("filename") String fileName) {
        try {
            String str = serviceTwo.getShips(fileName);
            if (str.isEmpty())
            {
                return "File is empty";
            }
            return str;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
        }
    }

    @PostMapping("/getReport")
    public String getReport(@RequestBody String string) {
        try {
            if (string.equals("\"report\""))
            {
                return serviceTwo.getReport();
            }
            else {
                FileWriter writer = new FileWriter(".\\src\\main\\resources\\report.JSON");
                writer.write("");
                return null;
            }
        }
        catch (IOException | JsonFileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
        }
    }
}
