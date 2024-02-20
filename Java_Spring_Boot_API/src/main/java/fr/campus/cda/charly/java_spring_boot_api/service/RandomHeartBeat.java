package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.repository.HeartbeatSensorInterface;
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
