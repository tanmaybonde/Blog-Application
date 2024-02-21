package com.tanmay.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tanmay.blog.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private int id; 
	
	@NotEmpty(message = "Name is Required")
	@Size(min=4,message = "User Must minimum of 4 Character")
	private String name;
	
	@NotEmpty(message = "Email is Required")
	@Email(message = "Enter the Valid Email Address!!!")
	private String email;
	
	@NotEmpty(message = "password is required")
	@Size(min=3,max=10,message = "Enter the Password min 3 character and max 10 char")
//	@Pattern annotation for enable the one pattern for the password 
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
	
	

}
