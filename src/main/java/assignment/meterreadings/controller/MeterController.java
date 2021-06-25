package assignment.meterreadings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.meterreadings.model.MeterEntity;
import assignment.meterreadings.service.MeterService;
 
@RestController
@RequestMapping("/meters")
public class MeterController {
    @Autowired
    MeterService service;
 
    /* method for listing all entries in the meter table that contains energy measurements */
    @GetMapping
    public ResponseEntity<List<MeterEntity>> getAllMeters() {
        List<MeterEntity> list = service.getAllMeters();
 
        return new ResponseEntity<List<MeterEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    /* method for getting meter by id */
    @GetMapping("/{id}")
    public ResponseEntity<MeterEntity> getMeterById(@PathVariable("id") Long id){
        MeterEntity entity = service.getMeterById(id);
 
        return new ResponseEntity<MeterEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    

}