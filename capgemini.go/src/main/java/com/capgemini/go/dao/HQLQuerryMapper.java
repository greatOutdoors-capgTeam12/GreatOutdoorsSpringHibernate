package com.capgemini.go.dao;

public class HQLQuerryMapper {


	public static final String USER_ID_EXISTS = "FROM UserDTO WHERE userId =:idExist";
	public static final String USER_CATEGORY = "FROM UserDTO WHERE userCategory =:catgCorrect";
	public static final String USER_PASSWORD_CHECK = "select userPassword FROM UserDTO WHERE userId =:correctUser";
	public static final String CHANGE_ACTIVE_STATUS = "UPDATE UserDTO SET userActiveStatus=1 WHERE userId =:userLoggin";
	
	
	
	
	
	
	public static final String VALIDATE_NUMBER_EMAIL = "SELECT COUNT(*) FROM UserDTO WHERE userNumber =:existNum OR userMail =:existMail";


	public static final String UPDATE_ORDER_PRODUCT_MAP="UPDATE OrderProductMapDTO opm SET opm.productStatus=0 WHERE ORDER_ID=:orderId ";

	
	
	
	
	//GOADMIN REPORTS HQL QUERRY
	public static final String SELECT_REVENUE_DATA = "SELECT order.orderInitiateTime , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";
	

	
	public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderDTO order JOIN OrderProductMapDTO opm ON order.orderId=opm.orderId JOIN ProductDTO prod ON opm.productId=prod.productId";

	
	//Cancel Order, product by Sales Rep
	public static final String IS_ORDER_PRESENT = "SELECT orderId FROM OrderEntity WHERE orderId = :orderID";
	
	public static final String IS_SALES_REP_ID_PRESENT = "FROM SalesRepEntity sre WHERE sre.userId = :userID";
	
	public static final String CHECK_ORDER_DISPATCH_STATUS = "SELECT ORDER_DISPATCH_STATUS FROM `ORDER` WHERE ORDER_ID= :orderID";
	
	public static final String GET_PRODUCT_MAP = "FROM OrderProductMapEntity WHERE orderId=:orderID";
	
	public static final String UPDATE_ORDER_PRODUCT_MAP_WITH_PRODUCT_UIN = "Update OrderProductMapEntity set productStatus= 0 where orderId=:orderID and productId=:productID and productUIN=:productUIN";
	
	public static final String GET_PRODUCT_QUANTITY = "SELECT COUNT(*) FROM OrderProductMapEntity WHERE productStatus = 1 AND orderId=:orderID AND productId=:productID";
	
	public static final String UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY = "Update OrderProductMapEntity set productStatus = 0 where orderId =:orderID AND productId =:productID";
	
	public static final String UPDATE_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY  = "UPDATE ORDER_PRODUCT_MAP SET PRODUCT_STATUS = 0 WHERE PRODUCT_UIN IN (SELECT * FROM (SELECT PRODUCT_UIN FROM ORDER_PRODUCT_MAP WHERE ORDER_ID = :orderID AND PRODUCT_ID = :productID LIMIT :quantity) AS L)";	
	
	public static final String GET_ORDER_PRODUCT_MAP_CANCEL_PROD_EQUAL_QUANTITY = "FROM OrderProductMapEntity WHERE orderId =:orderID AND productId =:productID AND productStatus = 0";
	
	public static final String GET_ORDER_PRODUCT_MAP_CANCEL_PROD_LESS_QUANTITY = "FROM OrderProductMapEntity as opme WHERE opme.productUIN IN (SELECT opme2.productUIN FROM OrderProductMapEntity as opme2 WHERE opme2.orderId =:orderID AND opme2.productId =:productID AND opme2.productStatus = 0)";
	
	public static final String SELECT_SALES_REP_TARGET = "SELECT target FROM SalesRepEntity sre WHERE sre.userId = :userID";
	
	public static final String GET_TARGET_STATUS = "SELECT targetStatus FROM SalesRepEntity sre WHERE sre.userId = :userID";
	
	public static final String SELECT_SALES_REP_BONUS = "SELECT bonus FROM SalesRepEntity sre WHERE sre.userId = :userID";
	
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
			

	
	


