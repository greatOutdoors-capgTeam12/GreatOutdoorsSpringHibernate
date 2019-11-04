package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.WishlistDTO;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.ProductException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.exception.WishlistException;

@Repository(value = "wishlistDao")
public class WishlistDaoImpl implements WishlistDao {

	// this class is wired with the sessionFactory to do some operation in the
	// database

	
	@Autowired	
	private SessionFactory sessionFactory;
	// this will create one sessionFactory for this class
	// there is only one sessionFactory should be created for the applications
	// we can create multiple sessions for a sessionFactory
	// each session can do some functions

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ------------------------ GreatOutdoor Application --------------------------
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
	@Override
	public boolean addProductToWishlist(WishlistDTO wishlist) throws WishlistException, ConnectException {
		// TODO Auto-generated method stub
		return false;
	}
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
	@Override
	public boolean removeProductFromWishlist(WishlistDTO wishlist) throws WishlistException, ConnectException {
		// TODO Auto-generated method stub
		return false;
	}
	
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
	@Override
	public List<ProductDTO> fetchfavproduct(String userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
