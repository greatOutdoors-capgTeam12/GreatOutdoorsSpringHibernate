package com.capgemini.go.service;

import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.exception.RetailerInventoryException;

public interface RetailerInventoryService {
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Monthly shelf
	 * time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getMonthlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Quarterly
	 * shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getQuarterlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getYearlyShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Yearly shelf
	 * time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getYearlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getItemWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Items and their Delivery time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getItemWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException;
	
	/*******************************************************************************************************
	 * - Function Name : getCategoryWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Categories and their average Delivery time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getCategoryWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException;
	
	/*******************************************************************************************************
	 * - Function Name : getOutlierCategoryItemWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Items in Outlier Categories and their Delivery time
	 * periods
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getOutlierCategoryItemWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException;

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerInventoryException;
	// end of Shelf Time Report and Delivery Time Report
}
