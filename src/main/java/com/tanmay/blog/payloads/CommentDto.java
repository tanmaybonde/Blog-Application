package com.tanmay.blog.payloads;

import com.tanmay.blog.entities.Post;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDto {
	

	private int id;
	
	private String content;
	
	
}
