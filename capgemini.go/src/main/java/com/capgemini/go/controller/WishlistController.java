package com.capgemini.go.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.WishlistDTO;
import com.capgemini.go.exception.WishlistException;
import com.capgemini.go.service.ProductService;
import com.capgemini.go.service.WishlistService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Wishlist")

public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	// Getters and Setters

	public WishlistService getWishlistService() {
		return wishlistService;
	}

	public void setProductService(ProductService productService) {
		this.wishlistService = wishlistService;;
	}
	
	
	@ResponseBody
	@GetMapping("/AddtoWishlist")
	boolean addProductToWishlist(WishlistDTO wishlist) throws WishlistException,ConnectException {
		
		return false;
	
//		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        
//	        
//
//			 
//
//	        StringBuffer jb = new StringBuffer();
//	          String line = null;
//	          try {
//	            BufferedReader reader = request.getReader();
//	            while ((line = reader.readLine()) != null)
//	              jb.append(line);
//	          } catch (Exception e) {  }
//	        Map<String,String> myMap = new HashMap<String, String>();
//
//	 
//
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        
//	        myMap = objectMapper.readValue(jb.toString(), HashMap.class);
//	        String userId = myMap.get("userid");
//	        String productId= myMap.get("prodid");
//	      
//	        
//
//	 
//
//	        
//	        response.setContentType("application/json");
//	        response.setHeader("Access-Control-Allow-Origin", "*");
//	        
//	        response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
//	        response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//	        ObjectMapper mapper = new ObjectMapper();
//	        JsonNode dataResponse = mapper.createObjectNode();
//	        boolean result=false;
//	        PrintWriter out =response.getWriter();
//	    
//	            
//	            RetailerService fav = new RetailerServiceImpl();
//	            FrequentOrderedDTO addFav = new FrequentOrderedDTO(userId ,productId);
//	                try {
//	                    WishlistEntity wishlistentity = null;
//						result = fav.addProductToWishlist(wishlistentity);
//	                
//	                if(result)
//	                {
//	                    ((ObjectNode) dataResponse).put("Success :","Product added to fav");
//	                }
//	                
//	            } catch ( Exception e) {
//	                
//	                ((ObjectNode) dataResponse).put("Error :",e.getMessage());
//	                
//	            }
//	            finally
//	            {
//	                out.print(dataResponse);
//	                out.close();
//	            }

//	}
}
}

