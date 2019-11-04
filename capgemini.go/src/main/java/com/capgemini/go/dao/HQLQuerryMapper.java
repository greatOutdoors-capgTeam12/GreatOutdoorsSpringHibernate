package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductDTO prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	

	public static final String USER_ID_EXISTS = "FROM UserEntity WHERE userId =:idExist";
	public static final String USER_CATEGORY = "FROM UserEntity WHERE userCategory =:catgCorrect";
	public static final String USER_PASSWORD_CHECK = "select userPassword FROM UserEntity WHERE userId =:correctUser";
	public static final String CHANGE_ACTIVE_STATUS = "UPDATE UserEntity SET userActiveStatus=1 WHERE userId =:userLoggin";
	
	
	
	
	
	
	public static final String VALIDATE_NUMBER_EMAIL = "SELECT COUNT(*) FROM UserEntity WHERE userNumber =:existNum OR userMail =:existMail";


	
	public static final String IS_ORDER_PRESENT = "FROM OrderDTO WHERE orderId = :ordID";
	

	public static final String UPDATE_ORDER_PRODUCT_MAP="UPDATE OrderProductMapDTO opm SET opm.productStatus=0 WHERE ORDER_ID=:orderId ";

	
	
	
	
	//GOADMIN REPORTS HQL QUERRY
	public static final String SELECT_REVENUE_DATA = "SELECT order.orderInitiateTime , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
	

	
	public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";

	
	
}


	
	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId as ViewSalesReportByUserEntity.userId, order.orderInitiateTime as ViewSalesReportByUserEntity.date, order.orderId as ViewSalesReportByUserEntity.orderId, opm.productId as ViewSalesReportByUserEntity.productId, prod.productCategory as ViewSalesReportByUserEntity.productCategory, prod.price as ViewSalesReportByUserEntity.price FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
	
	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
//	
	
	//public static final String SELECT_REVENUE_DATA="SELECT MONTH(`ORDER_INITIATE_TIME`), YEAR(`ORDER_INITIATE_TIME`), `PRODUCT_PRICE`, FROM`ORDER` INNER JOIN `ORDER_PRODUCT_MAP`USING(ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
	
//	public static final String SELECT_REVENUE_DATA = "SELECT order.orderInitiateTime , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
//	
//
//	
//	public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";

	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order, omp, prod  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
//	
	//public static final String SELECT_DATA_FROM_DATABASE = "FROM OrderDTO";

	
	//"SELECT `ORDER_ID`, `USER_ID` , `ORDER_INITIATE_TIME` FROM ORDER
		//"SELECT `ORDER_ID`, `PRODUCT_ID` , `PRODUCT_UIN` FROM ORDER_PRODUCT_MAP
		//"SELECT  `PRODUCT_ID`,`PRODUCT_PRICE` , `PRODUCT_CATEGORY` FROM PRODUCT
		//, , `PRODUCT_UIN` ,  FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
			

	
	


