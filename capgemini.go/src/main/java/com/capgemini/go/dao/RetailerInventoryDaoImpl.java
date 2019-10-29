package com.capgemini.go.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.RetailerInventoryException;
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
			throws RetailerInventoryException {
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
			throws RetailerInventoryException {
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
			throws RetailerInventoryException {
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
			throws RetailerInventoryException {
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
			throws RetailerInventoryException {
		List<RetailerInventoryDTO> result = null;	// List reference variable for query result
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RetailerInventoryDTO> criteriaQuery = builder.createQuery(RetailerInventoryDTO.class);
			Root<RetailerInventoryDTO> retialerInventory = criteriaQuery.from(RetailerInventoryDTO.class);
			criteriaQuery.select(retialerInventory);
			criteriaQuery.where(builder.equal(retialerInventory.get("retailerId"), queryArguments.getRetailerId()));
			// select * from RETAILER_INVENTORY where RETAILER_ID = ?
			//Query<RetailerInventoryDTO> q = session.createQuery(criteriaQuery);
			result = session.createQuery(criteriaQuery).getResultList(); // q.getResultList();
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("getOutlierItemDeliveryTime - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (IllegalArgumentException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("getOutlierItemDeliveryTime - " + ExceptionConstants.INAPPROPRIATE_ARGUMENT_PASSED);
		} finally {
			session.close();
		}
		if (result == null || result.size() == 0) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(ExceptionConstants.NO_DATA_FOUND);
			throw new RetailerInventoryException("getOutlierItemDeliveryTime - " + ExceptionConstants.NO_DATA_FOUND);
		}
		return result;
	}

	/*******************************************************************************************************
	 * - Function Name : getOutlierItemInOutlierProductCategoryDeliveryTime - Input
	 * Parameters : RetailerInventory queryArguments - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Sujit - Creation Date :
	 * 21/9/2019 - Description : to get List of all products in outlier categories
	 * and their Delivery time periods
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	/*******************************************************************************************************
	 * - Function Name : getListOfRetailers - Input Parameters : N/A - Return Type :
	 * List<RetailerInventoryBean> - Throws : N/A - Author : Kunal - Creation Date :
	 * 21/9/2019 - Description : to get List of all retailers in database
	 ********************************************************************************************************/
	public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}
	// end of Shelf Time Report and Delivery Time Report

	// Functions for Retailer Inventory Manipulation
	/*******************************************************************************************************
	 * Function Name : updateProductReceiveTimeStamp Input Parameters :
	 * RetailerInventoryDTO Return Type : boolean Author : Kunal Creation Date :
	 * 21/9/2019 Description : to update receive timestamp of the product
	 ********************************************************************************************************/
	public boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException {
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
				GoLog.getLogger(RetailerInventoryDaoImpl.class).debug(ExceptionConstants.PRODUCT_NOT_IN_INVENTORY);
				throw new RetailerInventoryException("updateProductReceiveTimeStamp - " + ExceptionConstants.PRODUCT_NOT_IN_INVENTORY);
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("updateProductReceiveTimeStamp - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("updateProductReceiveTimeStamp - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
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
	 ********************************************************************************************************/
	public boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException {
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
					.createQuery("from RetailerInventoryDTO", RetailerInventoryDTO.class).list();
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
				GoLog.getLogger(RetailerInventoryDaoImpl.class).debug(ExceptionConstants.PRODUCT_NOT_IN_INVENTORY);
				throw new RetailerInventoryException(
						"updateProductSaleTimeStamp - " + ExceptionConstants.PRODUCT_NOT_IN_INVENTORY);
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException(
					"updateProductSaleTimeStamp - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("updateProductSaleTimeStamp - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
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
	 ********************************************************************************************************/
	public boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException {
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
			throw new RetailerInventoryException(
					"insertItemInRetailerInventory - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("insertItemInRetailerInventory - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} catch (PersistenceException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException(
					"insertItemInRetailerInventory - " + ExceptionConstants.PRODUCT_ALREADY_PRESENT_IN_INVENTORY);
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
	 ********************************************************************************************************/
	public boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException {
		boolean itemDeleted = false;
		/*
		 * required arguments in `queryArguments` productUIN, retailerUserId
		 * 
		 * un-required productRecieveTime, productShelfTimeOut, productCategory,
		 * productDispatchTime
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
			session.remove(newItem);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException(
					"deleteItemInRetailerInventory - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(RetailerInventoryDaoImpl.class).error(error.getMessage());
			throw new RetailerInventoryException("deleteItemInRetailerInventory - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} finally {
			session.close();
		}
		itemDeleted = true;
		return itemDeleted;
	}

}
