package com.tanmay.blog.payloads;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtAuthResponse {
	
	private String token;
	
	private UserDto user;

}
