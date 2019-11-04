package com.capgemini.go.dao;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.UserException;

@Component
public interface UserDao {

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userRegistration - Input Parameters : userID, userName,
	 * userMail, userNumber, activeStatus, password, userCategory - Return Type :
	 * boolean - Throws :UserException - Author:AMAN - Creation Date : 21/9/2019 -
	 * Description : to register a new user
	 * 
	 * @throws ConnectException
	 * @throws SQLException
	 ********************************************************************************************************/

	boolean userRegistration(UserDTO user) throws UserException, ConnectException;

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogin - Input Parameters : userID, password - Return
	 * Type : boolean - Throws :UserException - Author : AMAN - Creation Date
	 * :21/9/2019 - Description : to login a user
	 * 
	 * @throws UserException
	 * @throws Exception
	 ********************************************************************************************************/
	boolean userLogin(UserDTO user) throws UserException, Exception;

	// ------------------------ GreatOutdoor Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : userLogout - Input Parameters : userID - Return Type :
	 * boolean - Throws :UserException - Author : AMAN - Creation Date : 21/9/2019 -
	 * Description : to logout a user
	 * 
	 * @throws SQLException
	 * @throws ConnectException
	 ********************************************************************************************************/

	boolean userLogout(UserDTO user) throws UserException,  ConnectException;
	
	UserDTO fetchUser(String userId) throws UserException;

}
