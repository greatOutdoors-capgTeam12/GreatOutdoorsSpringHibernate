package com.capgemini.go.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductException;

@Service(value = "favouritesService")
public class WishlistServiceImpl implements WishlistService {

//	@Autowired
//	private FavouritesDao favouritesDao;
//
//	public ProductDao getProductDao() {
//		return productDao;
//	}
//
//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : viewAllProducts - Input Parameters : - Return Type :
	 * List<ProductDTO> - Throws : ProductException - Author : AGNIBHA CHANDRA -
	 * Creation Date : 21/9/2019 - Description : to get all the product from the
	 * database
	 ********************************************************************************************************/

	public List<ProductDTO> viewAllProducts() throws ProductException {

		//return productDao.viewAllProducts();
		return null;
	}

}