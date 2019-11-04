package com.capgemini.go.utility;

import java.net.ConnectException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.capgemini.go.dao.HQLQuerryMapper;
import com.capgemini.go.dao.UserDaoImpl;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.exception.AuthenticationException;

public class Authentication {

	/*******************************************************************************************************
	 * - Function Name : encrypt - Input Parameters : password,key Return Type
	 * :String Throws : AuthenticationException- Author : Agnibha Creation Date :
	 * 21/9/2019 - Description : to encrypt the password
	 ********************************************************************************************************/

	public static String encrypt(String strClearText, String strKey) throws AuthenticationException {
		String strData = "";

		try {

			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(strClearText.getBytes());
			strData = new String(encrypted);

		} catch (Exception e) {

			throw new AuthenticationException(e.getMessage());
		}
		return strData;
	}

	/*******************************************************************************************************
	 * - Function Name : decrypt - Input Parameters : password,key Return Type
	 * :String Throws : AuthenticationException- Author : Agnibha Creation Date :
	 * 21/9/2019 - Description : to Decrypt the password
	 ********************************************************************************************************/
	public static String decrypt(String strEncrypted, String strKey) throws AuthenticationException {
		String strData = "";

		try {

			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted = cipher.doFinal(strEncrypted.getBytes());
			strData = new String(decrypted);

		} catch (Exception e) {

			throw new AuthenticationException(e.getMessage());
		}
		return strData;
	}

	/*******************************************************************************************************
	 * - Function Name : authenticate - Input Parameters : String userId,int
	 * category, Connection connection Return Type :boolean Throws :- Author :
	 * Agnibha Creation Date : 21/9/2019 - Description : to authenticate the user
	 * 
	 * @throws ConnectException
	 ********************************************************************************************************/
	public static boolean authenticateUser(String userId, int category) throws ConnectException {
		boolean authenticationStatus = false;

		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Query validateUser = (Query) session.createQuery(HQLQuerryMapper.USER_ID_EXISTS);
			validateUser.setParameter("idExist", userId);
			List<UserDTO> existUser = (List<UserDTO>) validateUser.list();

			for (UserDTO u : existUser) {
				if (existUser != null) {
					System.out.println(AuthenticationConstants.user_not_exists);
					return authenticationStatus;
				}

				if (u.isUserActiveStatus() != true) {
					System.out.println(AuthenticationConstants.login_error);
					return authenticationStatus;
				}
				if (u.getUserCategory() != category) {
					System.out.println(AuthenticationConstants.access_error);
					return authenticationStatus;
				}
				authenticationStatus = true;
				System.out.print(AuthenticationConstants.welcome);

			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			GoLog.getLogger(UserDaoImpl.class).error(e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return authenticationStatus;
	}

}
