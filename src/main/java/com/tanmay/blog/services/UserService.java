package com.tanmay.blog.services;

import java.util.List;

import com.tanmay.blog.payloads.UserDto;
import com.tanmay.blog.payloads.UserResponse;

public interface UserService {
	
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	UserResponse getAllUsers(Integer pageNumber,Integer pageSize);
	void deleteUser(Integer userId);
}
