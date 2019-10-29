package com.capgemini.go.dao;

import java.net.ConnectException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.HibernateUtil;

public class RetailerInventoryDaoImpl implements RetailerInventoryDao {

	// Shelf Time Report and Delivery Time Report
	/*******************************************************************************************************
	 * - Function Name : getMonthlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryBean> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Monthly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getQuarterlyTimeReport - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Vikas - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Quarterly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getYearlyTimeReport - Input Parameters : RetailerInventory
	 * queryArguments - Return Type : List<RetailerInventoryBean> - Throws : N/A -
	 * Author : Vikas - Creation Date : 21/9/2019 - Description : to get List of all
	 * products and their Yearly Shelf time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getYearlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierProductCategoryDeliveryTime - Input Parameters
	 * :RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all product categories and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemDeliveryTime - Input Parameters :
	 * RetailerInventory queryArguments - Return Type : List<RetailerInventoryBean>
	 * - Throws : N/A - Author : Sujit - Creation Date : 21/9/2019 - Description :
	 * to get List of all products and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime - Input
	 * Parameters : RetailerInventory queryArguments - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Sujit - Creation Date :
	 * 21/9/2019 - Description : to get List of all products in outlier categories
	 * and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}
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
	public boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		boolean receiveTimestampUpdated = false;
		/*
		 * required arguments in `queryArguments` productUIN, productRecieveTime
		 * 
		 * un-required productDispatchTime, productShelfTimeOut, productCategory,
		 * retailerUserId
		 */
		RetailerInventoryDTO newItem = new RetailerInventoryDTO();
		newItem.setProductUniqueId(queryArguments.getProductUniqueId());
		newItem.setProductReceiveTimestamp(queryArguments.getProductReceiveTimestamp());

		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			List<RetailerInventoryDTO> itemList = session
					.createQuery("from RetailerInventoryDTO", RetailerInventoryDTO.class).list();
			boolean productNotFound = true;
			for (RetailerInventoryDTO item : itemList) {
				if (item.getProductUniqueId().equals(newItem.getProductUniqueId())) {
					newItem.setRetailerId(item.getRetailerId());
					newItem.setProductCategory(item.getProductCategory());
					newItem.setProductDispatchTimestamp(item.getProductDispatchTimestamp());
					productNotFound = false;
					break;
				}
			}
			if (productNotFound) {
				GoLog.getLogger(RetailerInventoryDaoImpl.class).error("Product is not a part of the Inventory");
				throw new RetailerException("Product is not a part of the Inventory");
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Method has been invoked at an illegal or inappropriate time");
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Could not Commit changes to Retailer Inventory");
		} catch (PersistenceException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("The item is already present in the inventory");
		} finally {
			session.close();
		}
		receiveTimestampUpdated = true;
		return receiveTimestampUpdated;
	}

	/*******************************************************************************************************
	 * Function Name : updateProductSaleTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update sale timestamp of the product
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	public boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		boolean saleTimestampUpdated = false;
		/*
		 * required arguments in `queryArguments` productUIN, productSaleTime
		 * 
		 * un-required productDispatchTime, productReceiveTime, productCategory,
		 * retailerUserId
		 */
		RetailerInventoryDTO newItem = new RetailerInventoryDTO();
		newItem.setProductUniqueId(queryArguments.getProductUniqueId());
		newItem.setProductSaleTimestamp(queryArguments.getProductSaleTimestamp());

		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			List<RetailerInventoryDTO> itemList = session
					.createQuery("from RetailerInventoryEntity", RetailerInventoryDTO.class).list();
			boolean productNotFound = true;
			for (RetailerInventoryDTO item : itemList) {
				if (item.getProductUniqueId().equals(newItem.getProductUniqueId())) {
					newItem.setRetailerId(item.getRetailerId());
					newItem.setProductCategory(item.getProductCategory());
					newItem.setProductDispatchTimestamp(item.getProductDispatchTimestamp());
					newItem.setProductReceiveTimestamp(item.getProductReceiveTimestamp());
					productNotFound = false;
					break;
				}
			}
			if (productNotFound) {
				GoLog.getLogger(RetailerInventoryDaoImpl.class).error("Product is not a part of the Inventory");
				throw new RetailerException("Product is not a part of the Inventory");
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Method has been invoked at an illegal or inappropriate time");
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Could not Commit changes to Retailer Inventory");
		} catch (PersistenceException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("The item is already present in the inventory");
		} finally {
			session.close();
		}
		saleTimestampUpdated = true;
		return saleTimestampUpdated;
	}

	/*******************************************************************************************************
	 * Function Name : insertItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to insert a product into the inventory
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	public boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		boolean productInserted = false;
		/*
		 * required arguments in `queryArguments` retailerUserId, productCategory,
		 * productUIN, productDispatchTime
		 * 
		 * un-required productRecieveTime, productShelfTimeOut
		 * 
		 * in any case, if the arguments are supplied, they will be stored in the
		 * database
		 */
		RetailerInventoryDTO newItem = new RetailerInventoryDTO();
		newItem.setRetailerId(queryArguments.getRetailerId());
		newItem.setProductCategory((byte) queryArguments.getProductCategory());
		newItem.setProductUniqueId(queryArguments.getProductUniqueId());
		newItem.setProductDispatchTimestamp(queryArguments.getProductDispatchTimestamp());

		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(newItem);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Method has been invoked at an illegal or inappropriate time");
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Could not Commit changes to Retailer Inventory");
		} catch (PersistenceException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("The item is already present in the inventory");
		} finally {
			session.close();
		}
		productInserted = true;
		return productInserted;
	}

	/*******************************************************************************************************
	 * Function Name : deleteItemInRetailerInventory Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 29/9/2019 Description : to delete a product into the inventory
	 * 
	 * @throws ConnectException
	 * @throws RetailerException
	 ********************************************************************************************************/
	public boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		boolean itemDeleted = false;
		/*
		 * required arguments in `queryArguments` productUIN
		 * 
		 * un-required productRecieveTime, productShelfTimeOut, retailerUserId,
		 * productCategory, productDispatchTime
		 * 
		 * in any case, if the arguments are supplied, they will be stored in the
		 * database
		 */
		RetailerInventoryDTO newItem = new RetailerInventoryDTO();
		newItem.setProductUniqueId(queryArguments.getProductUniqueId());

		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(newItem);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Method has been invoked at an illegal or inappropriate time");
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("Could not Commit changes to Retailer Inventory");
		} catch (PersistenceException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerException("The item is not present in the inventory");
		} finally {
			session.close();
		}
		itemDeleted = true;
		return itemDeleted;
	}

}
