package com.capgemini.go.service;



import java.net.ConnectException;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.ProductDao;
import com.capgemini.go.dao.UserDao;
import com.capgemini.go.dao.UserDaoImpl;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.UserException;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	

	@Autowired
	private UserDao user;

	public UserDao getProductDao() {
		return user;
	}

	public void setProductDao(UserDao user) {
		this.user = user;
	}
	

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration 
	 * - Input Parameters : userID, userName, userMail, userNumber, activeStatus, password, userCategory 
	 * - Return Type : boolean 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 21/9/2019 
	 * - Description : to register a new user
	 * 
	 * @throws UserException
	 * @throws ConnectException 
	 ********************************************************************************************************/

	public boolean userRegistration(UserDTO newuser) throws UserException, ConnectException {
		boolean regStatus = false;
		try {
			regStatus = user.userRegistration(newuser);
		} catch (UserException e) {
			throw e;
		}
		return regStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogin 
	 * - Input Parameters : userID, password 
	 * - Return Type : boolean 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 21/9/2019 
	 * - Description : to login a user
	 * 
	 * @throws Exception
	 ********************************************************************************************************/

	public boolean userLogin(UserDTO existingUser) throws Exception {
		boolean userLoginStatus = false;
		try {
			userLoginStatus = user.userLogin(existingUser);
		} catch (UserException e) {
			throw new Exception (e.getMessage());
		}
		return userLoginStatus;
	}

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout 
	 * - Input Parameters : userID
	 * - Return Type : boolean 
	 * - Throws :UserExecution 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 21/9/2019 
	 * - Description : to logout a user
	 * 
	 * @throws UserException
	 ********************************************************************************************************/

	@Override
	public boolean userLogout(UserDTO userLoggingOut) throws UserException, ConnectException {
		boolean userLogoutStatus = false;
		try {
			userLogoutStatus = user.userLogout(userLoggingOut);
			} catch (UserException e) {
			throw e;
		}
		return userLogoutStatus;
	}
	
	// ------------------------ GreatOutdoor Application --------------------------
			/*******************************************************************************************************
			 * - Function Name : userFetch - Input Parameters : userID
			 *- Return Type :User
			 *  Throws :UserException - Author :AMAN - Creation Date :
			 * 21/9/2019 - Description : to fetch a  user
			 * @throws UserException 
			
			 ********************************************************************************************************/
		public UserDTO fetchUser(String userId) throws UserException
		{
			UserDTO loggedUser = user.fetchUser(userId);
			return loggedUser;
		}
}
		
