package com.capgemini.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.exception.ProductException;
import com.capgemini.go.service.ProductService;

@Controller
@RequestMapping("/all-products")
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
	@RequestMapping(method = RequestMethod.GET )
	public String getAllProducts() throws ProductException {
		List<ProductDTO> products = productService.viewAllProducts();
		return products.toString();
	}
}
