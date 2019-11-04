package com.capgemini.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/retailerInventory")
public class RetailerInventoryController {
	@RequestMapping(value = "/splash", method = RequestMethod.GET)
	public String printHello (ModelMap model) {
		model.addAttribute ("message", "Hello Spring MVC Framework!");
		return "hello";
	}
	
	@RequestMapping(value = "/shelfTimeReport", method = RequestMethod.POST)
	public String getShelfTimeReport () {
		return null;
	}
	
	@RequestMapping(value = "/deliveryTimeReport", method = RequestMethod.POST)
	public String getDeliveryTimeReport () {
		return null;
	}
	
	@RequestMapping(value = "/retailerList", method = RequestMethod.POST)
	public String getRetailerList () {
		return null;
	}
}
