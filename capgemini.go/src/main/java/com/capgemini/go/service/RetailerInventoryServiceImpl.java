package com.capgemini.go.service;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.List;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.exception.RetailerException;

public class RetailerInventoryServiceImpl implements RetailerInventoryService {

	public List<RetailerInventoryBean> getShelfTimeReport(ReportType reportType, String retailerId,
			Calendar dateSelection) throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getDeliveryTimeReport(ReportType reportType, String retailerId,
			int productCategory) throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RetailerInventoryBean> getListOfRetailers() throws RetailerException, ConnectException {
		// TODO Auto-generated method stub
		return null;
	}

}
