package com.capgemini.go.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderProductMapDTO;

@Component
public interface SalesRepresentativeDao {

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderDetails(String orderId) 
	 * - Input Parameters : orderId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if orderId exists and also getting order details
	 ********************************************************************************************************/
	String getOrderDetails(String orderId) throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkSalesRepId(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : boolean
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if userId exists
	 ********************************************************************************************************/
	boolean checkSalesRepId(String userId) throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkDispatchStatusForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : boolean 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Check if order is dispatched
	 ********************************************************************************************************/
	boolean checkDispatchStatusForCancelling(String orderId) throws Exception;
	
	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderProductMapForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : List<OrderReturnEntity>
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : To get a list of type OrderReturnEntity
	 ********************************************************************************************************/
	List<OrderProductMapDTO> getOrderProductMapForCancelling(String orderId)  throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/**************************************************************************************************************
	 * - Function Name : cancelOrder(OrderCancelEntity orderCancel)
	 * - Input Parameters : OrderCancelEntity orderCancel 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table and updating OrderReturnEntity  after canceling the product
	 **************************************************************************************************************/
	String cancelOrder(OrderCancelDTO oc) throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getProductQuantityOrdered(String orderId, String productId)
	 * - Input Parameters : orderId, productId
	 * - Return Type : int 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return the quantity of product ordered
	 ********************************************************************************************************/
	int getProductQuantityOrdered(String orderId, String productId) throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelProduct(String orderId, String productId, int productQty, int quantity) 
	 * - Input Parameters : orderId, productId, productQty, quantity 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Updating the OrderReturnEntity after canceling the product
	 ********************************************************************************************************/
	String cancelProduct(String orderId, String productId, int productQtyOrdered, int quantity) throws Exception;

	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************************
	 * - Function Name : updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int quantity,
			String userId) 
	 * - Input Parameters : orderId, productId, productQtyOrdered, quantity, userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table after canceling the product
	 ******************************************************************************************************************/
	String updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int quantity, String userId)
			throws Exception;
	
	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getTargetSales(String userId)
	 * - Input Parameters : userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Returns Target Sales and Target Status for a Sales Representative
	 ********************************************************************************************************/
	String getTargetSales(String userId) throws Exception;
	
	
	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getBonus(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Returns Bonus offered to a Sales Representative 
	 ********************************************************************************************************/
	String getBonus(String userId) throws Exception;
	
}
