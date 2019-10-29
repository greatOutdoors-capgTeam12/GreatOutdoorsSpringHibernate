package com.capgemini.go.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.ProductException;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

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
		this.sessionFactory = sessionFactory;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : viewAllProducts - Input Parameters : - Return Type :
	 * List<ProductDTO> - Throws : ProductException - Author : AGNIBHA CHANDRA -
	 * Creation Date : 21/9/2019 - Description : to get all the product from the
	 * database
	 * 
	 * @throws ProductException
	 ********************************************************************************************************/

	public List<ProductDTO> viewAllProducts() throws ProductException {

		List<ProductDTO> allProducts = null;
		Session session = null;
		CriteriaBuilder criteriaBuilder = null;
		try {
			session = sessionFactory.openSession();
			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ProductDTO> criteriaQuery = criteriaBuilder.createQuery(ProductDTO.class);
			criteriaQuery.from(ProductDTO.class);
//			criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(product.get("quantity"), 0)) ;
//			criteriaQuery.orderBy(criteriaBuilder.asc(product.get("productName")));
			allProducts = session.createQuery(criteriaQuery).getResultList();

		} catch (Exception exp) {
			throw new ProductException(ExceptionConstants.VIEW_PRODUCT_ERROR + exp.getMessage());

		} finally {
			session.flush();
			session.close();
		}
		return allProducts;
	}

}
