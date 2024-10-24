package com.valtech.mobility.controller;


import com.valtech.mobility.model.LocationDetail;
import com.valtech.mobility.service.CreateLocationConsumer;
import com.valtech.mobility.service.CreateLocationProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("/location")
@RestController
public class LocationController {

    private final CreateLocationProducer createLocationProducer;

    public LocationController(CreateLocationProducer createLocationProducer) {
        this.createLocationProducer = createLocationProducer;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody LocationDetail locationDetail) throws ExecutionException, InterruptedException {
        createLocationProducer.sendCreateLocationEvent(locationDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
