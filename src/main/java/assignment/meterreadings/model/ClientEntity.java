package assignment.meterreadings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name="TBL_CLIENTS")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="address")
    private String address;
    
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeterEntity> meters;
        
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    @Override
    public String toString() {
        return "ClientEntity [id=" + id + ", name=" + name + 
                ", address=" + address + "]";
    }
}
