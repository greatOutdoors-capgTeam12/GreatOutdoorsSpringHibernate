package com.capgemini.go.dao;

public class HQLQuerryMapper {

	public static final String GET_ALL_PRODUCTS = "FROM ProductEntity prod WHERE prod.quantity >= 0 ORDER BY prod.productName";
	

	public static final String USER_ID_EXISTS = "FROM UserEntity WHERE userId =:idExist";
	public static final String USER_CATEGORY = "FROM UserEntity WHERE userCategory =:catgCorrect";
	public static final String USER_PASSWORD_CHECK = "select userPassword FROM UserEntity WHERE userId =:correctUser";
	public static final String CHANGE_ACTIVE_STATUS = "UPDATE UserEntity SET userActiveStatus=1 WHERE userId =:userLoggin";
	public static final String GET_PRODUCT_QTY_FROM_DB = "from ProductEntity prod where prod.productId = :product_id";
	public static final String CART_ITEM_QTY_FOR_PRODUCT_ID="FROM CartItemEntity cart where cart.productId =:productId AND cart.retailerId =:retailerId";
	public static final String GET_PRODUCT_DETAILS_FROM_PRODUCT_UIN_MAP="from ProductUinMapEntity prodUinMap where prodUinMap.productUniqueIdentity.productId=: product_id AND  prodUinMap.productIsPresent=:prodispresent";
	public static final String UPDATE_CART = "update CartItemEntity cart set cart.quantity= :quantity where cart.productId= :product_id AND cart.retailerId = :retailerId";
	public static final String GET_CART_ITEMS_OF_RETAILER ="from CartItemEntity cart where cart.retailerId= :retailer_id";
	public static final String UPDATE_PRODUCT_UIN_MAP="update ProductUinMapEntity prodUinMapEnt set prodUinMapEnt.productIsPresent=:0 where prodUinMap.productUniqueIdentity.productId=: product_id";
	public static final String VALIDATE_NUMBER_EMAIL = "SELECT COUNT(*) FROM UserEntity WHERE userNumber =:existNum OR userMail =:existMail";
	public static final String UPDATE_PRODUCT_ENTITY="update ProductEntity product set product.quantity=: product.quantity- CartItemEntity.quantity where productId=:productid";
	public static final String DELETE_ORDER="delete OrderEntity ord where ord.orderId=:orderid";
	public static final String DELETE_CART="delete CartItemEntity cart where cart.retailerId=:retailerid";


	
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

