package com.capgemini.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.service.RetailerInventoryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/RetailerInventory")
public class RetailerInventoryController {
	@Autowired
	private RetailerInventoryService retailerInventoryService;

	public RetailerInventoryService getRetailerInventoryService() {
		return retailerInventoryService;
	}

	public void setRetailerInventoryService(RetailerInventoryService retailerInventoryService) {
		this.retailerInventoryService = retailerInventoryService;
	}
		
	@ResponseBody
	@PostMapping("/ShelfTimeReport")
	public String getShelfTimeReport () {
		return null;
	}
	
	@ResponseBody
	@PostMapping("/DeliveryTimeReport")
	public String getDeliveryTimeReport () {
		return null;
	}
	
	@ResponseBody
	@PostMapping("/RetailerList")
	public String getRetailerList () {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		JsonArray retailerList = new JsonArray();
		try {
			List<RetailerInventoryBean> result = this.retailerInventoryService.getListOfRetailers();
			for (RetailerInventoryBean item : result) {
				JsonObject retailerObj = new JsonObject();
				retailerObj.addProperty ("retailerId", item.getRetailerId());
				retailerObj.addProperty("retailerName", item.getRetailerName());
				retailerList.add(retailerObj);
			}
		} catch (Exception error) {
			((ObjectNode) dataResponse).put("Error :", error.getMessage());
			return dataResponse.toString();
		}
		return retailerList.toString();
	}
}
