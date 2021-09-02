package com.threetrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "se_product")
public class Product {

	@Id
	@Column(name = "i_product_id")
	protected Integer productId;

	@Column(name = "v_name")
	protected String name;

	public Product() {}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
