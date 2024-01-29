package com.tanmay.blog.controllers;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.blog.exceptions.ApiException;
import com.tanmay.blog.payloads.JwtAuthRequest;
import com.tanmay.blog.payloads.JwtAuthResponse;
import com.tanmay.blog.payloads.UserDto;
import com.tanmay.blog.security.JwtTokenHelper;
import com.tanmay.blog.services.UserService;

import jakarta.validation.Valid;

//@RestController
//@RequestMapping("/api/v1/auth/")
//public class AuthController {
//	
//	
//	@Autowired
//	private JwtTokenHelper jwtTokenHelper;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	
//	@PostMapping("/login")
//	public ResponseEntity<JwtAuthResponse> createToken(
//			@RequestBody JwtAuthRequest request
//			)
//	{
//		this.authenticate(request.getUsername(),request.getPassword());
//		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
//	
//		String token=this.jwtTokenHelper.generateToken(userDetails);
//		
//		JwtAuthResponse response=new JwtAuthResponse();
//		response.setToken(token);
//		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
//	}
//	
////	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
////	    authenticate(request.getUsername(), request.getPassword());
////		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
////		String token = this.jwtTokenHelper.generateToken(userDetails);
////		JwtAuthResponse response = new JwtAuthResponse();
////		response.setToken(token);
////		System.out.println("Response Token: " + token);
////		return ResponseEntity.ok(response);
////	}
//
//
//
//	private void authenticate(String username, String password) {
//		// TODO Auto-generated method stub
//		
//		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
//		
//		
//		this.authenticationManager.authenticate(authenticationToken);
//		
//	}


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private UserService userService;


    @Autowired
    private JwtTokenHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new ApiException(" Invalid Username or Password  !!");
        }

    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto)
    {
    	UserDto registerNewUser = this.userService.registerNewUser(userDto);
    	return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
    }
    
    
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }

}
