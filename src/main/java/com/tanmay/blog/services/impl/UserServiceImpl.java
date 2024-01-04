package com.tanmay.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.tanmay.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanmay.blog.config.AppContants;
import com.tanmay.blog.entities.Role;
import com.tanmay.blog.entities.User;
import com.tanmay.blog.payloads.UserDto;
import com.tanmay.blog.payloads.UserResponse;
import com.tanmay.blog.repositories.RoleRepo;
import com.tanmay.blog.repositories.UserRepo;
import com.tanmay.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	public ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoTouser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userTodeto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id ",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		UserDto userdto1=this.userTodeto(updatedUser);
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","Id", userId));
		return this.userTodeto(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber,Integer pageSize) {
		
		// TODO Auto-generated method stub
		
		Pageable p=PageRequest.of(pageNumber,pageSize);
		
		Page<User> pageUser=this.userRepo.findAll(p);
		
		List<User> users=pageUser.getContent();
		
		List<UserDto> userDetos=users.stream().map(user->this.userTodeto(user)).collect(Collectors.toList());
		
		UserResponse userResponse=new UserResponse();
		userResponse.setContent(userDetos);
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getTotalElements());;
		userResponse.setTotalPages(pageUser.getTotalPages());
		userResponse.setLastPage(pageUser.isLast());
		
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		this.userRepo.delete(user);

	}
	
	private User dtoTouser(UserDto userDto) {
//		this will automatically convert 
		User user=this.modelmapper.map(userDto,User.class);
		
//		this is the manual process for converting onee model into another
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
		
	}
	
	public UserDto userTodeto(User user) {
		
		UserDto userdto=this.modelmapper.map(user,UserDto.class);
		
		
//		UserDto userdto=new UserDto();
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
//		get user
		User user = this.modelmapper.map(userDto, User.class);
		
		
//		encryt password;
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppContants.NORMAL_USER).get();
		
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		
		return this.modelmapper.map(newUser, UserDto.class);
	}
}
