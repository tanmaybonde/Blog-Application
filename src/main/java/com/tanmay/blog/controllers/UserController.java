package com.tanmay.blog.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.blog.config.AppContants;
import com.tanmay.blog.payloads.ApiResponse;
import com.tanmay.blog.payloads.UserDto;
import com.tanmay.blog.payloads.UserResponse;
import com.tanmay.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	//POST - create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	//PUT- update Users
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{
		UserDto updateduser=this.userservice.updateUser(userDto, uid);
		return ResponseEntity.ok(updateduser);	
	}
	
	// Delete -delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userservice.deleteUser(uid);
//		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Successfully Deleted",true),HttpStatus.OK);
	}
	
	// Get -user get
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppContants.PAGE_SIZE ,required = false) Integer pageSize
			)
	{
		return ResponseEntity.ok(this.userservice.getAllUsers(pageNumber,pageSize));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid)
	{
		return ResponseEntity.ok(this.userservice.getUserById(uid));
	}
	
}
