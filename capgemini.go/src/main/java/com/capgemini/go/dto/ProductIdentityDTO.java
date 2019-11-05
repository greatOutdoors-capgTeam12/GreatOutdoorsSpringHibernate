package com.capgemini.go.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductIdentityDTO implements Serializable{

	private static final long serialVersionUID = -2593500634779412169L;

	@Column(name="PRODUCT_ID" , nullable = false, length=20)
	private String productId;
	
	
	@Column(name="PRODUCT_UIN", unique = true, nullable = false, length=20)
	private String productUIN;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductUIN() {
		return productUIN;
	}

	public void setProductUIN(String productUIN) {
		this.productUIN = productUIN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productUIN == null) ? 0 : productUIN.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIdentityDTO other = (ProductIdentityDTO) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productUIN == null) {
			if (other.productUIN != null)
				return false;
		} else if (!productUIN.equals(other.productUIN))
			return false;
		return true;
	}
}
