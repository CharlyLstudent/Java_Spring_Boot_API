package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.repository.HeartbeatSensorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HeartBeatController {

    @Autowired
    private HeartbeatSensorInterface heartbeatSensor;

    @Autowired
    private GameCatalogInterface gameCatalog;

    @GetMapping("/heartbeat")
    public int getHeartBeat() {
        return heartbeatSensor.get();
    }

}
