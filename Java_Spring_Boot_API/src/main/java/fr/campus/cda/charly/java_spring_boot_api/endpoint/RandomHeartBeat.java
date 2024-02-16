package fr.campus.cda.charly.java_spring_boot_api.endpoint;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartBeat implements HeartbeatSensorInterface {

    @Override
    public int get() {
        Random randomNumbers = new Random();
        return randomNumbers.nextInt(40, 230);
    }
}
