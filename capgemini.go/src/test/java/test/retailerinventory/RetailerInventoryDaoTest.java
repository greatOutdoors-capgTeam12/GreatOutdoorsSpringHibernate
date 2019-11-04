package test.retailerinventory;

import java.util.List;

import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerInventoryException;

import junit.framework.TestCase;

/**
 * 
 * @author kuroycho
 * This class contains tests for all Dao layer functions for RetailInventoryDaoImpl <br>
 * 
 */
public class RetailerInventoryDaoTest extends TestCase {
	/*
	 * Functions to Test:
	 *  public List<RetailerInventoryDTO> getSoldItemsDetails(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;
		public List<RetailerInventoryDTO> getDeliveredItemsDetails(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;
		public List<RetailerInventoryDTO> getItemListByRetailer(RetailerInventoryDTO queryArguments)
			throws RetailerInventoryException;
		public List<RetailerInventoryDTO> getListOfRetailers() throws RetailerInventoryException;
		boolean updateProductReceiveTimeStamp(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
		boolean updateProductSaleTimeStamp(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
		boolean insertItemInRetailerInventory(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
		boolean deleteItemInRetailerInventory(RetailerInventoryDTO queryArguments) throws RetailerInventoryException;
	 */
	
	// Create a constructor for this class, passing a name that is representative of the set of tests for this class as the parameter.
	public RetailerInventoryDaoTest(String name) {
	    super(name);
	}
	
	//Create a fixture. A test fixture is a set of sample objects that you want to (re)use during testing. 
	//For example, you might create a few sample source files for the Parser to parse. 
	//JUnit provides a setUp and a tearDown method to manage the fixture. 
	//Therefore, you can eg. create file objects in setUp to open the source files and release these resources in the tearDown method. 
	//The important thing to note is that setUp and tearDown will be called for every 'test' that you run.
	
}
