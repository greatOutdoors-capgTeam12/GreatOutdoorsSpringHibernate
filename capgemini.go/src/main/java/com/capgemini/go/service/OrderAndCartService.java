package com.capgemini.go.service;

import com.capgemini.go.exception.RetailerException;

public interface OrderAndCartService {
	/*******************************************************************************************************
	 * Function Name : addItemToCart <br>
	 * Input Parameters :  <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to add item to a cart <br>
	 ********************************************************************************************************/
	boolean addItemToCart () throws RetailerException;
	
	/*******************************************************************************************************
	 * Function Name : removeItemFromCart <br>
	 * Input Parameters :  <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to add item to a cart <br>
	 ********************************************************************************************************/
	boolean removeItemFromCart () throws RetailerException;
	
	/*******************************************************************************************************
	 * Function Name : placeOrder <br>
	 * Input Parameters :  <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to place the order of items <br>
	 ********************************************************************************************************/
	boolean placeOrder () throws RetailerException;
}
