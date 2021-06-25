package assignment.meterreadings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
@Table(name="TBL_METER_MEASUREMENTS")
public class MeterEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="date")
    private java.sql.Date date;

    @Column(name="value")
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "client_id"), name = "client_id")
    private ClientEntity clientEntity;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	
	public Long getClientId() {
		return clientEntity.getId();
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
    @Override
    public String toString() {
        return "MeterEntity [clientId=" + clientEntity.getId() + ", date=" + date + 
                ", value=" + value + "]";
    }

}
