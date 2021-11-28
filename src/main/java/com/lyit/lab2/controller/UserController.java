package com.lyit.lab2.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyit.lab2.model.UserRest;
import com.lyit.lab2.model.request.UpdateUserDetailsRequestModel;
import com.lyit.lab2.model.request.UserDetailsRequestModel;
import com.lyit.lab2.service.WelcomeService;

@RestController
@RequestMapping("/users")
public class UserController {
	
Map<String, UserRest> users;
	
    private WelcomeService welcomeService;

    public UserController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam(defaultValue = "Stranger") String name) {
        return welcomeService.getWelcomeMessage(name);
    }
    @GetMapping(path="/{userId}",
    		produces = {
    				MediaType.APPLICATION_XML_VALUE,
    				MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
    	
    	if(users.containsKey(userId)) {
    		return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
    	}
    }
    
    @PostMapping(consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE}, 
    		produces = {
    				MediaType.APPLICATION_XML_VALUE,
    				MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
    	UserRest returnValue = new UserRest();
    	returnValue.setEmail(userDetails.getEmail());
    	returnValue.setFirstName(userDetails.getFirstName());
    	returnValue.setLastName(userDetails.getLastName());
    	
    	String userId = UUID.randomUUID().toString();
    	returnValue.setUserId(userId);
    	
    	if(users == null) users = new HashMap<String, UserRest>();
    	users.put(userId, returnValue);
    	
    	return returnValue;
    }
    
    @PutMapping(path="/{userId}", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE}, 
    		produces = {
    				MediaType.APPLICATION_XML_VALUE,
    				MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails) {
    	
    	UserRest storedUserDetails = users.get(userId);
    	storedUserDetails.setFirstName(userDetails.getFirstName());
    	storedUserDetails.setLastName(userDetails.getLastName());
    	
    	users.put(userId, storedUserDetails);
    	
    	return storedUserDetails;
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    	users.remove(id);
    	
    	return ResponseEntity.noContent().build();
    }
	

}
