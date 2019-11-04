package com.capgemini.go.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;

	// Getters and Setters

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseBody
	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> requestData) 
	{

	    String userId = requestData.get("userId").toString();
	    String password = requestData.get("password").toString();
	    UserDTO existUser = new UserDTO(null, userId, null, password, 0L, 2,false);
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode dataResponse = mapper.createObjectNode();
	    boolean result = false;
	    String userFetched = null;
		try {
			result = userService.userLogin(existUser);
			if(result)
			{
				UserDTO loggedUser = userService.fetchUser(userId);
				userFetched = mapper.writeValueAsString(loggedUser);
				dataResponse = mapper.readTree(userFetched);
			}
		}
		catch(Exception exp)
		{
			((ObjectNode) dataResponse).put("Error :",exp.getMessage());
		}
		
		return dataResponse.toString();
		
	}
}
