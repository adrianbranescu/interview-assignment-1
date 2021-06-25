package assignment.meterreadings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assignment.meterreadings.model.ClientEntity;
 
@Repository
public interface ClientRepository
        extends JpaRepository<ClientEntity, Long> {
 
}
