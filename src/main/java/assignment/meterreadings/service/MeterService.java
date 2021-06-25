package assignment.meterreadings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assignment.meterreadings.model.MeterEntity;
import assignment.meterreadings.repository.MeterRepository;
 
@Service
public class MeterService {
     
    @Autowired
    MeterRepository repository;
     
    public List<MeterEntity> getAllMeters()
    {
        List<MeterEntity> meterList = repository.findAll();
         
        if(meterList.size() > 0) {
            return meterList;
        } else {
            return new ArrayList<MeterEntity>();
        }
    }
    

    public MeterEntity getMeterById(Long id){
        Optional<MeterEntity> client = repository.findById(id);
         
        if(client.isPresent()) {
            return client.get();
        } else {
            System.out.println("No employee record exist for given id");
            return new MeterEntity();
        }
    }

    
    public List<MeterEntity> getMetersForAClient()
    {
        List<MeterEntity> meterList = repository.findAll();
        
        if(meterList.size() > 0) {
            return meterList;
        } else {
            return new ArrayList<MeterEntity>();
        }
    }
    

    
}
