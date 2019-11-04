package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Component;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.WishlistDTO;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.exception.WishlistException;



@Component
public interface WishlistDao {

	/*******************************************************************************************************
	 *- Function Name : addProductToWishlist 
	 *- Input Parameters : Product List
	 *- Return Type : boolean
	 *- Throws : RetailerException
	 *- Author : CAPGEMINI 
	 *- Creation Date : 21/9/2019
	 *- Description : To add products to Wishlist database
	 *@throws ConnectException 
	 *@throws SQLException 
	 ********************************************************************************************************/
	boolean addProductToWishlist(WishlistDTO wishlist) throws WishlistException,ConnectException;

	/*******************************************************************************************************
	 *- Function Name : RemoveProductFromWishlist 
	 *- Input Parameters : Product ID
	 *- Return Type : boolean
	 *- Throws : RetailerException
	 *- Author : CAPGEMINI 
	 *- Creation Date : 21/9/2019
	 *- Description : To remove products from Wishlist database
	 *@throws ConnectException 
	 *@throws SQLException 
	 ********************************************************************************************************/
	public boolean removeProductFromWishlist (WishlistDTO wishlist) throws WishlistException, ConnectException;
	
	/*******************************************************************************************************
	 *- Function Name : viewWishlist 
	 *- Input Parameters : user ID
	 *- Return Type : List
	 *- Throws : UserException
	 *- Author : CAPGEMINI 
	 *- Creation Date : 21/9/2019
	 *- Description : To view products in Wishlist database
	 *@throws UserException 
	 ********************************************************************************************************/
	List<ProductDTO> fetchfavproduct(String userId) throws UserException;
	
	}
