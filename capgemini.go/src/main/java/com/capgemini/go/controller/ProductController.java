package com.capgemini.go.controller;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductException;
import com.capgemini.go.service.ProductService;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.InfoConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import netscape.javascript.JSObject;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Getters and Setters

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	public String getAllProducts() throws ProductException  {
	
		List<ProductDTO> products = productService.viewAllProducts();
	    JsonArray productList = new JsonArray();
	   
		
	    for(ProductDTO prod : products) {
	        JsonObject  productObj = new JsonObject();
	        productObj.addProperty("prodid", prod.getProductId());
			productObj.addProperty("prodName", prod.getProductName());
			productObj.addProperty("prodBrand", prod.getManufacturer());
			productObj.addProperty("prodSpec", prod.getSpecification());
			productObj.addProperty("prodDim", prod.getDimension());
			productObj.addProperty("prodQty", prod.getQuantity());
			productObj.addProperty("prodPrice", prod.getPrice());
			productObj.addProperty("prodColor", prod.getColour());
			productObj.addProperty("category", prod.getProductCategory());
			productObj.addProperty("isActive", true);
			productObj.addProperty("photoPath", "assets/images/products/" + prod.getProductId() + ".jpg");
			productList.add(productObj);
	    }
	
		return productList.toString();
	}
		
}
