package com.tanmay.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
		info=@Info(
				title="Blog Application Apis",
				description = "DOing Crud Operation",
				summary = "This will contain all the blog application api..",
				termsOfService = "T&C",
				contact = @Contact(
						name="Tanmay",
						email = "tanmaybonde2002@gmail.com"
						),
				license = @License(
						name = "License"
						),
				version = "v1"
				
				),
		
				 security=@SecurityRequirement(
						 name="auth"
						 
						 )
		)

@SecurityScheme(
		name="auth",
		in=SecuritySchemeIn.HEADER,
		type=SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		description = "security desc"
	
		)
	
public class SwaggerConfig {
	
	
}
