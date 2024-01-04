package com.tanmay.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.blog.config.AppContants;
import com.tanmay.blog.payloads.ApiResponse;
import com.tanmay.blog.payloads.CategoryDto;
import com.tanmay.blog.payloads.CategoryResponse;
import com.tanmay.blog.payloads.UserDto;
import com.tanmay.blog.services.CategoryService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/categories")
public class CategoryControllers {
	
	@Autowired
	private CategoryService categoryService;
	
//	post
	@PostMapping("/") 
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categorydto)
	{
		CategoryDto createCategoryDto=this.categoryService.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
	}
	
//	Put
	@PutMapping("/{CategoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categorydto,@PathVariable("CategoryId") Integer CategoryId)
	{
		CategoryDto UpdateCategory=this.categoryService.updateCategory(categorydto, CategoryId);
		return ResponseEntity.ok(UpdateCategory);
	}
	
//	Delete Mapping
	@DeleteMapping("/{CategoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("CategoryId") Integer CategoryId)
	{
		this.categoryService.deleteCategory(CategoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Successfully Deleted",true),HttpStatus.OK);
	}
	
	// Get -user get
		@GetMapping("/")
		public ResponseEntity<CategoryResponse> getAllCategories(
				@RequestParam(value = "pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
				@RequestParam(value="pageSize",defaultValue = AppContants.PAGE_SIZE,required = false) Integer pageSize
				)
		{
			return ResponseEntity.ok(this.categoryService.getCategories(pageNumber,pageSize));
		}
		
		
		
		
		@GetMapping("/{CategoryId}")
		public ResponseEntity<CategoryDto> getCategory(@PathVariable("CategoryId") Integer CatId)
		{
			return ResponseEntity.ok(this.categoryService.getCategory(CatId));
		}
		
	
}
