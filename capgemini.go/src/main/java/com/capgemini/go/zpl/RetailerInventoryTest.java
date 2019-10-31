package com.capgemini.go.zpl;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import com.capgemini.go.dao.RetailerInventoryDao;
import com.capgemini.go.dao.RetailerInventoryDaoImpl;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;

public class RetailerInventoryTest {
	
	public static void main (String [] args) {
		RetailerInventoryDao retailer = new RetailerInventoryDaoImpl ();
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
//		
//		try {
//			//System.out.println(retailer.insertItemInRetailerInventory(argument));
//			System.out.println(retailer.deleteItemInRetailerInventory(argument));
//			//System.out.println(retailer.insertItemInRetailerInventory(argument));
//			//System.out.println(retailer.updateProductReceiveTimeStamp(argument2));
//			//System.out.println(retailer.updateProductSaleTimeStamp(argument3));
//		} catch (RetailerInventoryException e) {
//			System.out.println(e.getMessage());
//		}
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO();
		queryArguments.setRetailerId("ret1");
		try {
			List<RetailerInventoryDTO> result = retailer.getItemListByRetailer(queryArguments);
			for (int index = 0; index < result.size(); index++)
				System.out.println(result.get(index));
		} catch (RetailerInventoryException e) {
			System.err.println(e.getMessage());
		}
			
		
	}
}
