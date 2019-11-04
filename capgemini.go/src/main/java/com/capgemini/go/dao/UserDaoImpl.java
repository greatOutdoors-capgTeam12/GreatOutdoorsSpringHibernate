package com.capgemini.go.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.ExceptionConstants;
import com.capgemini.go.exception.ProductException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.AuthenticationConstants;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.HibernateUtil;
import com.capgemini.go.utility.PropertiesLoader;
import com.capgemini.go.utility.Validator;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";

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
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ProductDTO> criteriaQuery = criteriaBuilder.createQuery(ProductDTO.class);
			Root<ProductDTO> product = criteriaQuery.from(ProductDTO.class);
			criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(product.get("quantity"), 0));
			criteriaQuery.orderBy(criteriaBuilder.asc(product.get("productName")));
			allProducts = session.createQuery(criteriaQuery).getResultList();

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ProductException(ExceptionConstants.VIEW_PRODUCT_ERROR + exp.getMessage());

		} finally {
			session.close();
		}
		return allProducts;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration - Input Parameters : userID, userName,
	 * userMail, userNumber, activeStatus, password, userCategory - Return Type :
	 * boolean - Throws :UserException - Author:AMAN - Creation Date : 21/9/2019 -
	 * Description : to register a new user
	 * 
	 * @throws ConnectException
	 * @throws HibernateException
	 ********************************************************************************************************/

	public boolean userRegistration(UserDTO user) throws UserException, ConnectException {

		boolean userRegStatus = false;

		String userName = user.getUserName();
		String userId = user.getUserId();
		String userMail = user.getUserMail();
		String userPassword = user.getUserPassword();
		long userNumber = user.getUserNumber();
		int userCategory = user.getUserCategory();

		Session session = null;
		SessionFactory sessionFactory = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			if (!(userCategory == Integer.parseInt(goProps.getProperty("SALES_REP"))
					|| userCategory == Integer.parseInt(goProps.getProperty("RETAILER")))) {
				GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("invalid_registration"));
				throw new UserException(exceptionProps.getProperty("invalid_registration"));

			}

			if (Validator.validatePhoneNumber(userNumber) != true) {
				GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("invalid_phone_number"));
				throw new UserException(exceptionProps.getProperty("invalid_phone_number"));
			}

			if (Validator.isValid(userMail) != true) {
				GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("invalid_email"));
				throw new UserException(exceptionProps.getProperty("invalid_email"));
			}

			if (Validator.validateUserId(userId) != true) {
				GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("invalid_userId"));
				throw new UserException(exceptionProps.getProperty("invalid_userId"));
			}
			Query validateNumberMail = (Query) session.createQuery(HQLQuerryMapper.VALIDATE_NUMBER_EMAIL);

			validateNumberMail.setParameter("existNum", userNumber);
			validateNumberMail.setParameter("existMail", userMail);

			Long existNumMail = (Long) validateNumberMail.uniqueResult();
			if (existNumMail != 0) {
				GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("number_mail_reg"));
				throw new UserException(exceptionProps.getProperty("number_mail_reg"));
			}

			Query validateUser = (Query) session.createQuery(HQLQuerryMapper.USER_ID_EXISTS);
			validateUser.setParameter("idExist", userId);
			List<UserDTO> existUser = (List<UserDTO>) validateUser.list();
			for (UserDTO u : existUser) {
				if (existUser != null) {
					GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("user_exists"));
					throw new UserException(exceptionProps.getProperty("user_exists"));
				}
				break;
			}

			UserDTO newUser = new UserDTO();

			newUser.setUserName(userName);
			newUser.setUserId(userId);
			newUser.setUserMail(userMail);
			newUser.setUserPassword(userPassword);
			newUser.setUserNumber(userNumber);
			newUser.setUserCategory(userCategory);

			session.save(newUser);

			userRegStatus = true;
			session.getTransaction().commit();

		} catch (HibernateException | IOException e) {
			session.getTransaction().rollback();
			GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("registration_failed"));
			throw new UserException(" >>>" + e.getMessage());
		} finally {
			session.close();
		}
		return userRegStatus;

	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogin - Input Parameters : userID, password - Return
	 * Type : boolean - Throws :UserException - Author : AMAN - Creation Date
	 * :21/9/2019 - Description : to login a user
	 * 
	 * @throws UserException
	 * @throws Exception
	 ********************************************************************************************************/

	public boolean userLogin(UserDTO user) throws UserException, Exception, HibernateException {
		boolean userFound = false;
		boolean userLoginStatus = false;

		String userId = user.getUserId();
		String userPassword = user.getUserPassword();

		Session session = null;
		SessionFactory sessionFactory = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Query validateUser = (Query) session.createQuery(HQLQuerryMapper.USER_ID_EXISTS);
			validateUser.setParameter("idExist", userId);
			List<UserDTO> existUser = (List<UserDTO>) validateUser.list();
			for (UserDTO u : existUser) {
				userFound = true;

				if (u.isUserActiveStatus() == false) {

					if (u.getUserPassword().equals(
							Authentication.encrypt(user.getUserPassword(), AuthenticationConstants.secretKey))) {
						u.setUserActiveStatus(true);
						userLoginStatus = true;

						break;
					} else {
						GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("incorrect_password"));
						throw new UserException(exceptionProps.getProperty("incorrect_password"));
					}
				}

				else {
					GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("already_loggedin"));
					throw new UserException(exceptionProps.getProperty("already_loggedin"));

				}

			}
			session.getTransaction().commit();
		}

		catch (HibernateException | IOException e) {
			session.getTransaction().rollback();
			GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("login_failure"));
			throw new UserException(" >>>" + e.getMessage());
		} finally {
			session.close();
		}
		if (userFound == false) {
			userLoginStatus = false;
			GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("userId_not_found_failure"));
			throw new UserException(exceptionProps.getProperty("userId_not_found_failure"));

		}
		return userLoginStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout - Input Parameters : userID - Return Type :
	 * boolean - Throws :UserException - Author : AMAN - Creation Date : 21/9/2019 -
	 * Description : to logout a user
	 * 
	 * @throws SQLException
	 * @throws ConnectException
	 ********************************************************************************************************/

	@Override
	public boolean userLogout(UserDTO user) throws UserException, ConnectException {

		boolean userFound = false;
		boolean userLogoutStatus = false;
		String userId = user.getUserId();

		Session session = null;
		SessionFactory sessionFactory = null;

		try {
			exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Query validateUser = (Query) session.createQuery(HQLQuerryMapper.USER_ID_EXISTS);
			validateUser.setParameter("idExist", userId);

			List<UserDTO> existUser = (List<UserDTO>) validateUser.list();
			for (UserDTO u : existUser) {
				userFound = true;

				if (u.isUserActiveStatus() == true) {

					u.setUserActiveStatus(false);
					userLogoutStatus = true;

					break;
				} else {
					GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("already_loggedout"));
					throw new UserException(exceptionProps.getProperty("already_loggedout"));
				}
			}

			session.getTransaction().commit();

		}

		catch (HibernateException | IOException e) {
			session.getTransaction().rollback();
			GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("logout_failure"));
			throw new UserException(" >>>" + e.getMessage());

		} finally {
			session.close();
		}

		if (!userFound) {

			GoLog.getLogger(UserDaoImpl.class).error(exceptionProps.getProperty("invalid_user"));
			throw new UserException(exceptionProps.getProperty("invalid_user"));

		}

		return userLogoutStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
		/*******************************************************************************************************
		 * - Function Name : userFetch - Input Parameters : userID - Return Type :User
		 * Throws :UserException - Author : AGNIBHA/AMAN - Creation Date : 21/9/2019 -
		 * Description : to fetch a user
		 * 
		 * @throws UserException
		 * @throws ConnectException
		 * 
		 * @throws SQLException
		 ********************************************************************************************************/
		@Override
		public UserDTO fetchUser(String userId) throws UserException {
		
			UserDTO loggedUser = null;
			Session session = null;
			SessionFactory sessionFactory = null;
			try {
				exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
				goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
				sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();

				Query validateUser = (Query) session.createQuery(HQLQuerryMapper.USER_ID_EXISTS);
				validateUser.setParameter("idExist", userId);

				List<UserDTO> existUser = (List<UserDTO>) validateUser.list();
				if(existUser.size() != 0) {
				for (UserDTO u : existUser) {
				
					String userName =u.getUserName();
					String userid = u.getUserId();
					String userMail =u.getUserMail();
					String userPassword =u.getUserPassword();
					Long userNumber = u.getUserNumber();
					int userCategory =u.getUserCategory();

					loggedUser = new UserDTO(userName, userid, userMail, userPassword, userNumber, userCategory, true);
				} 
				}else {
					throw new UserException("User Does Not Exists !");
				}
				session.getTransaction().commit();
			} catch (HibernateException | IOException e) {
				session.getTransaction().rollback();
				GoLog.getLogger(UserDaoImpl.class).error(e.getMessage());
				throw new UserException(exceptionProps.getProperty("filter_product_error") + " >>> " + e.getMessage());
			}
			finally {
				session.close();
			}
			return loggedUser;
		}
}
