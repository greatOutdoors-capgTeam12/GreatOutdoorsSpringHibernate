package com.capgemini.go.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductException;

@Component
public interface ProductDao {

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : viewAllProducts - Input Parameters : - Return Type :
	 * List<ProductDTO> - Throws : ProductException - Author : AGNIBHA CHANDRA -
	 * Creation Date : 21/9/2019 - Description : to get all the product from the
	 * database
	 ********************************************************************************************************/
	List<ProductDTO> viewAllProducts() throws ProductException;

}
