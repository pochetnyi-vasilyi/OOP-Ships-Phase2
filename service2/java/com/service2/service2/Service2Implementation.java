package com.service2.service2;

import com.service2.exception.JsonFileNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class Service2Implementation implements Service2 {
    private static final int SERVICE_ONE_PORT_NUMBER = 8080;
    private static final int SERVICE_THREE_PORT_NUMBER = 8083;

    @Override
    public String getShips() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + SERVICE_ONE_PORT_NUMBER + "/serviceOne/generate";
        ResponseEntity<String> answer = restTemplate.getForEntity(url, String.class);
        String resourceName = ".\\src\\main\\resources\\json\\schedule.JSON";
        try (FileWriter writer = new FileWriter(resourceName)) {
            writer.write(answer.getBody());
            System.out.println("Respond from service 1 was written in JSON");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return answer.getBody();
    }

    @Override
    public String getShips(String str) {
        String fileName = ".\\src\\main\\resources\\json\\" + str + ".JSON";
        File file = new File(fileName);
        if (!file.exists()) {
            throw new JsonFileNotFoundException("File doesn't exist!");
        }
        try {
            return new String(Files.readAllBytes(file.toPath()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getReport() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + SERVICE_THREE_PORT_NUMBER + "/serviceThree/report";
        ResponseEntity<String> answer = restTemplate.getForEntity(url, String.class);
        String fileName = ".\\src\\main\\resources\\json\\report.JSON";
        try (FileWriter fWriter = new FileWriter(fileName)) {
            fWriter.write(answer.getBody());
            System.out.println("Service 3 was written in JSON");
        }
        catch (IOException e) {
            throw new JsonFileNotFoundException("No file was found with this name");
        }
        return answer.getBody();
    }
}
