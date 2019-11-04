package com.capgemini.go.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WISHLIST")
public class WishlistDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2165469246059692902L;

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "PRODUCT_ID", unique = true, nullable = false, length=20)
	private String productId;
	
	
	public WishlistDTO()
	{
		
	}
	
	public WishlistDTO(String productId, String userId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	@Override
	public String toString() {
		return "WishlistDTO [userId=" + userId +", productId=" + productId + "]";
	}
	
}


