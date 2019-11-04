package com.capgemini.go.controller;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.service.GoAdminReportsService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

@RestController
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RequestMapping("/Reports")
public class GoAdminReportsController {

	@Autowired
	private GoAdminReportsService goAdminReportsService;

	// Getters and Setters

	public GoAdminReportsService getGoAdminReportsService() {
		return goAdminReportsService;
	}

	public void setGoAdminReportsService(GoAdminReportsService goAdminReportsService) {
		this.goAdminReportsService = goAdminReportsService;
	}

	@ResponseBody
	@GetMapping("/RevenueReports")
	public String getRevenueReports(ReportFormBean input) throws GoAdminException {

		String userId = input.retailerId;
		int categoryType = Integer.parseInt(input.reportType);
		String startDate = input.startDate;
		String endDate = input.endDate;
		JsonArray dataList = new JsonArray();
		try {

			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

			List<ViewSalesReportByUserDTO> list = goAdminReportsService.viewSalesReportByUserAndCategory(dentry, dexit,
					userId, categoryType);
			for (ViewSalesReportByUserDTO bean : list) {
				JsonObject dataObj = new JsonObject();
				dataObj.addProperty("userId", bean.getUserId());
				dataObj.addProperty("date", bean.getDate().toString());
				dataObj.addProperty("orderId", bean.getOrderId());
				dataObj.addProperty("productId", bean.getProductId());
				dataObj.addProperty("productCategory", Integer.toString(bean.getProductCategory()));
				dataObj.addProperty("productPrice", Double.toString(bean.getProductPrice()));
				dataList.add(dataObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList.toString();
	}

	@ResponseBody
	@GetMapping("/GrowthReports")
	public String getGrowthReports(ReportFormBean input) throws GoAdminException {

		int categoryType = Integer.parseInt(input.reportType);
		String startDate = input.startDate;
		String endDate = input.endDate;
		JsonArray dataList = new JsonArray();
		try {

			Date dentry = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date dexit = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

			List<ViewDetailedSalesReportByProductDTO> list = goAdminReportsService
					.viewDetailedSalesReportByProduct(dentry, dexit, categoryType);
			for (ViewDetailedSalesReportByProductDTO bean : list) {
				JsonObject dataObj = new JsonObject();
				if (categoryType == 1) {
					dataObj.addProperty("period", Month.of(bean.getPeriod() + 1).name());
				} else if (categoryType == 2) {
					dataObj.addProperty("period", "Q" + Integer.toString((bean.getPeriod()) + 1));
				} else {
					dataObj.addProperty("period", "YEAR:" + Integer.toString(bean.getPeriod()));
				}
				dataObj.addProperty("revenue", Double.toString(bean.getRevenue()));
				dataObj.addProperty("amountChange", Double.toString(bean.getAmountChange()));
				dataObj.addProperty("percentageGrowth", Double.toString(bean.getPercentageGrowth()));
				dataObj.addProperty("colorCode", bean.getCode());
				dataList.add(dataObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList.toString();
	}

}
