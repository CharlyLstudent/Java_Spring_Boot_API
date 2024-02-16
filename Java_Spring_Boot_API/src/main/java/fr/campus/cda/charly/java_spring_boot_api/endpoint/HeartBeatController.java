package fr.campus.cda.charly.java_spring_boot_api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {

    @Autowired
    private HeartbeatSensorInterface heartbeatSensor;
    @GetMapping("/heartbeat")
    public int getHeartBeat() {
        return heartbeatSensor.get();
    }
}
