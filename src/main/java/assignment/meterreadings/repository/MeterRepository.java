package assignment.meterreadings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import assignment.meterreadings.model.MeterEntity;

@Repository
public interface MeterRepository
        extends JpaRepository<MeterEntity, Long> {
 	  
	  @Query("select m from MeterEntity m where m.clientEntity.id = ?1")
	  List<MeterEntity> findAllMeterRecordingsForClient(Long clientId);
	  
	  /* calculates aggregate consumption for a client on a specific year */
	  @Query("select sum(m.value) from MeterEntity m where m.clientEntity.id = ?1 and year(m.date) = ?2")
	  int getEnergyConsumption(Long clientId, int year);
}
