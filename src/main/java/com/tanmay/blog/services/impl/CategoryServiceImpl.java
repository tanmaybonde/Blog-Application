package com.tanmay.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tanmay.blog.entities.Category;
import com.tanmay.blog.exceptions.ResourceNotFoundException;
import com.tanmay.blog.payloads.CategoryDto;
import com.tanmay.blog.payloads.CategoryResponse;
import com.tanmay.blog.payloads.PostResponse;
import com.tanmay.blog.repositories.CategoryRepo;
import com.tanmay.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelmapper.map(categoryDto, Category.class);
		Category addedcat=this.categoryRepo.save(cat);
		return this.modelmapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedcat=this.categoryRepo.save(cat);
				
		return this.modelmapper.map(updatedcat, CategoryDto.class) ;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId ));
		this.categoryRepo.delete(cat); 
		

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
		return this.modelmapper.map(cat, CategoryDto.class);
	} 

	@Override
	public CategoryResponse getCategories(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable p=PageRequest.of(pageNumber,pageSize);
		
		Page<Category> pageCategory=this.categoryRepo.findAll(p);
		
		
		List<Category> categories=pageCategory.getContent();
		
		List<CategoryDto> categorydto=categories.stream().map((cat)-> this.modelmapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		CategoryResponse categoryResponse=new CategoryResponse();
		categoryResponse.setContent(categorydto);
		categoryResponse.setPageNumber(pageCategory.getNumber());
		categoryResponse.setPageSize(pageCategory.getSize());
		categoryResponse.setTotalElements(pageCategory.getTotalElements());;
		categoryResponse.setTotalPages(pageCategory.getTotalPages());
		categoryResponse.setLastPage(pageCategory.isLast());
		
		return categoryResponse;
	}

}
