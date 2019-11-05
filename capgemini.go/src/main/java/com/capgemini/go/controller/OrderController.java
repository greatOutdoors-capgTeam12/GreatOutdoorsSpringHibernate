package com.capgemini.go.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.service.OrderAndCartService;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Orders")
public class OrderController {

	@Autowired
	private OrderAndCartService orderAndCartService;

	// Getters and Setters

	public OrderAndCartService getOrderAndCartService() {
		return orderAndCartService;
	}

	public void setOrderAndCartService(OrderAndCartService orderAndCartService) {
		this.orderAndCartService = orderAndCartService;
	}

	@ResponseBody
	@PostMapping("/AddItemServlet")
	public String addItemtoCart(@RequestBody Map<String, Object> requestData) {
		CartDTO cartItem = new CartDTO();
		System.out.println(requestData);
		cartItem.setUserId(requestData.get("addItemuserId").toString());
		cartItem.setProductId(requestData.get("addItemProdId").toString());
		cartItem.setQuantity(Integer.valueOf(requestData.get("addItemProdQty").toString()));
		boolean cart;
		JsonObject cartObj = null;
		try {
			cart = orderAndCartService.addItemToCart(cartItem);
			if (cart) {
				cartObj = new JsonObject();
				cartObj.addProperty("message", "Item added to cart scuccessfully");
			} else {
				cartObj = new JsonObject();
				cartObj.addProperty("message", "Unable to add item to cart");
			}
		} catch (RetailerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartObj.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/BuyNowServlet", method = RequestMethod.POST)
	public String placeOrder(@RequestBody Map<String, Object> requestData) {
		OrderDTO ord = new OrderDTO();
		ord.setOrderId(String.valueOf(requestData.get("placeOrderCustId")));
		ord.setAddressId(String.valueOf(requestData.get("placeOrderAddrId")));
		boolean order = true;
		JsonObject orderObj = null;
		try {
			order = orderAndCartService.registerOrder(ord);
			if (order) {
				orderObj = new JsonObject();
				orderObj.addProperty("message", "Order placed scuccessfully");
			} else {
				orderObj = new JsonObject();
				orderObj.addProperty("message", "Unable to place Order");
			}
		} catch (RetailerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderObj.toString();
	}

}