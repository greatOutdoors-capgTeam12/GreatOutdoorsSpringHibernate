package com.capgemini.go.zpl;

import java.util.List;

import com.capgemini.go.dao.RetailerInventoryDao;
import com.capgemini.go.dao.RetailerInventoryDaoImpl;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;
import com.capgemini.go.utility.DummyDataGenerator;
import com.capgemini.go.utility.HibernateUtil;

public class RetailerInventoryTest {
	
	public static void main (String [] args) {
		RetailerInventoryDao retailer = new RetailerInventoryDaoImpl ();
		((RetailerInventoryDaoImpl) retailer).setSessionFactory (HibernateUtil.getSessionFactory());
//		RetailerInventoryDTO argument = new RetailerInventoryDTO();
//		argument.setRetailerId("ret05");
//		argument.setProductCategory((byte) 5);
//		argument.setProductUniqueId("cat6uid0990");
//		argument.setProductDispatchTimestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
//		
//		RetailerInventoryDTO argument2 = new RetailerInventoryDTO();
//		argument2.setProductUniqueId("cat5uid0990");
//		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
//		argument2.setProductReceiveTimestamp(c);
//		
//		RetailerInventoryDTO argument3 = new RetailerInventoryDTO();
//		argument3.setProductUniqueId("cat5uid0990");
//		Calendar d = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//		d.set(Calendar.MONTH, d.get(Calendar.MONTH) + 2);
//		argument3.setProductSaleTimestamp(d);
		
//		try {
//			//System.out.println(retailer.insertItemInRetailerInventory(argument));
//			System.out.println(retailer.deleteItemInRetailerInventory(argument));
//			//System.out.println(retailer.insertItemInRetailerInventory(argument));
//			//System.out.println(retailer.updateProductReceiveTimeStamp(argument2));
//			//System.out.println(retailer.updateProductSaleTimeStamp(argument3));
//		} catch (RetailerInventoryException e) {
//			System.out.println(e.getMessage());
//		}
		
//		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO();
//		queryArguments.setRetailerId("rt1");
//		try {
//			List<RetailerInventoryDTO> result = retailer.getListOfRetailers();
//			String x = String.valueOf(result.get(0));
//			System.out.println(x);
//		} catch (RetailerInventoryException e) {
//		System.err.println(e.getMessage());
//		}
		
		DummyDataGenerator.initializeRetailerInventory (680, HibernateUtil.getSessionFactory());
	}
}
