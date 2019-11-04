package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.HibernateUtil;
import com.capgemini.go.utility.PropertiesLoader;

public class OrderAndCartDaoImpl implements OrderAndCartDao{

	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";


	/*******************************************************************************************************
	 * Function Name : addItemToCart Input Parameters : CartDTO Return Type :
	 * boolean Throws : RetailerException Author : Agnibha, Azhar Creation Date :
	 * 27/9/2019 Description : to add item to a cart
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/

	@SuppressWarnings("rawtypes")
	@Override
	public boolean addItemToCart(CartDTO cartItem) throws RetailerException {
		// function variables

		boolean itemAddedToCart = false;
		String retailerId = cartItem.getRetailerId();
		String productId = cartItem.getProductId();
		int quantity = cartItem.getQuantity();

		// hibernate access variables
		Session session = null;

		Transaction transaction = null;
		int quant = 0, prodQty = 0;
		// IOException possible
		// exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
		try {
			Properties exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction(); 
			Query query = (Query) session.createQuery(HQLQuerryMapper.CART_ITEM_QTY_FOR_PRODUCT_ID);
			query.setParameter("retailerId", retailerId);
			query.setParameter("productId", productId);
			query.setMaxResults(1);
			CartDTO crt = (CartDTO) query.uniqueResult();
			if (crt == null)
				quant = 0;
			else
			
				quant = crt.getQuantity();
				
			System.out.println(quant);

			// System.out.println(querycart.list());
			// int quant = (Integer) querycart.uniqueResult();

			// System.out.println(quant);
			Query query1 = session.createQuery(HQLQuerryMapper.GET_PRODUCT_QTY_FROM_DB);
			query1.setParameter("product_id", productId);
			query1.setMaxResults(1);
			ProductDTO rs1 = (ProductDTO) query1.uniqueResult();
			prodQty = rs1.getQuantity();
			System.out.println(prodQty);

			if (quant == 0) {
				// the user is adding this product to the cart for the first time
				if (prodQty > cartItem.getQuantity()) {
					CartDTO obj = new CartDTO(retailerId, productId,quantity);
					session.save(obj);
					System.out.println("FIRST TIME CART");
					itemAddedToCart = true;			
					} 
				else {

					itemAddedToCart = false;
					GoLog.getLogger(OrderAndCartDaoImpl.class).error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
			} else {
				// the user has previously added this item to his cart and is trying to increase
				// quantity
				if (prodQty > quantity) {

//		    		// add quantity to that already present in the cart and reduce the quantity in PRODUCT table by quantity amount
					try {
						
						Query query4 = session.createQuery(HQLQuerryMapper.UPDATE_CART);
						int quantityPresent = quant;
						quantityPresent += quantity;
						//System.out.println(quant+"+"+quantity+"="+quantityPresent);
						query4.setParameter("retailerId", retailerId);
						query4.setParameter("product_id", productId);
						query4.setParameter("quantity", quantityPresent);
						@SuppressWarnings("unused")
						int status=query4.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} 
				else {
					// the requested quantity of items is not available
					itemAddedToCart = false;
					GoLog.getLogger(OrderAndCartDaoImpl.class).error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
			}

			transaction.commit();
		} catch (IOException | HibernateException exp) {
			GoLog.getLogger(OrderAndCartDaoImpl.class).error(exp.getMessage());
			throw new RetailerException("Could not open Error Properties File");
		} finally {
			session.close();
		}
		System.out.println(itemAddedToCart);
		return itemAddedToCart;

	}
	
	
	/*******************************************************************************************************
	 * Function Name : placeOrder Input Parameters : Order Return Type :boolean
	 * Throws : RetailerException Author : Agnibha , Azhar - Creation Date :
	 * 21/9/2019 Description : to place order for items in the cart
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean placeOrder(OrderDTO order) throws RetailerException, ConnectException {
		boolean checkOutStatus = false;
		boolean isCartEmpty = true;
		String retailerID = order.getUserId();
		String addressID = order.getAddressId();
		String orderID = order.getOrderId();
		Date ordInitTime = (Date) order.getOrderInitiateTime();

		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {

			Properties exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			transaction = session.getTransaction();
			transaction.begin();

	        Query q1 = session.createQuery(HQLQuerryMapper.GET_CART_ITEMS_OF_RETAILER);
	        //may have multiple product ids
			q1.setParameter("retailer_id", retailerID);
			//q1.setMaxResults(1);
		    List<CartDTO> crt = (List<CartDTO>)q1.list();
		    System.out.println(crt);
		    //String orderId, String userId, String addressId, boolean orderDispatched, Date orderInitiationTime,
			//Date orderDispatchTime
		    OrderDTO ord = new OrderDTO(orderID,retailerID,addressID,false,ordInitTime,null);
		    session.save(ord);
        	/*Query q2 = session.createQuery(HQLQuerryMapper.UPDATE_ORDER_DB);

			q2.setParameter("order_id", orderID);
			q2.setParameter("address_id", addressID);
			q2.setParameter("user_id", retailerID);
			q2.setParameter("order_Initiate_Time", ordInitTime);
			q2.executeUpdate();
			
			*/
			for(CartDTO c: crt){
				isCartEmpty = false;
				String prodId = c.getProductId();
				int prodqty=c.getQuantity();
				Query q3 = session.createQuery(HQLQuerryMapper.GET_PRODUCT_DETAILS_FROM_PRODUCT_UIN_MAP);
				q3.setParameter("product_id", prodId);
				q3.setParameter("prodispresent", 1);	
				q3.executeUpdate();
				List<ProductUinMapEntity> productList = (List<ProductUinMapEntity>)q3.list();
				boolean isProductAvailable = false;
				for(int i=0;i<=c.getQuantity();i++) {
					isProductAvailable = true;
					Query q4 = session.createQuery(HQLQuerryMapper.UPDATE_PRODUCT_UIN_MAP);
					q4.setParameter("productisPresent", 0);
					q4.setParameter("product_id", prodId);
					q4.executeUpdate();
					//Query q5 = session.createQuery(HQLQuerryMapper.UPDATE_ORDER_PRODUCT_MAP_ENTRY);
					//q5.executeUpdate();
					//ProductUinMapEntity productUIN=prod.get(i);
					OrderProductMapEntity orpm=new OrderProductMapEntity();
					orpm.setGiftStatus(0);
					orpm.setOrderId(orderID);
					orpm.setProductId(prodId);
					orpm.setProductStatus(1);
					orpm.setProductUIN(productList.get(i).toString());
					session.save(orpm);
					session.getTransaction().commit();
				}
				if (isProductAvailable == false) {
					GoLog.getLogger(OrderAndCartDaoImpl.class).error(exceptionProps.getProperty("prod_not_available"));
					throw new RetailerException(exceptionProps.getProperty("prod_not_available"));
				}
				ProductDTO p = new ProductDTO();
				String pid = p.getProductId();
				Query q5 = session.createQuery(HQLQuerryMapper.UPDATE_PRODUCT_ENTITY);
				q5.setParameter("productid", pid);
				q5.executeUpdate();
			}
			if (isCartEmpty == true) {
				Query q6 = session.createQuery(HQLQuerryMapper.DELETE_ORDER);
				q6.setParameter("orderid", orderID);
				GoLog.getLogger(OrderAndCartDaoImpl.class).error(exceptionProps.getProperty("cart_empty"));
				throw new RetailerException(exceptionProps.getProperty("cart_empty"));
			}

			Query q7 = session.createQuery(HQLQuerryMapper.DELETE_CART);
			q7.setParameter("retailerId", retailerID);
			q7.executeUpdate();
			checkOutStatus = true;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return checkOutStatus;
	}

	
}
