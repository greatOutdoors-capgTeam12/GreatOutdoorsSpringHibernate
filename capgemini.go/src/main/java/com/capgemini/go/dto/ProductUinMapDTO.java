package com.capgemini.go.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

class ProductIdentityEntity implements Serializable {
	private static final long serialVersionUID = 2060177406245482526L;

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
		ProductIdentityEntity other = (ProductIdentityEntity) obj;
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

@Entity
@Table(name = "PRODUCT_UIN_MAP")
public class ProductUinMapDTO implements Serializable {
	private static final long serialVersionUID = -5420007247668476033L;

	@EmbeddedId
	private ProductIdentityEntity productUniqueIdentity;

	@Column(name = "PRODUCT_IS_PRESENT", nullable = false, unique = false)
	private boolean productIsPresent;

	public ProductUinMapDTO() {

	}

	public ProductUinMapDTO(String productId, String productUin, boolean productIsPresent) {
		this.productUniqueIdentity.setProductId(productId);
		this.productUniqueIdentity.setProductUin(productUin);
		this.productIsPresent = productIsPresent;
	}

	public void setProductId(String productId) {
		this.productUniqueIdentity.setProductId(productId);
	}

	public void setProductUin(String productUin) {
		this.productUniqueIdentity.setProductUin(productUin);
	}

	public void setProductIsPresent(boolean productIsPresent) {
		this.productIsPresent = productIsPresent;
	}

	public String getProductId() {
		return this.productUniqueIdentity.getProductId();
	}

	public String getProductUin() {
		return this.productUniqueIdentity.getProductUin();
	}

	public boolean isProductIsPresent() {
		return productIsPresent;
	}
}
