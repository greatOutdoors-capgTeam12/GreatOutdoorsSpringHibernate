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

	public List<RetailerInventoryDTO> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getYearlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

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
		newItem.setProductUniqueId(queryArguments.getProductUIN());
		newItem.setProductReceiveTimestamp(queryArguments.getProductRecieveTime());

		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			List<RetailerInventoryEntity> itemList = session
					.createQuery("from RetailerInventoryEntity", RetailerInventoryEntity.class).list();
			boolean productNotFound = true;
			for (RetailerInventoryEntity item : itemList) {
				if (item.getProductUniqueId().equals(newItem.getProductUniqueId())) {
					newItem.setRetailerId(item.getRetailerId());
					newItem.setProductCategory(item.getProductCategory());
					newItem.setProductDispatchTimestamp(item.getProductDispatchTimestamp());
					productNotFound = false;
					break;
				}
			}
			if (productNotFound) {
				GoLog.logger.error("Product is not a part of the Inventory");
				throw new RetailerException("Product is not a part of the Inventory");
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.logger.error(error.getMessage());
			throw new RetailerException("Method has been invoked at an illegal or inappropriate time");
		} catch (RollbackException error) {
			GoLog.logger.error(error.getMessage());
			throw new RetailerException("Could not Commit changes to Retailer Inventory");
		} catch (PersistenceException error) {
			GoLog.logger.error(error.getMessage());
			throw new RetailerException("The item is already present in the inventory");
		} finally {
			session.close();
		}
		receiveTimestampUpdated = true;
		return receiveTimestampUpdated;
	}

	public boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return false;
	}

}
