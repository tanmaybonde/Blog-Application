package com.tanmay.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tanmay.blog.entities.User;
import com.tanmay.blog.exceptions.ResourceNotFoundException;
import com.tanmay.blog.repositories.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		loading user from database by username
		User user  = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email :"+ username,0));
		
		
		
		return user;
	}

}
