package com.capgemini.go.dao;

import java.util.List;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;

public interface RetailerInventoryDao {
	// Retailer Inventory Data Access Functions
	/*******************************************************************************************************
	 * - Function Name : getSoldItemsDetails - Input Parameters : RetailerInventory
	 * queryArguments - Author : - Description : to get List of all Sold Items By
	 * retailer ID
	 * 
	 * @param queryArguments
	 * @return List<RetailerInventoryDTO>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryDTO> getSoldItemsDetails(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getDeliveredItemsDetails - Input Parameters :
	 * RetailerInventory queryArguments - Author : - Description : to get List of
	 * all Sold Items By retailer ID
	 * 
	 * @param queryArguments
	 * @return
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryDTO> getDeliveredItemsDetails(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;
	// END OF Retailer Inventory Data Access Functions

	// Functions for Retailer Inventory Manipulation
	/*******************************************************************************************************
	 * - Function Name : getItemListByRetailer - Input Parameters :
	 * RetailerInventoryDTO queryArguments - Return Type :
	 * List<RetailerInventoryDTO> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getItemListByRetailer(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryDTO> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerInventoryException;

	/*******************************************************************************************************
	 * Function Name : updateProductReceiveTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update receive timestamp of the product
	 ********************************************************************************************************/
	boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;

	/*******************************************************************************************************
	 * Function Name : updateProductSaleTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update sale timestamp of the product
	 ********************************************************************************************************/
	boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;

	/*******************************************************************************************************
	 * Function Name : insertItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to insert a product into the inventory
	 ********************************************************************************************************/
	boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;

	/*******************************************************************************************************
	 * Function Name : deleteItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 29/9/2019 Description : to delete a product into the inventory
	 ********************************************************************************************************/
	boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
	// end of Functions for Retailer Inventory Manipulation
}
