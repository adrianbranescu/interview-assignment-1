package assignment.meterreadings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.meterreadings.model.ClientEntity;
import assignment.meterreadings.service.ClientService;

import assignment.meterreadings.model.MeterEntity;
import assignment.meterreadings.service.MeterService;

@RestController
@RequestMapping("/clients")
public class ClientController
{
    @Autowired
    ClientService clientService;
    
    @Autowired
    MeterService meterService;
 
    /* method used for retrieving all clients */
    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> list = clientService.getAllClients();
 
        return new ResponseEntity<List<ClientEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    /* method for getting client by id */
    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") Long id){
        ClientEntity entity = clientService.getClientById(id);
 
        return new ResponseEntity<ClientEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /* method that returns a list containing all meter recordings for a specific client */
    @GetMapping("/measurements/{id}")
    public ResponseEntity<List<MeterEntity>> getClientMeasurements(@PathVariable("id") Long id) {
        List<MeterEntity> list = clientService.getClientMeasurements(id);
 
        return new ResponseEntity<List<MeterEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    

    /* method that returns the sum of all meter recordings for a specific client on a specific year */
    @GetMapping("/report/consumption/{id}/{year}")
    public ResponseEntity<String> getClientConsumptionOverAYear(@PathVariable("id") Long id, @PathVariable("year") int year) {
    	
    	String responseMessage="";
    	
    	try{
            int sum = clientService.getClientConsumption(id, year);
            responseMessage = "For year "+year+", client energy consumption is "+sum;
    	}
    	catch(Exception e){
    		responseMessage = "An error has occured";
    		return new ResponseEntity<String>(responseMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        
        return new ResponseEntity<String>(responseMessage, new HttpHeaders(), HttpStatus.OK);
    }

    /* method for adding a client */    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addClient(@RequestBody ClientEntity client) {
    	clientService.saveClient(client);
    }

    /* method for updating a client */
    @PutMapping("/{id}")
    public void updateClient(@PathVariable(value = "id") Long clientId, @RequestBody ClientEntity clientDetails) {
    	
    	ClientEntity client = clientService.getClientById(clientId);
    	client.setName(clientDetails.getName());
    	client.setAddress(clientDetails.getAddress());
    	clientService.saveClient(client);
    }
    
    /* method for deleting a client */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
    	clientService.deleteById(id);
    }

    
}
