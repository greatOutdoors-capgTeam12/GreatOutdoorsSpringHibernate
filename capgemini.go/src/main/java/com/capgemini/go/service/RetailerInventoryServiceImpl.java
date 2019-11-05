package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dao.RetailerInventoryDao;
import com.capgemini.go.dao.UserDao;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.RetailerInventoryException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.GoUtility;

@Service (value = "retailerInventoryService")
public class RetailerInventoryServiceImpl implements RetailerInventoryService {
	@Autowired
	private RetailerInventoryDao retailerInventoryDao;
	
	public RetailerInventoryDao getRetailerInventoryDao () {
		return retailerInventoryDao;
	}

	public void setRetailerInventoryDao (RetailerInventoryDao retailerInventoryDao) {
		this.retailerInventoryDao = retailerInventoryDao;
	}
	
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao () {
		return userDao;
	}

	public void setUserDao (UserDao userDao) {
		this.userDao = userDao;
	}
	
	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyShelfTimeReport <br>
	 * - Description : to get Monthly Shelf Time Report <br>
	 * 
	 * @param String   retailerId
	 * @param Calendar dateSelection
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getMonthlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyShelfTimeReport <br>
	 * - Description : to get Quarterly Shelf Time Report <br>
	 * 
	 * @param String   retailerId
	 * @param Calendar dateSelection
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getQuarterlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getYearlyShelfTimeReport <br>
	 * - Description : to get Yearly Shelf Time Report <br>
	 * 
	 * @param String   retailerId
	 * @param Calendar dateSelection
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getYearlyShelfTimeReport(String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getItemWiseDeliveryTimeReport <br>
	 * - Description : to get Item wise Delivery Time Report <br>
	 * 
	 * @param String retailerId
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getItemWiseDeliveryTimeReport (String retailerId)
			throws RetailerInventoryException {
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		String retailerName = null;
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO (retailerId, (byte)0, null, null, null, null);
		List<RetailerInventoryDTO> listOfDeliveredItems = this.retailerInventoryDao.getDeliveredItemsDetails(queryArguments);
				
		try {
			retailerName = this.userDao.getUserById(retailerId).getUserName();
			
			for (RetailerInventoryDTO deliveredItem : listOfDeliveredItems) {
				RetailerInventoryBean object = new RetailerInventoryBean ();
				object.setRetailerId(retailerId);
				object.setRetailerName(retailerName);
				object.setProductCategoryNumber(deliveredItem.getProductCategory());
				object.setProductCategoryName(GoUtility.getCategoryName(deliveredItem.getProductCategory()));
				object.setProductUniqueId(deliveredItem.getProductUniqueId());
				object.setDeliveryTimePeriod(GoUtility.calculatePeriod(deliveredItem.getProductDispatchTimestamp(), deliveredItem.getProductReceiveTimestamp()));
				object.setShelfTimePeriod(null);
				result.add(object);
			}
			
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getItemWiseDeliveryTimeReport - " + ExceptionConstants.FAILED_TO_RETRIEVE_USERNAME);
		} catch (RuntimeException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getItemWiseDeliveryTimeReport - " + ExceptionConstants.INTERNAL_RUNTIME_ERROR);
		}
		
		return result;
	}

	/*******************************************************************************************************
	 * - Function Name : getCategoryWiseDeliveryTimeReport <br>
	 * - Description : to get Category wise Delivery Time Report <br>
	 * 
	 * @param String retailerId
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getCategoryWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException {
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierCategoryItemWiseDeliveryTimeReport <br>
	 * - Description : to get Outlier Category Item wise Delivery Time Report <br>
	 * 
	 * @param String retailerId
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getOutlierCategoryItemWiseDeliveryTimeReport(String retailerId)
			throws RetailerInventoryException {
		return null;
	}
	// end of Shelf Time Report and Delivery Time Report

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers <br>
	 * - Description : to get list of retailers in database <br>
	 * 
	 * @return List<RetailerInventoryBean>
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerInventoryException {
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : addItemToInventory <br>
	 * - Description : to add an item to inventory <br>
	 * - This function is to be called in the Order service when Order is placed by a retailer
	 * 
	 * @return boolean (true: if item added | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean addItemToInventory(String retailerId, String productUIN) throws RetailerInventoryException {
		return false;
	}

	/*******************************************************************************************************
	 * - Function Name : deleteItemFromInventory <br>
	 * - Description : to delete an item from inventory <br>
	 * - This function is to be called if Order is canceled by a retailer
	 * 
	 * @return boolean (true: if item deleted | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean deleteItemFromInventory(String retailerId, String productUIN) throws RetailerInventoryException {
		return false;
	}

	/*******************************************************************************************************
	 * - Function Name : updateItemReceiveTimestamp <br>
	 * - Description : to update receive timestamp of an item in inventory <br>
	 * 
	 * @return boolean (true: if receive timestamp updated | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean updateItemReceiveTimestamp(String retailerId, String productUIN) throws RetailerInventoryException {
		return false;
	}

	/*******************************************************************************************************
	 * - Function Name : updateItemSaleTimestamp <br>
	 * - Description : to update sale timestamp of an item in inventory <br>
	 * 
	 * @return boolean (true: if sale timestamp updated | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean updateItemSaleTimestamp(String retailerId, String productUIN) throws RetailerInventoryException {
		return false;
	}
}
