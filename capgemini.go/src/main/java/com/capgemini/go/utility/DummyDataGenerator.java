package com.capgemini.go.utility;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.SessionFactory;

import com.capgemini.go.dao.RetailerInventoryDao;
import com.capgemini.go.dao.RetailerInventoryDaoImpl;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;

public class DummyDataGenerator {
	
	/**
	 * This function is only to be used to populate the retailer inventory table with dummy data.
	 * It does not check to see if the table exists or not, nor does it delete existing data.
	 * It inserts @numOfSamples number of new entities in the table.
	 */
	public static void initializeRetailerInventory (int numOfSamples, SessionFactory sessionFactory) {
		String [] retailerIds = {"avik", "Go09", "QW01", "RT03", "RT1235", "RT1239", "RT1240",
				"RT421", "RT55", "SR01", "TY900", "TY989", "TYP09", "USER01", "USERA22",
				"UU555", "XX99"};
		
		int numberOfSamples = numOfSamples;
		
		RetailerInventoryDao retailer = new RetailerInventoryDaoImpl ();
		((RetailerInventoryDaoImpl) retailer).setSessionFactory(sessionFactory);
		Random randomGenerator = new Random();
		Calendar temp = null;
		
		int samplesAdded = 0;
		int samplesRejected = 0;
		boolean flag = false;
		while (samplesAdded != numberOfSamples) {
			String retailerId = retailerIds[randomGenerator.nextInt(Array.getLength(retailerIds))];
			int productCategory = randomGenerator.nextInt(5) + 1;
			String productUID = "cat" + Integer.toString(productCategory) + "uid" + 
					Integer.toString(randomGenerator.nextInt(9000) + 1000);// + some random 4 digit number
			Calendar dispatchTimeStamp = Calendar.getInstance();
			dispatchTimeStamp.set(randomGenerator.nextInt(100)+1950, randomGenerator.nextInt(12)+1, randomGenerator.nextInt(31)+1);
			temp = Calendar.getInstance();
			temp.set(Calendar.YEAR, dispatchTimeStamp.get(Calendar.YEAR) + randomGenerator.nextInt(50));
			temp.set(Calendar.MONTH, dispatchTimeStamp.get(Calendar.MONTH) + randomGenerator.nextInt(12)+1);
			temp.set(Calendar.DAY_OF_MONTH, dispatchTimeStamp.get(Calendar.DAY_OF_MONTH) + randomGenerator.nextInt(31)+1);
			flag = ((randomGenerator.nextInt(2)+1) % 2 == 0)?(true):(false);
			Calendar receiveTimeStamp = (flag)?(temp):(null);
			Calendar saleTimeStamp = null;
			if (receiveTimeStamp != null) {
				Calendar temp1 = Calendar.getInstance();
				temp1.set(Calendar.YEAR, receiveTimeStamp.get(Calendar.YEAR) + randomGenerator.nextInt(50));
				temp1.set(Calendar.MONTH, receiveTimeStamp.get(Calendar.MONTH) + randomGenerator.nextInt(12)+1);
				temp1.set(Calendar.DAY_OF_MONTH, receiveTimeStamp.get(Calendar.DAY_OF_MONTH) + randomGenerator.nextInt(30)+1);
				saleTimeStamp = (flag && (randomGenerator.nextInt(2)+1)%2 == 0)?(temp1):(null);
			} else {
				
			}
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
}
