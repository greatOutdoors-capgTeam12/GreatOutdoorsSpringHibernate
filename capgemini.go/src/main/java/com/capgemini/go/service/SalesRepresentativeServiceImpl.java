package com.capgemini.go.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.SalesRepresentativeDao;
import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.exception.OrderNotFoundException;
import com.capgemini.go.exception.ProductNotFoundException;
import com.capgemini.go.exception.SalesRepresentativeException;

@Service(value = "salesRepresentativeService")
public class SalesRepresentativeServiceImpl implements SalesRepresentativeService{

	@Autowired
	private SalesRepresentativeDao salesRepresentativeDao;

	public SalesRepresentativeDao getSalesRepDao() {
		return salesRepresentativeDao;
	}

	public void setSalesRepDao(SalesRepresentativeDao salesRepresentativeDao) {
		this.salesRepresentativeDao = salesRepresentativeDao;
	}


	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelOrder() 
	 * - Input Parameters : String orderId, String userId
	 * - Return Type : 
	 * - Throws :SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(orderId)
	 ********************************************************************************************************/

	public String cancelOrder(String orderId, String userId) throws Exception {

		@SuppressWarnings("unused")
		OrderDTO order = null;
		@SuppressWarnings("unused")
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		String statusOrderCancel = null;
		System.out.println("Cancelling of order is being processed");
		if (salesRepresentativeDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representative id is invalid");
		} else if (salesRepresentativeDao.getOrderDetails(orderId) == null) {
			throw new OrderNotFoundException("No such order id exists");
		} else if ((salesRepresentativeDao.checkDispatchStatusForCancelling(orderId)) == true) {
			throw new OrderNotFoundException("Order cant be cancelled as it is dispatched! Return the order");
		} else if (salesRepresentativeDao.getOrderProductMapForCancelling(orderId).isEmpty() == true) {
			throw new OrderNotFoundException("Products are not mapped with order");
		} else {
			list = salesRepresentativeDao.getOrderProductMapForCancelling(orderId);
			Iterator<OrderProductMapDTO> itr = list.iterator();
			java.util.Date dt = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
			int i = 0;
			while (itr.hasNext()) {
				OrderCancelDTO oc = new OrderCancelDTO(orderId, userId, list.get(i).getProductId(),
						list.get(i).getProductUIN(), sqlDate, 0);
				statusOrderCancel = salesRepresentativeDao.cancelOrder(oc);
				i++;
				itr.next();
			}
			System.out.println("The order-cancel table's " + i + " rows has been inserted");
			statusOrderCancel = "Order has been cancelled";
		}
		return statusOrderCancel;

	}




	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelProduct 
	 * - Input Parameters : String orderId, String userId, String productID, int qty 
	 * - Return Type : String 
	 * - Throws : SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(orderId)
	 ********************************************************************************************************/

	public String cancelProduct(String orderId, String userId, String productId, int quantity) throws Exception {
		@SuppressWarnings("unused")
		OrderDTO order = null;
		@SuppressWarnings("unused")
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		@SuppressWarnings("unused")
		List<OrderDTO> listOrder = null;
		String statusProductCancel = null;
		if (salesRepresentativeDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else if (salesRepresentativeDao.getOrderDetails(orderId) == null) {
			throw new OrderNotFoundException("No such order id exists");
		}else if ((salesRepresentativeDao.checkDispatchStatusForCancelling(orderId)) == true) {
			throw new OrderNotFoundException(
					"Selected Product(s) cant be cancelled as it is dispatched! Return the Product");
		} else if (salesRepresentativeDao.getOrderProductMapForCancelling(orderId).isEmpty() == true) {
			throw new OrderNotFoundException("Products are not mapped with order");
		} else {
			list = salesRepresentativeDao.getOrderProductMapForCancelling(orderId);
			@SuppressWarnings("unused")
			int totalQuantityOrdered = list.size();
			@SuppressWarnings("unused")
			String statusOrderCancelForProduct = null;
			int productQtyOrdered = salesRepresentativeDao.getProductQuantityOrdered(orderId, productId);
			if(productQtyOrdered==0) {
				throw new ProductNotFoundException("There are no such products in the order");
			}
			if (productQtyOrdered == 0) {
				System.out.println("Invalid product id");
			} else {
				if (productQtyOrdered < quantity) {
					throw new ProductNotFoundException(
							"Quantity of product cancelled cant be greater than the quantity of product ordered");
				} else if (productQtyOrdered == quantity) {

					statusProductCancel = salesRepresentativeDao.cancelProduct(orderId, productId, productQtyOrdered, quantity);
					statusOrderCancelForProduct = salesRepresentativeDao.updateOrderCancelForProduct(orderId, productId,
							productQtyOrdered, quantity, userId);
					statusProductCancel = "The given products are canceled";
				} else if (productQtyOrdered > quantity) {
					statusProductCancel = salesRepresentativeDao.cancelProduct(orderId, productId, productQtyOrdered, quantity);
					statusOrderCancelForProduct = salesRepresentativeDao.updateOrderCancelForProduct(orderId, productId,
							productQtyOrdered, quantity, userId);
					statusProductCancel = "The given products are canceled";
				}
			}
			return statusProductCancel;
		}
	}

	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkTargetSales 
	 * - Input Parameters : String userId 
	 * - Return Type : 
	 * - Throws : SalesRepresentativeException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(sr)
	 ********************************************************************************************************/

	public String checkTargetSales(String userId) throws Exception {

		String targetStatus = null;
		if (salesRepresentativeDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else {
			targetStatus = salesRepresentativeDao.getTargetSales(userId);
		}

		return targetStatus;

	}

	//------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkBonus() 
	 * - Input Parameters : String userId 
	 * - Return Type : String
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Cancel Order to database calls dao method getOrderDetails(sr)
	 ********************************************************************************************************/

	public String checkBonus(String userId) throws Exception {
		String bonus = null;
		if (salesRepresentativeDao.checkSalesRepId(userId) == false) {
			throw new SalesRepresentativeException("Sales Representaive id is invalid");
		} else {
			bonus = salesRepresentativeDao.getBonus(userId);
		}
		return bonus;
	}
	
}
