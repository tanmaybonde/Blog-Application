package com.tanmay.blog.services;

import java.util.List;

import com.tanmay.blog.entities.Post;
import com.tanmay.blog.payloads.PostDto;
import com.tanmay.blog.payloads.PostResponse;

public interface PostService {

	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
//	delete
	
	void deletePost(Integer postId);
	
//	get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortby,String sortDir);

//	get Single Post
	PostDto getPostById(Integer postId);
	
//	get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
//	get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
//	search Post
	List<PostDto> searchPost(String keyword);
	
	
}
