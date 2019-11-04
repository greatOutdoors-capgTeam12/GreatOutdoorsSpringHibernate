package com.capgemini.go.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.OrderCancelDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.exception.SalesRepresentativeException;

@Repository(value = "salesRepresentativeDao")
public class SalesRepresentativeDaoImpl implements SalesRepresentativeDao{

	// this class is wired with the sessionFactory to do some operation in the
	// database

	@Autowired	
	private SessionFactory sessionFactory;
	// this will create one sessionFactory for this class
	// there is only one sessionFactory should be created for the applications
	// we can create multiple sessions for a sessionFactory
	// each session can do some functions

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("Setting Session Factory - " + sessionFactory);
		this.sessionFactory = sessionFactory;
	}
	
	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderDetails(String orderId) 
	 * - Input Parameters : orderId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if orderId exists and also getting order details
	 ********************************************************************************************************/

	public String getOrderDetails(String orderId) throws Exception {

		Session session = null;
		SessionFactory sessionFactory = null;
		String orderID = null;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery(HQLQuerryMapper.IS_ORDER_PRESENT);
			query.setParameter("orderID", orderId);
			List<String> orderList = (List<String>)query.list();
			orderID = orderList.get(0).toString();
			if(orderID != null) {
				return orderID;
			}
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("orderId_not_found_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return orderID;


	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkSalesRepId(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : boolean
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Checking if userId exists
	 ********************************************************************************************************/

	public boolean checkSalesRepId(String userId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		boolean checkSalesRepIdFlag = false;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(HQLQuerryMapper.IS_SALES_REP_ID_PRESENT);
			query.setParameter("userID", userId);
			List<SalesRepDTO> userList = (List<SalesRepDTO>)query.list();
			String userID = userList.get(0).getUserId();
			if (userID != null) {
				checkSalesRepIdFlag = true;
				return checkSalesRepIdFlag;
			}
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("userId_not_found_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return checkSalesRepIdFlag;

	}


	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : checkDispatchStatusForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : boolean 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Check if order is dispatched
	 ********************************************************************************************************/

	public boolean checkDispatchStatusForCancelling(String orderId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		boolean checkDispatchStatusFlag = false;
		int index = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createNativeQuery(HQLQuerryMapper.CHECK_ORDER_DISPATCH_STATUS);
			query.setParameter("orderID", orderId);
			List<Integer> orderDipatchStatusList = (List<Integer>)query.list();
			index = Integer.parseInt(orderDipatchStatusList.get(0).toString());
			if (index == 1) {
				checkDispatchStatusFlag = true;
			}
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("productId_not_found_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return checkDispatchStatusFlag;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getOrderProductMapForCancelling(String orderId)
	 * - Input Parameters : orderId 
	 * - Return Type : List<OrderReturnEntity>
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : To get a list of type OrderReturnEntity
	 ********************************************************************************************************/

	public List<OrderProductMapDTO> getOrderProductMapForCancelling(String orderId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		OrderProductMapDTO opm = null;
		List<OrderProductMapDTO> list = null;
		list = new ArrayList<OrderProductMapDTO>();
		int index = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery(HQLQuerryMapper.GET_PRODUCT_MAP);
			query.setParameter("orderID", orderId);
			List<OrderProductMapDTO> orderProductMapEntityList = (List<OrderProductMapDTO>)query.list();
			while (orderProductMapEntityList.size()>index) {
				int productStatus = orderProductMapEntityList.get(index).getProductStatus();
				if (productStatus != 1) {
					//GoLog.logger.error(exceptionProps.getProperty("order_return_failure"));
					throw new SalesRepresentativeException("product cancel failure"/*exceptionProps.getProperty("product_cancel_failure")*/);
				} else {
					String productId = orderProductMapEntityList.get(index).getProductId();
					String productUIN = orderProductMapEntityList.get(index).getProductUIN();
					opm = new OrderProductMapDTO(orderId, productId, productUIN, (productStatus == 0 ? 0 : 1),
							0);
					list.add(opm);
				}
				index++;
			}

		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("orderId_not_found_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return list;
	}

	// ------------------------ 1. GO Application --------------------------
	/**************************************************************************************************************
	 * - Function Name : cancelOrder(OrderCancelEntity orderCancel)
	 * - Input Parameters : OrderCancelEntity orderCancel 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table and updating OrderReturnEntity  after canceling the product
	 **************************************************************************************************************/

	public String cancelOrder(OrderCancelDTO orderCancel) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		Session session2 = null;
		SessionFactory sessionFactory2 = null;
		String cancelOrderStatus = "Order cant be cancelled";
		int i = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			OrderCancelDTO oce = new OrderCancelDTO();
			oce.setOrderid(orderCancel.getOrderid());
			oce.setUserId(orderCancel.getUserId());
			oce.setProductid(orderCancel.getProductid());
			oce.setProductuin(orderCancel.getProductuin());
			//java.util.Date utilDate = orderCancel.getOrdercanceltime();
			//java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			oce.setOrdercanceltime(orderCancel.getOrdercanceltime());
			oce.setOrdercancelstatus(1);
			session.save(oce);
			session.getTransaction().commit();
			sessionFactory2.getSessionFactory();
			session2 = sessionFactory2.getCurrentSession();
			session2.beginTransaction();
			Query query =  session2.createQuery(HQLQuerryMapper.UPDATE_ORDER_PRODUCT_MAP_WITH_PRODUCT_UIN);
			query.setParameter("orderID", orderCancel.getOrderid());
			query.setParameter("productID", orderCancel.getProductid());
			query.setParameter("productUIN", orderCancel.getProductuin());
			int j = query.executeUpdate();
			System.out.println("The order-product-map table's " + j + " rows has been updated");
			cancelOrderStatus = "The product with the uin " + orderCancel.getProductuin() + " has been cancelled";
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty(" return_order_failure"));
		} finally {
			try {
				session.close();
				session2.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return cancelOrderStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getProductQuantityOrdered(String orderId, String productId)
	 * - Input Parameters : orderId, productId
	 * - Return Type : int 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return the quantity of product ordered
	 ********************************************************************************************************/

	public int getProductQuantityOrdered(String orderId, String productId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		int productQuantity = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery(HQLQuerryMapper.GET_PRODUCT_QUANTITY);
			query.setParameter("orderID", orderId);
			query.setParameter("productID", productId);
			List<Long> prodQtyList = (List<Long>)query.list();
			productQuantity = Integer.parseInt(prodQtyList.get(0).toString());
		} catch (Exception e) {
			throw new SalesRepresentativeException(e.getMessage());
			//GoLog.logger.error(exceptionProps.getProperty("product_quantity_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return productQuantity;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : cancelProduct(String orderId, String productId, int productQtyOrdered, int quantity) 
	 * - Input Parameters : orderId, productId, productQty, quantity 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Updating the Order_Cancel table after canceling the product
	 ********************************************************************************************************/

	public String cancelProduct(String orderId, String productId, int productQtyOrdered, int quantity) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		String cancelProductStatus = "Product cant be cancelled";
		int rowsChanged = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			if (productQtyOrdered == quantity) {
				Query query =  session.createQuery(HQLQuerryMapper.UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY);
				query.setParameter("orderID", orderId);
				query.setParameter("productID", productId);
				rowsChanged = query.executeUpdate();
			} else if (productQtyOrdered > quantity) {
				Query query =  session.createNativeQuery(HQLQuerryMapper.UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY);
				query.setParameter("orderID", orderId);
				query.setParameter("productID", productId);
				query.setParameter("quantity", quantity);
				rowsChanged = query.executeUpdate();
			}
			cancelProductStatus = "The given quantity of product has been cancelled and " + String.valueOf(rowsChanged)
			+ " rows has been changed";
			System.out.println(cancelProductStatus);
			return cancelProductStatus;
		} catch (Exception e) {
			//throw new SalesRepresentativeException(e.getMessage());
			//GoLog.logger.error(exceptionProps.getProperty("product_quantity_failure"));
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				//throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return cancelProductStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************************
	 * - Function Name : updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int qty,
								String userId) 
	 * - Input Parameters : orderId, productId, productQtyOrdered, qty, userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Adding rows to OrderCancelEntity table after canceling the product
	 ******************************************************************************************************************/

	public String updateOrderCancelForProduct(String orderId, String productId, int productQtyOrdered, int quantity,
			String userId) throws Exception {
		String statusCancelOrderForProduct = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		Session session2 = null;
		SessionFactory sessionFactory2 = null;
		int rowsChanged = 0;
		int index = 0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			if (productQtyOrdered == quantity) {
				Query query =  session.createQuery(HQLQuerryMapper.GET_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY);
				query.setParameter("orderID", orderId);
				query.setParameter("productID", productId);
				List<OrderProductMapDTO> orderProductMapEntityList = (List<OrderProductMapDTO>)query.list();
				sessionFactory2.getSessionFactory();
				session2 = sessionFactory2.getCurrentSession();
				while (orderProductMapEntityList.size()>index) {
					OrderCancelDTO oce = new OrderCancelDTO();
					oce.setOrderid(orderProductMapEntityList.get(index).getOrderId());
					oce.setUserId(userId);
					oce.setProductid(orderProductMapEntityList.get(index).getProductId());
					oce.setProductuin(orderProductMapEntityList.get(index).getProductUIN());
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					oce.setOrdercanceltime(sqlDate);
					oce.setOrdercancelstatus(1);
					session2.save(oce);
					session2.getTransaction().commit();
					index++;
					sessionFactory2.getSessionFactory();
					session2 = sessionFactory2.getCurrentSession();
					session2.beginTransaction();
				}
				System.out.println("The order-cancel table's " + orderProductMapEntityList.size() + " rows has been inserted");
			} else if (productQtyOrdered > quantity) {
				Query query =  session.createQuery(HQLQuerryMapper.GET_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY);
				query.setParameter("orderID", orderId);
				query.setParameter("productID", productId);
				query.setFirstResult(0); 
				query.setMaxResults(quantity);
				List<OrderProductMapDTO> orderProductMapEntityList = (List<OrderProductMapDTO>)query.list();
				sessionFactory2.getSessionFactory();
				session2 = sessionFactory2.getCurrentSession();
				while (orderProductMapEntityList.size()>index) {
					OrderCancelDTO oce = new OrderCancelDTO();
					oce.setOrderid(orderProductMapEntityList.get(index).getOrderId());
					oce.setUserId(userId);
					oce.setProductid(orderProductMapEntityList.get(index).getProductId());
					oce.setProductuin(orderProductMapEntityList.get(index).getProductUIN());
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					oce.setOrdercanceltime(sqlDate);
					oce.setOrdercancelstatus(1);
					session2.save(oce);
					session2.getTransaction().commit();
					index++;
					sessionFactory2.getSessionFactory();
					session2 = sessionFactory2.getCurrentSession();
					session2.beginTransaction();
				}
				System.out.println("The order-cancel table's " + orderProductMapEntityList.size() + " rows has been inserted");
			}
			statusCancelOrderForProduct = "The given quantity of product has been cancelled";
		} catch (Exception e) {
			throw new SalesRepresentativeException(e.getMessage());
			//GoLog.logger.error(exceptionProps.getProperty("cancel_order_failure"));
		} finally {
			try {
				session.close();
				session2.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return statusCancelOrderForProduct;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getTargetSales(String userId)
	 * - Input Parameters : userId
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return Target Sales and Target Status for a Sales Representative
	 ********************************************************************************************************/

	public String getTargetSales(String userId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		Session session2 = null;
		SessionFactory sessionFactory2 = null;
		String targetStatus = null;
		int targetSalesStatus = 0;
		String status = null;
		double targetSales = 0.0;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery(HQLQuerryMapper.SELECT_SALES_REP_TARGET);
			query.setParameter("userID", userId);
			List<Double> targetSalesList = (List<Double>)query.list();
			targetSales = Double.parseDouble(targetSalesList.get(0).toString());
			Query query2 =  session.createQuery(HQLQuerryMapper.GET_TARGET_STATUS);
			query2.setParameter("userID", userId);
			List<Integer> targetStatusList = (List<Integer>)query2.list();
			targetSalesStatus = Integer.parseInt(targetStatusList.get(0).toString());
			if(targetSalesStatus==-1) {
				status = "exceeded";
			}else if(targetSalesStatus==0) {
				status = "met";
			}else {
				status = "not met";
			}
			targetStatus = "Your target sales is " + String.valueOf(targetSales) + " and target status is "
					+ status;
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("sales representative not found"));
			throw new Exception("Sales representative data not found");
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return targetStatus;
	}

	// ------------------------ 1. GO Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : getBonus(String userId) 
	 * - Input Parameters : userId 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 28/09/2019 
	 * - Description : Return Bonus offered to a Sales Representative 
	 ********************************************************************************************************/

	public String getBonus(String userId) throws Exception {
		Session session = null;
		SessionFactory sessionFactory = null;
		Double bonus = 0.0;
		String bonusForSales = null;
		try {
			//exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			//goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery(HQLQuerryMapper.SELECT_SALES_REP_BONUS);
			query.setParameter("userID", userId);
			List<Double> bonusList = (List<Double>)query.list();
			bonus = Double.parseDouble(bonusList.get(0).toString());
			bonusForSales = "Your bonus is " + String.valueOf(bonus);
		} catch (Exception e) {
			//GoLog.logger.error(exceptionProps.getProperty("sales representative not found"));
			throw new Exception("Sales representative data not found");
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				throw new SalesRepresentativeException(e.getMessage());
				//throw new ConnectException(Constants.connectionError);
			}
		}
		return bonusForSales;
	}
}
