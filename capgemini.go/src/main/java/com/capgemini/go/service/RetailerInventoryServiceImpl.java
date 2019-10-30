package com.capgemini.go.service;

import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.exception.RetailerInventoryException;

public class RetailerInventoryServiceImpl implements RetailerInventoryService {

	public List<RetailerInventoryBean> getShelfTimeReport(ReportType reportType, String retailerId,
			Calendar dateSelection) throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getDeliveryTimeReport(ReportType reportType, String retailerId,
			int productCategory) throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerInventoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
