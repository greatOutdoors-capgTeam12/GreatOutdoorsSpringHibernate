package com.capgemini.go.exception;

public class ExceptionConstants {
	
	public static final String VIEW_PRODUCT_ERROR = " Error in Viewing Product >>>";
	
	// STANDARD EXCEPTION CONSTANTS
	public static final String INAPPROPRIATE_METHOD_INVOCATION = "Method has been invoked at an illegal or inappropriate time";
	public static final String FAILURE_COMMIT_CHANGES = "Could not commit changes to retailer inventory";
	public static final String INAPPROPRIATE_ARGUMENT_PASSED = "Illegal arguments passed";
	public static final String PERSISTENCE_ERROR = "Persistence error occurred";
	public static final String NO_DATA_FOUND = "The requested data was not found";
	public static final String INTERNAL_RUNTIME_ERROR = "Internal runtime error occured";
	// END OF STANDARD EXCEPTION CONSTANTS
	
	// RETAILER INVENTORY EXCEPTION CONSTANTS
	public static final String PRODUCT_NOT_IN_INVENTORY = "Item is not present in inventory";
	public static final String PRODUCT_ALREADY_PRESENT_IN_INVENTORY = "Item already present in inventory";
	public static final String FAILED_TO_RETRIEVE_USERNAME = "Could not retrieve retailer name from Database";
	// END OF RETAILER INVENTORY EXCEPTION CONSTANTS
	
	// ORDER AND CART EXCEPTION CONSTANTS
	public static final String ITEM_ALREADY_PRESENT_IN_CART = "Item is already present in cart";
	public static final String ITEM_NOT_IN_CART = "Item is not in cart";
	public static final String ITEM_ALREADY_MAPPED_TO_ORDER = "Item is already mapped to an existing order";
	// END OF ORDER AND CART EXCEPTION CONSTANTS
	
	public static final String INVALID_DATE = "The given date is invalid";
	public static final String EMPTY_DATABASE = "The database is empty";
}

