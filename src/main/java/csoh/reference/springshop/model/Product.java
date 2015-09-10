package csoh.reference.springshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "\"com.sap.xs2.samples::AddressBook.Book\"")
@Table(name = "\"PRODUCT\"")
public class Product {

	@Id
	@Column(name = "\"id\"", nullable = false)
	private long id;
	
	@Column(name = "\"name\"", length = 100)
	private String name;

	private double price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	

}
