package com.capgemini.go.service;


import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.exception.RetailerInventoryException;

public interface RetailerInventoryService {
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Author : Kunal - Creation Date : 21/9/2019 - Description : Static
	 * Enumeration for Different Report Types
	 ********************************************************************************************************/
	public static enum ReportType {
		MONTHLY_SHELF_TIME, QUARTERLY_SHELF_TIME, YEARLY_SHELF_TIME, OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME,
		OUTLIER_ITEM_DELIVERY_TIME, OUTLIER_ITEM_IN_OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME
	}

	/*******************************************************************************************************
	 * - Function Name : getShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their shelf time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getShelfTimeReport(ReportType reportType, String retailerId,
			Calendar dateSelection) throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getDeliveryTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Delivery time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getDeliveryTimeReport(ReportType reportType, String retailerId,
			int productCategory) throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerInventoryException;
	// end of Shelf Time Report and Delivery Time Report
}
