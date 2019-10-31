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

	public static void initializeRetailerInventoryWithData () {
		int numberOfSamples = 500;	// change this as per requirement
		RetailerInventoryDao retailer = new RetailerInventoryDaoImpl ();
		Random randomGenerator = new Random();
		Calendar temp = null;
		
		int samplesAdded = 0;
		int samplesRejected = 0;
		boolean flag = false;
		while (samplesAdded != numberOfSamples) {
			String retailerId = "rt" + Integer.toString(randomGenerator.nextInt(10) + 1);
			int productCategory = randomGenerator.nextInt(5) + 1;
			String productUID = "cat" + Integer.toString(productCategory) + "uid" + 
					Integer.toString(randomGenerator.nextInt(9000) + 1000);// + some random 4 digit number
			Calendar dispatchTimeStamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			temp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			temp.set(randomGenerator.nextInt(100)+1950, randomGenerator.nextInt(12)+1, randomGenerator.nextInt(31)+1);
			flag = ((randomGenerator.nextInt(2)+1) % 2 == 0)?(true):(false);
			Calendar receiveTimeStamp = (flag)?(temp):(null);
			temp.set(Calendar.YEAR, temp.get(Calendar.YEAR)+randomGenerator.nextInt(50));
			temp.set(Calendar.MONTH, temp.get(Calendar.MONTH)+randomGenerator.nextInt(24)+1);
			temp.set(Calendar.DAY_OF_MONTH, temp.get(Calendar.DAY_OF_MONTH)+randomGenerator.nextInt(30)+1);
			Calendar saleTimeStamp = (flag && (randomGenerator.nextInt(2)+1)%2 == 0)?(temp):(null);
			RetailerInventoryDTO newItem = new RetailerInventoryDTO (retailerId, 
					(byte)productCategory, productUID, dispatchTimeStamp, receiveTimeStamp, saleTimeStamp);
			try {
				if (retailer.insertItemInRetailerInventory(newItem)) {
					samplesAdded++;
				}
			} catch (RetailerInventoryException e) {
				System.out.println(e.getMessage());
				samplesRejected++;
			}
		}
		System.out.println("Total Samples: " + numberOfSamples + "; Samples Added: " 
				+ samplesAdded + "; Samples Rejected: " + samplesRejected);
		return;
	}
	
	public static void main (String [] args) {
		RetailerInventoryDao retailer = new RetailerInventoryDaoImpl ();
		RetailerInventoryDTO argument = new RetailerInventoryDTO();
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
		
		RetailerInventoryDTO queryArguments = new RetailerInventoryDTO();
		queryArguments.setRetailerId("ret1");
		try {
			List<RetailerInventoryDTO> result = retailer.getItemListByRetailer(queryArguments);
			for (int index = 0; index < result.size(); index++)
				System.out.println(result.get(index));
		} catch (RetailerInventoryException e) {
		System.err.println(e.getMessage());
		}
		
		//initializeRetailerInventoryWithData ();
	}
}
