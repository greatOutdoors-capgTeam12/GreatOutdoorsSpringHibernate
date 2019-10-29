package com.capgemini.go.dao;

import java.net.ConnectException;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerException;

public class RetailerInventoryDaoImpl implements RetailerInventoryDao{

	public List<RetailerInventoryBean> getMonthlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getQuarterlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getYearlyShelfTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getOutlierProductCategoryDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getOutlierItemDeliveryTime(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getOutlierItemInOutlierProductCategoryDeliveryTime(
			RetailerInventoryDTO queryArguments) throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments)
			throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return false;
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
