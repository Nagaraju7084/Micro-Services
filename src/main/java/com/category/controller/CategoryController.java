package com.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.category.bean.CategoryBean;
import com.category.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="Category Service", description = "Rest Apis for Category Resource")
@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	/*
	 * @Value("${todays.gold.price}") private String todaysGoldPrice;
	 */
	 
	@Operation(summary = "Add Category", description = "Adding Category Data")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PostMapping("/category")
	public ResponseEntity<? extends Object> addCategory(@RequestBody CategoryBean catBean){
		try {
			catBean = catService.save(catBean);			
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(catBean, HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public ResponseEntity<? extends Object> getAllCategories(){
		//log.info("todays gold rate is:\t"+todaysGoldPrice);
		try {
			List<CategoryBean> catBeanList = catService.findAll();		
			return new ResponseEntity<Object>(catBeanList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/category/{catId}")
	public ResponseEntity<? extends Object>  deleteCategory(@PathVariable("catId") int catId){
		log.info("category id is:\t"+catId);;
		try {
			List<CategoryBean> catBeanList = catService.deleteCategory(catId);
			return new ResponseEntity<Object>(catBeanList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/category/{catId}")
	public ResponseEntity<? extends Object>  categoryById(@PathVariable("catId") int catId){
		log.info("category id is:\t"+catId);;
		try {
			CategoryBean catBean = catService.findCatById(catId);
			return new ResponseEntity<Object>(catBean, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
