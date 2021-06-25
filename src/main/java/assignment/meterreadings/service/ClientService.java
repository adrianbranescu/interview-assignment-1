package assignment.meterreadings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assignment.meterreadings.model.ClientEntity;
import assignment.meterreadings.model.MeterEntity;
import assignment.meterreadings.repository.ClientRepository;
import assignment.meterreadings.repository.MeterRepository;
 
@Service
public class ClientService {
     
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    MeterRepository meterRepository;
     
    public List<ClientEntity> getAllClients()
    {
        List<ClientEntity> clientList = clientRepository.findAll();
        
         
        if(clientList.size() > 0) {
        
            return clientList;
        } else {
        
            return new ArrayList<ClientEntity>();
        }
    }
    

    public ClientEntity getClientById(Long id){
        Optional<ClientEntity> client = clientRepository.findById(id);
         
        if(client.isPresent()) {
            return client.get();
        } else {
            return new ClientEntity();
        }
    }

    public List<MeterEntity> getClientMeasurements(Long id){
    	List<MeterEntity> meterList = meterRepository.findAllMeterRecordingsForClient(id);

        if(meterList.size() > 0) {
            
            return meterList;
        } else {
        
            return new ArrayList<MeterEntity>();
        }
    }

    
    public int getClientConsumption(Long id, int year){
    	
    	return meterRepository.getEnergyConsumption(id,year);

    }
    
    public void saveClient(ClientEntity client){
    	clientRepository.save(client);
    }
    
    public void deleteById(Long id){
    	clientRepository.deleteById(id);
    }
}
