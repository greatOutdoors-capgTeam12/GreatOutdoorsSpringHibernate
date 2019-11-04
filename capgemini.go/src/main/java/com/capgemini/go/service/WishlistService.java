package com.capgemini.go.service;

import java.util.List;

	import com.capgemini.go.dto.ProductDTO;
	import com.capgemini.go.exception.ProductException;

public interface WishlistService {

// ------------------------ GreatOutdoor Application --------------------------
/*******************************************************************************************************
* - Function Name : viewAllProducts - Input Parameters : - Return Type :
* List<ProductDTO> - Throws : ProductException - Author : AGNIBHA CHANDRA -
* Creation Date : 21/9/2019 - Description : to get all the product from the
* database
********************************************************************************************************/
    List<ProductDTO> viewAllProducts() throws ProductException;
	}
