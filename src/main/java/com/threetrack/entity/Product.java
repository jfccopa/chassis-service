package com.threetrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "se_product")
public class Product extends GenericEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_product_id")
	protected Integer id;

	@Column(name = "v_name")
	protected String name;

	@Column(name = "v_description")
	protected String description;

	@Column(name = "v_serial_number")
	protected String serialNumber;

	@Column(name = "i_quantity")
	protected Integer quantity;

	@Column(name = "d_price")
	protected Double price;

	@Column(name = "d_weight")
	protected Double weight;

	@Column(name = "d_volume")
	protected Double volume;

	@Column(name = "c_state")
	protected Character state;

	@Column(name = "i_organization_id")
	protected Integer organizationId;

	public Product(){}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer productId) {
		this.id = productId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getSerialNumber(){
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber){
		this.serialNumber = serialNumber;
	}

	public Integer getQuantity(){
		return this.quantity;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public Double getPrice(){
		return this.price;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getWeight(){
		return this.weight;
	}

	public void setWeight(Double weight){
		this.weight = weight;
	}

	public Double getVolume(){
		return this.volume;
	}

	public void setVolume(Double volume){
		this.volume = volume;
	}

	public Character getState(){
		return this.state;
	}

	public void setState(Character state){
		this.state = state;
	}

	public Integer getOrganizationId(){
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId){
		this.organizationId = organizationId;
	}

}