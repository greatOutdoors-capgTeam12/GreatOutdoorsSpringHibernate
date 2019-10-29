package com.capgemini.go.dao;

import java.net.ConnectException;
import java.util.List;

import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerException;

public interface RetailerInventoryDao {
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryBean> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Monthly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyTimeReport - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Vikas - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Quarterly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getYearlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryBean> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Yearly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getYearlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierProductCategoryDeliveryTime - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all product categories and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemDeliveryTime - Input Parameters :
	 * RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime - Input
	 * Parameters : RetailerInventory queryArguments - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Sujit - Creation Date :
	 * 21/9/2019 - Description : to get List of all products in outlier categories
	 * and their Delivery time periods
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerException, ConnectException;
	// end of Shelf Time Report and Delivery Time Report

	// Functions for Retailer Inventory Manipulation
	/*******************************************************************************************************
	 * Function Name : updateProductReceiveTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update receive timestamp of the product
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * Function Name : updateProductSaleTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update sale timestamp of the product
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments) throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * Function Name : insertItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to insert a product into the inventory
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;

	/*******************************************************************************************************
	 * Function Name : deleteItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 29/9/2019 Description : to delete a product into the inventory
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException;
	// end of Functions for Retailer Inventory Manipulation
}
