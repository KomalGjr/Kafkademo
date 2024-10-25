package com.valtech.mobility.controller;


import com.valtech.mobility.model.FuelConsumptionDetails;
import com.valtech.mobility.service.FuelConsumptionProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("/fuelconsumption")
@RestController
public class FuelConsumptionController {

    private final FuelConsumptionProducer fuelConsumptionProducer;

    public FuelConsumptionController(FuelConsumptionProducer fuelConsumptionProducer) {
        this.fuelConsumptionProducer = fuelConsumptionProducer;
    }

    @PostMapping
    public ResponseEntity<?> createFuelConsumption(@RequestBody FuelConsumptionDetails fuelConsumptionDetails) throws ExecutionException, InterruptedException {
        fuelConsumptionProducer.sendFuelConsumptionEvent(fuelConsumptionDetails);
//        for (int i = 50; i <=100 ; i++) {
//            FuelConsumptionDetails details = new FuelConsumptionDetails();
//            details.setCarId("car" + (i % 3)); // Distributing among 3 cars
//            details.setFuelUsed(Math.random() * 10); // Random fuel used
//            fuelConsumptionProducer.sendFuelConsumptionEvent(details);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
