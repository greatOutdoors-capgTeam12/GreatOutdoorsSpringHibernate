package com.capgemini.go.dao;

import java.net.ConnectException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.utility.GoLog;

@Repository(value = "orderAndCartDao")
public class OrderAndCartDaoImpl implements OrderAndCartDao{
	@Autowired	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*******************************************************************************************************
	 * Function Name : addItemToCart <br>
	 * Input Parameters : CartDTO (retailerId, productId, quantity) <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to add item to a cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	@Override
	public boolean addItemToCart (CartDTO cartItem) throws RetailerException {
		boolean itemAdded = false;
		
		CartDTO newItem = new CartDTO (cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(newItem);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"addItemToCart - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"addItemToCart - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} catch (PersistenceException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"addItemToCart - " + ExceptionConstants.ITEM_ALREADY_PRESENT_IN_CART);
		} finally {
			session.close();
		}
		itemAdded = true;
		return itemAdded;
	}

	/*******************************************************************************************************
	 * Function Name : removeItemFromCart <br>
	 * Input Parameters : CartDTO (retailerId, productId)<br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to remove a item from cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	@Override
	public boolean removeItemFromCart (CartDTO cartItem) throws RetailerException {
		boolean itemRemoved = false;
		
		CartDTO newItem = new CartDTO (cartItem.getUserId(), cartItem.getProductId(), 0);
		
		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.remove(newItem);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"removeItemFromCart - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"removeItemFromCart - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} finally {
			session.close();
		}
		itemRemoved = true;
		return itemRemoved;
	}

	/*******************************************************************************************************
	 * Function Name : increaseItemQuantity <br>
	 * Input Parameters : CartDTO (retailerId, productId, quantity(absolute value|not offset))<br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 27/9/2019 <br>
	 * Description : to increase item quantity in cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	@Override
	public boolean updateItemQuantity (CartDTO cartItem) throws RetailerException {
		boolean itemUpdated = false;
		
		CartDTO newItem = new CartDTO (cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			List<CartDTO> itemList = session
					.createQuery("from CartDTO", CartDTO.class).list();
			boolean productNotFound = true;
			for (CartDTO item : itemList) {
				if (item.getUserId().equals(newItem.getUserId()) && item.getProductId().equals(newItem.getProductId())) {
					productNotFound = false;
					break;
				}
			}
			if (productNotFound) {
				GoLog.getLogger(OrderAndCartDaoImpl.class).debug(ExceptionConstants.PRODUCT_NOT_IN_INVENTORY);
				throw new RetailerException(
						"updateItemQuantity - " + ExceptionConstants.ITEM_NOT_IN_CART);
			} else {
				session.merge(newItem);
			}
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"addItemToCart - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"addItemToCart - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} finally {
			session.close();
		}
		itemUpdated = true;
		return itemUpdated;
	}
	
	/*******************************************************************************************************
	 * Function Name : placeOrder <br>
	 * Input Parameters : OrderDTO(orderId, userId, addressId, orderInitiationTime) <br>
	 * Return Type : boolean <br>
	 * Throws : RetailerException <br>
	 * Author : Azhar <br>
	 * Creation Date : 21/9/2019 <br>
	 * Description : to register an order for items in the cart <br>
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	public boolean registerOrder (OrderDTO order) throws RetailerException {
		boolean orderRegistered = false;
		
		OrderDTO newOrder = new OrderDTO (order.getOrderId(), (byte)0, null, 
				order.getUserId(), order.getAddressId(), order.getOrderInitiateTime());
		Transaction transaction = null;
		Session session = getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(newOrder);
			transaction.commit();
		} catch (IllegalStateException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"registerOrder - " + ExceptionConstants.INAPPROPRIATE_METHOD_INVOCATION);
		} catch (RollbackException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"registerOrder - " + ExceptionConstants.FAILURE_COMMIT_CHANGES);
		} catch (PersistenceException error) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(error.getMessage());
			throw new RetailerException(
					"registerOrder - " + ExceptionConstants.ITEM_ALREADY_PRESENT_IN_CART);
		} finally {
			session.close();
		}
		orderRegistered = true;
		return orderRegistered;
	}
}
