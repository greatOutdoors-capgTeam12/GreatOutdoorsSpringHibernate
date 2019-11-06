package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dao.RetailerInventoryDao;
import com.capgemini.go.dao.UserDao;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.dto.UserDTO;
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
     List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO (retailerId, (byte)0, null, null, null, dateSelection );
		List<RetailerInventoryDTO> listOfSoldItems = this.retailerInventoryDao.getSoldItemsDetails(queryArguments);
		try {
			List<UserDTO> userList = this.userDao.getUserIdList();
			
			for (RetailerInventoryDTO soldItem : listOfSoldItems) {
				if (soldItem.getProductSaleTimestamp().get(Calendar.MONTH) == dateSelection.get(Calendar.MONTH)) {
					RetailerInventoryBean object = new RetailerInventoryBean ();
					object.setRetailerId(retailerId);
					for (UserDTO user : userList) {
						if (user.getUserId().equals(retailerId)) {
							object.setRetailerName(user.getUserName());
							break;
						}
					}
					object.setProductCategoryNumber(soldItem.getProductCategory());
					object.setProductCategoryName(GoUtility.getCategoryName(soldItem.getProductCategory()));
					object.setProductUniqueId(soldItem.getProductUniqueId());
					object.setShelfTimePeriod(GoUtility.calculatePeriod(soldItem.getProductReceiveTimestamp(), 
							soldItem.getProductSaleTimestamp()));
					object.setDeliveryTimePeriod(null);
					result.add(object);
				} else {
					
				}
			}
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getMonthlyShelfTimeReport - " + ExceptionConstants.FAILED_TO_RETRIEVE_USERNAME);
		} catch (RuntimeException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getMonthlyShelfTimeReport - " + ExceptionConstants.INTERNAL_RUNTIME_ERROR);
		}
		return result;
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
List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO (retailerId, (byte)0, null, null, null, dateSelection );
		List<RetailerInventoryDTO> listOfSoldItems = this.retailerInventoryDao.getSoldItemsDetails(queryArguments);
		try {
			List<UserDTO> userList = this.userDao.getUserIdList();
			
			for (RetailerInventoryDTO soldItem : listOfSoldItems) {
				RetailerInventoryBean object = new RetailerInventoryBean ();
				object.setRetailerId(retailerId);
				for (UserDTO user : userList) {
					if (user.getUserId().equals(retailerId)) {
						object.setRetailerName(user.getUserName());
						break;
					}
				}
				object.setProductCategoryNumber(soldItem.getProductCategory());
				object.setProductCategoryName(GoUtility.getCategoryName(soldItem.getProductCategory()));
				object.setProductUniqueId(soldItem.getProductUniqueId());
				object.setShelfTimePeriod(GoUtility.calculatePeriod(soldItem.getProductReceiveTimestamp(), 
						soldItem.getProductSaleTimestamp()));
				object.setDeliveryTimePeriod(null);
				result.add(object);
			}
			
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getQuarterlyShelfTimeReport - " + ExceptionConstants.FAILED_TO_RETRIEVE_USERNAME);
		} catch (RuntimeException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getQuarterlyShelfTimeReport - " + ExceptionConstants.INTERNAL_RUNTIME_ERROR);
		}
		return result;
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
	public List<RetailerInventoryBean> getYearlyShelfTimeReport (String retailerId, Calendar dateSelection)
			throws RetailerInventoryException {
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO (retailerId, (byte)0, null, null, null, dateSelection );
		List<RetailerInventoryDTO> listOfSoldItems = this.retailerInventoryDao.getSoldItemsDetails(queryArguments);
		try {
			List<UserDTO> userList = this.userDao.getUserIdList();
			
			for (RetailerInventoryDTO soldItem : listOfSoldItems) {
				RetailerInventoryBean object = new RetailerInventoryBean ();
				object.setRetailerId(retailerId);
				for (UserDTO user : userList) {
					if (user.getUserId().equals(retailerId)) {
						object.setRetailerName(user.getUserName());
						break;
					}
				}
				object.setProductCategoryNumber(soldItem.getProductCategory());
				object.setProductCategoryName(GoUtility.getCategoryName(soldItem.getProductCategory()));
				object.setProductUniqueId(soldItem.getProductUniqueId());
				object.setShelfTimePeriod(GoUtility.calculatePeriod(soldItem.getProductReceiveTimestamp(), 
						soldItem.getProductSaleTimestamp()));
				object.setDeliveryTimePeriod(null);
				result.add(object);
			}
			
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getYearlyShelfTimeReport - " + ExceptionConstants.FAILED_TO_RETRIEVE_USERNAME);
		} catch (RuntimeException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error(error.getMessage());
			throw new RetailerInventoryException ("getYearlyShelfTimeReport - " + ExceptionConstants.INTERNAL_RUNTIME_ERROR);
		}
		return result;
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
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getItemWiseDeliveryTimeReport - " + "Request for item wise delivery time report received");
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO (retailerId, (byte)0, null, null, null, null);
		List<RetailerInventoryDTO> listOfDeliveredItems = this.retailerInventoryDao.getDeliveredItemsDetails(queryArguments);
				
		try {
			List<UserDTO> userList = this.userDao.getUserIdList();
			
			for (RetailerInventoryDTO deliveredItem : listOfDeliveredItems) {
				RetailerInventoryBean object = new RetailerInventoryBean ();
				object.setRetailerId(retailerId);
				for (UserDTO user : userList) {
					if (user.getUserId().equals(retailerId)) {
						object.setRetailerName(user.getUserName());
						break;
					}
				}
				object.setProductCategoryNumber(deliveredItem.getProductCategory());
				object.setProductCategoryName(GoUtility.getCategoryName(deliveredItem.getProductCategory()));
				object.setProductUniqueId(deliveredItem.getProductUniqueId());
				object.setDeliveryTimePeriod(GoUtility.calculatePeriod(deliveredItem.getProductDispatchTimestamp(), deliveredItem.getProductReceiveTimestamp()));
				object.setShelfTimePeriod(null);
				result.add(object);
			}
			
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error("getItemWiseDeliveryTimeReport - " + error.getMessage());
			throw new RetailerInventoryException ("getItemWiseDeliveryTimeReport - " + ExceptionConstants.FAILED_TO_RETRIEVE_USERNAME);
		} catch (RuntimeException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).error("getItemWiseDeliveryTimeReport - " + error.getMessage());
			throw new RetailerInventoryException ("getItemWiseDeliveryTimeReport - " + ExceptionConstants.INTERNAL_RUNTIME_ERROR);
		}
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getItemWiseDeliveryTimeReport - " + "Sent requested data");
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
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getListOfRetailers - function called");
		List<RetailerInventoryBean> result = new ArrayList<RetailerInventoryBean> ();
		
		List<RetailerInventoryDTO> tempListOfDeliveredItems = this.retailerInventoryDao.getListOfRetailers();
		List<RetailerInventoryDTO> listOfDeliveredItems = new ArrayList<RetailerInventoryDTO> ();
		for (int index = 0; index < tempListOfDeliveredItems.size(); index++) {
			listOfDeliveredItems.add(new RetailerInventoryDTO (String.valueOf(tempListOfDeliveredItems.get(index)), 
					(byte)0, null, null, null, null));
		}
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getListOfRetailers - List extracted");
		
		List<UserDTO> userList = null;
		try {
			userList = this.userDao.getUserIdList();
		} catch (UserException error) {
			GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getListOfRetailers - " + error.getMessage());
			throw new RetailerInventoryException ("getListOfRetailers - " + error.getMessage());
		}
		
		for (RetailerInventoryDTO item : listOfDeliveredItems) {
			String retailerName = null;
			for (UserDTO user : userList) {
				if (user.getUserId().equals(item.getRetailerId())) {
					retailerName = user.getUserName();
					break;
				}
			}
			RetailerInventoryBean object = new RetailerInventoryBean ();
			object.setRetailerId(item.getRetailerId());
			object.setRetailerName(retailerName);
			result.add(object);
		}
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("getListOfRetailers - function return");
		return result;
	}

	/*******************************************************************************************************
	 * - Function Name : addItemToInventory <br>
	 * - Description : to add an item to inventory <br>
	 * - This function is to be called in the Order service when Order is placed by a retailer
	 * 
	 * @return boolean (true: if item added | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean addItemToInventory(String retailerId, byte productCategory, String productUIN) throws RetailerInventoryException {
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("addItemToInventory - function called");
		boolean itemAdded = false;
		Calendar currentSystemTimestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		RetailerInventoryDTO queryArgument = new RetailerInventoryDTO(retailerId, productCategory, productUIN, currentSystemTimestamp, null, null);
		itemAdded = this.retailerInventoryDao.insertItemInRetailerInventory(queryArgument);
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("addItemToInventory - function return");
		return itemAdded;
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
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("deleteItemFromInventory - function called");
		RetailerInventoryDTO queryArgument = new RetailerInventoryDTO(retailerId, (byte)0, productUIN, null, null, null);
		boolean itemDeleted = this.retailerInventoryDao.deleteItemInRetailerInventory(queryArgument);
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("deleteItemFromInventory - function return");
		return itemDeleted;
	}

	/*******************************************************************************************************
	 * - Function Name : updateItemReceiveTimestamp <br>
	 * - Description : to update receive timestamp of an item in inventory <br>
	 * 
	 * @return boolean (true: if receive timestamp updated | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean updateItemReceiveTimestamp(String retailerId, String productUIN) throws RetailerInventoryException {
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("updateItemReceiveTimestamp - function called");
		Calendar currentSystemTimestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		RetailerInventoryDTO queryArgument = new RetailerInventoryDTO(retailerId, (byte)0, productUIN, null, currentSystemTimestamp, null);
		boolean itemUpdated = this.retailerInventoryDao.updateProductReceiveTimeStamp(queryArgument);
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("updateItemReceiveTimestamp - function return");
		return itemUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateItemSaleTimestamp <br>
	 * - Description : to update sale timestamp of an item in inventory <br>
	 * 
	 * @return boolean (true: if sale timestamp updated | false: otherwise)
	 * @throws RetailerInventoryException
	 *******************************************************************************************************/
	public boolean updateItemSaleTimestamp(String retailerId, String productUIN) throws RetailerInventoryException {
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("updateItemSaleTimestamp - function called");
		Calendar currentSystemTimestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		RetailerInventoryDTO queryArgument = new RetailerInventoryDTO(retailerId, (byte)0, productUIN, null, null, currentSystemTimestamp);
		boolean itemUpdated = this.retailerInventoryDao.updateProductSaleTimeStamp(queryArgument);
		GoLog.getLogger(RetailerInventoryServiceImpl.class).info("updateItemSaleTimestamp - function return");
		return itemUpdated;
	}
}
