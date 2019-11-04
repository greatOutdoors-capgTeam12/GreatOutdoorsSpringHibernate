package com.capgemini.go.dao;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.RetailerException;

public interface OrderAndCartDao {

	/*******************************************************************************************************
	 * Function Name : addItemToCart <br>
	 * Input Parameters : CartDTO (retailerId, productId, quantity) <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to add item to a cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean addItemToCart (CartDTO cartItem) throws RetailerException;
	
	/*******************************************************************************************************
	 * Function Name : removeItemFromCart <br>
	 * Input Parameters : CartDTO (retailerId, productId)<br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to remove a item from cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean removeItemFromCart (CartDTO cartItem) throws RetailerException;

	/*******************************************************************************************************
	 * Function Name : increaseItemQuantity <br>
	 * Input Parameters : CartDTO (retailerId, productId, quantity(absolute value|not offset))<br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to increase item quantity in cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean updateItemQuantity (CartDTO cartItem) throws RetailerException;
	
	/*******************************************************************************************************
	 * Function Name : placeOrder <br>
	 * Input Parameters : OrderDTO(orderId, userId, addressId, orderInitiationTime) <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 21/9/2019 <br>
	 * Description : to register an order for items in the cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	boolean registerOrder (OrderDTO order) throws RetailerException;
}
