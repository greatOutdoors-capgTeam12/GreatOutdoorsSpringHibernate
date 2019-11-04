package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	


	
	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId as ViewSalesReportByUserEntity.userId, order.orderInitiateTime as ViewSalesReportByUserEntity.date, order.orderId as ViewSalesReportByUserEntity.orderId, opm.productId as ViewSalesReportByUserEntity.productId, prod.productCategory as ViewSalesReportByUserEntity.productCategory, prod.price as ViewSalesReportByUserEntity.price FROM OrderEntity order JOIN OrderProductMapEntity opm ON order.orderId=opm.orderId JOIN ProductEntity prod ON opm.productId=prod.productId";
	
	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderEntity order JOIN OrderProductMapEntity opm ON order.orderId=opm.orderId JOIN ProductEntity prod ON opm.productId=prod.productId";
//	
	
	//public static final String SELECT_REVENUE_DATA="SELECT MONTH(`ORDER_INITIATE_TIME`), YEAR(`ORDER_INITIATE_TIME`), `PRODUCT_PRICE`, FROM`ORDER` INNER JOIN `ORDER_PRODUCT_MAP`USING(ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
	
	public static final String SELECT_REVENUE_DATA = "SELECT order.orderInitiateTime , prod.price  FROM OrderEntity order JOIN OrderProductMapEntity opm ON order.orderId=opm.orderId JOIN ProductEntity prod ON opm.productId=prod.productId";


	
	public static final String SELECT_DATA_FROM_DATABASE = "SELECT order.userId , order.orderInitiateTime , order.orderId , opm.productId , prod.productCategory , prod.price  FROM OrderEntity order JOIN OrderProductMapEntity opm ON order.orderId=opm.orderId JOIN ProductEntity prod ON opm.productId=prod.productId";

	//public static final String SELECT_DATA_FROM_DATABASE = "SELECT order, omp, prod  FROM OrderEntity order JOIN OrderProductMapEntity opm ON order.orderId=opm.orderId JOIN ProductEntity prod ON opm.productId=prod.productId";
//	
	//public static final String SELECT_DATA_FROM_DATABASE = "FROM OrderEntity";

	
	//"SELECT `ORDER_ID`, `USER_ID` , `ORDER_INITIATE_TIME` FROM ORDER
		//"SELECT `ORDER_ID`, `PRODUCT_ID` , `PRODUCT_UIN` FROM ORDER_PRODUCT_MAP
		//"SELECT  `PRODUCT_ID`,`PRODUCT_PRICE` , `PRODUCT_CATEGORY` FROM PRODUCT
		//, , `PRODUCT_UIN` ,  FROM `ORDER` INNER JOIN `ORDER_PRODUCT_MAP` USING (ORDER_ID) INNER JOIN `PRODUCT` USING (PRODUCT_ID)";
			

	public static final String IS_ORDER_PRESENT = "FROM OrderEntity WHERE orderId = :ordID";
	

	public static final String UPDATE_ORDER_PRODUCT_MAP="UPDATE OrderProductMapEntity opm SET opm.productStatus=0 WHERE ORDER_ID=:orderId ";

	
}
//INNER JOIN OrderProductMapPK as opm ON order.orderId = opm.orderId 