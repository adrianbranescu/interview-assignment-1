package assignment.meterreadings;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

import assignment.meterreadings.controller.ClientController;
import assignment.meterreadings.model.ClientEntity;

import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MeterreadingsApplicationTests {

	@Autowired
	private ClientController clientController;
	
	@Test
	void contextLoads() {
		assertThat(clientController).isNotNull();
	}

	@Test
	void testReadClient(){
		/* table is initially populated with test values; checking one such entry */
		ResponseEntity<ClientEntity> client = clientController.getClientById(new Long("1"));
		assertThat(client.getBody().getName().equals("Client1"));
		assertThat(client.getBody().getAddress().equals("Address1"));
	}
	
	@Test
	void testAddClient(){
		
		/* we create a client entity, add it to the table and then we read it back from the table */
		
		ClientEntity newClient = new ClientEntity();
		newClient.setName("Adrian");
		newClient.setAddress("Adrian's address");
        
		clientController.addClient(newClient); 

		ResponseEntity<ClientEntity> client = clientController.getClientById(new Long("1"));
		assertThat(client.getBody().getName().equals("Adrian"));
		assertThat(client.getBody().getAddress().equals("Adrian's address"));
	}

}
