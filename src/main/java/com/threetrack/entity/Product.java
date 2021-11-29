package com.threetrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "se_product")
public class Product extends GenericEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_product_id")
	protected Integer productId;

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

	public Product(){
	}

	public Product(Integer productId){
		this.productId = productId;
	}
}