package com.capgemini.go.service;

public interface SalesRepresentativeService {

	String cancelOrder(String orderId, String userId) throws Exception;

	String cancelProduct(String orderId, String userId, String productId, int quantity) throws Exception;

	String checkTargetSales(String userId) throws Exception;

	String checkBonus(String userId) throws Exception;
	
}
