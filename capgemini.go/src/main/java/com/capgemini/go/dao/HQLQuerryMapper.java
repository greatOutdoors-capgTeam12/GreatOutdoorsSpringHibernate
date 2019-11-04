package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	
	public static final String USER_ID_EXISTS = "FROM UserEntity WHERE userId =:idExist";
	public static final String USER_CATEGORY = "FROM UserEntity WHERE userCategory =:catgCorrect";
	public static final String USER_PASSWORD_CHECK = "select userPassword FROM UserEntity WHERE userId =:correctUser";
	public static final String CHANGE_ACTIVE_STATUS = "UPDATE UserEntity SET userActiveStatus=1 WHERE userId =:userLoggin";
	
	
	
	
	
	
	public static final String VALIDATE_NUMBER_EMAIL = "SELECT COUNT(*) FROM UserEntity WHERE userNumber =:existNum OR userMail =:existMail";


}
