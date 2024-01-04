package com.tanmay.blog.services;

import java.util.List;

import com.tanmay.blog.payloads.CategoryDto;
import com.tanmay.blog.payloads.CategoryResponse;

public interface CategoryService {

	
//	Create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	
//  update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
//	delete
	public void deleteCategory(Integer categoryId);
	
//	get
	
	public CategoryDto getCategory(Integer categoryId);
	
//	get all
	
	CategoryResponse getCategories(Integer pageNumber,Integer pageSize);
	
}
