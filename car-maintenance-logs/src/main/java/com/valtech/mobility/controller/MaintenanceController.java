package com.valtech.mobility.controller;


import com.valtech.mobility.model.MaintenanceDetails;
import com.valtech.mobility.service.MaintenanceProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("/maintenance")
@RestController
public class MaintenanceController {

    private final MaintenanceProducer maintenanceProducer;

    public MaintenanceController(MaintenanceProducer maintenanceProducer) {
        this.maintenanceProducer = maintenanceProducer;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody MaintenanceDetails maintenanceDetails) throws ExecutionException, InterruptedException {
        maintenanceProducer.sendMaintenanceEvent(maintenanceDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
