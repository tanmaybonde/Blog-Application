package com.tanmay.blog;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tanmay.blog.config.AppContants;
import com.tanmay.blog.entities.Role;
import com.tanmay.blog.repositories.RoleRepo;
import java.util.*;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("abc"));
		
		try {
			
			Role role=new Role();
			role.setId(AppContants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1=new Role();
			role1.setId(AppContants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles=List.of(role,role1);
			
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
		
			result.forEach(r -> {
				System.out.println(r.getName());
			});
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
