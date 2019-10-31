package com.capgemini.go.dao;

import java.util.List;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;

public interface RetailerInventoryDao {
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryDTO> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Monthly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyTimeReport - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryDTO>
	 * - Throws : N/A - Author : Vikas - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Quarterly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getYearlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryDTO> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Yearly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getYearlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierProductCategoryDeliveryTime - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryDTO>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all product categories and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemDeliveryTime - Input Parameters :
	 * RetailerInventory queryArguments - Return Type : List<RetailerInventoryDTO>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime - Input
	 * Parameters : RetailerInventory queryArguments - Return Type :
	 * List<RetailerInventoryDTO> - Throws : N/A - Author : Sujit - Creation Date :
	 * 21/9/2019 - Description : to get List of all products in outlier categories
	 * and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
	// end of Shelf Time Report and Delivery Time Report

	// Functions for Retailer Inventory Manipulation
	/*******************************************************************************************************
	 * - Function Name : getItemListByRetailer - Input Parameters : RetailerInventoryDTO queryArguments - Return Type :
	 * List<RetailerInventoryDTO> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getItemListByRetailer(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
	
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
