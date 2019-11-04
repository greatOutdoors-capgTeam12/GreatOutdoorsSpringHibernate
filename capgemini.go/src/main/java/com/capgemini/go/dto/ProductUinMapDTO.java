package com.capgemini.go.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

class ProductIdentityEntity {
	@Column(name = "PRODUCT_ID", unique = true, nullable = false, length = 20)
	private String productId;

	@Column(name = "PRODUCT_UIN", unique = true, nullable = false, length = 20)
	private String productUIN;

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductUin(String productUin) {
		this.productUIN = productUin;
	}

	public String getProductId() {
		return this.productId;
	}

	public String getProductUin() {
		return this.productUIN;
	}
}

@Entity
@Table(name = "PRODUCT_UIN_MAP")
public class ProductUinMapDTO {
	@EmbeddedId
	private ProductIdentityEntity productUniqueIdentity;

	@Column(name = "PRODUCT_IS_PRESENT", nullable = false, unique = false)
	private boolean productIsPresent;

	public ProductUinMapDTO () {
		
	}
	
	public ProductUinMapDTO (String productId, String productUin, boolean productIsPresent) {
		this.productUniqueIdentity.setProductId(productId);
		this.productUniqueIdentity.setProductUin(productUin);
		this.productIsPresent = productIsPresent;
	}

	public void setProductId (String productId) {
		this.productUniqueIdentity.setProductId(productId);
	}

	public void setProductUin (String productUin) {
		this.productUniqueIdentity.setProductUin(productUin);
	}
	
	public void setProductIsPresent (boolean productIsPresent) {
		this.productIsPresent = productIsPresent;
	}
	
	public String getProductId () {
		return this.productUniqueIdentity.getProductId();
	}
	
	public String getProductUin () {
		return this.productUniqueIdentity.getProductUin();
	}
	
	public boolean isProductIsPresent() {
		return productIsPresent;
	}
}
