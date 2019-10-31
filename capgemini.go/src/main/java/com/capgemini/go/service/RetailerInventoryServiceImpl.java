package com.capgemini.go.service;

import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.exception.RetailerInventoryException;

public class RetailerInventoryServiceImpl implements RetailerInventoryService {

	/*******************************************************************************************************
	 * - Function Name : getShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Monthly shelf
	 * time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getMonthlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Quarterly
	 * shelf time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getQuarterlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getYearlyShelfTimeReport - Input Parameters : ReportType
	 * reportType, String retailerId, Calendar dateSelection - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all products and their Yearly shelf
	 * time periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getYearlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getItemWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Items and their Delivery time
	 * periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getItemWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getCategoryWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Categories and their average Delivery time
	 * periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getCategoryWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierCategoryItemWiseDeliveryTimeReport - Input Parameters :
	 * ReportType reportType, String retailerId, int productCategory - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of Items in Outlier Categories and their Delivery time
	 * periods
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getOutlierCategoryItemWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	@Override
	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}
}
