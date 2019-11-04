package com.capgemini.go.dao;

import java.net.ConnectException;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.RetailerException;

public interface OrderAndCartDao {

	/*******************************************************************************************************
	 * Function Name : addItemToCart 
	 * Input Parameters :  CartDTO
	 * Return Type : boolean
	 * Throws : RetailerException 
	 * Author : Agnibha, Azhar 
	 * Creation Date : 27/9/2019
	 * Description : to add item to a cart
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean addItemToCart(CartDTO cartItem) throws RetailerException,ConnectException;
	

	/*******************************************************************************************************
	 * Function Name : placeOrder 
	 * Input Parameters :product
	 * Return Type :boolean 
	 * Throws :  RetailerException 
	 * Author : Agnibha , Azhar 
	 * Creation Date : 21/9/2019 
	 * Description : to place order for items in the cart
	 *  
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean placeOrder(OrderDTO order) throws RetailerException,ConnectException;
}
