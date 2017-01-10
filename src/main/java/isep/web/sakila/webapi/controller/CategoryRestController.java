package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isep.web.sakila.webapi.model.CategoryWO;
import isep.web.sakila.webapi.service.CategoryService;

@RestController
public class CategoryRestController
{

	@Autowired
	CategoryService categoryService;

	private static final Log log = LogFactory.getLog(CategoryRestController.class);

	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryWO>> listAllCategories()
	{
		List<CategoryWO> categories = categoryService.findAllCategories();
		if (categories.isEmpty())
		{
			return new ResponseEntity<List<CategoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryWO>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryWO> getCategory(@PathVariable("id") int id)
	{
		System.out.println("Fetching Category with id " + id);
		CategoryWO staffWO = categoryService.findById(id);
		if (staffWO == null)
		{
			System.out.println("Category with id " + id + " not found");
			return new ResponseEntity<CategoryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryWO>(staffWO, HttpStatus.OK);
	}

	// -------------------Create a Category----------------------------------

	@RequestMapping(value = "/category/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@RequestBody CategoryWO categoryWO, UriComponentsBuilder ucBuilder)
	{
		System.out.println("Creating Category " + categoryWO.getcategoryName());

		categoryService.saveCategory(categoryWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(categoryWO.getCategoryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categoryUpdate/", method = RequestMethod.POST)
	public ResponseEntity<CategoryWO> updateCategory(@RequestBody CategoryWO categoryWO, UriComponentsBuilder ucBuilder)
	{
		log.error(String.format("Updating Category %s ", categoryWO.getCategoryId()));
		CategoryWO currentCategory = categoryService.findById(categoryWO.getCategoryId());

		if (currentCategory == null)
		{
			System.out.println("Category with id " + categoryWO.getCategoryId() + " not found");
			return new ResponseEntity<CategoryWO>(HttpStatus.NOT_FOUND);
		}

		currentCategory.setcategoryName(categoryWO.getcategoryName());
		categoryService.updateCategory(currentCategory);

		return new ResponseEntity<CategoryWO>(currentCategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categoryDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryWO> deleteCategory(@PathVariable("id") int id)
	{

		System.out.println("Fetching & Deleting Category with id " + id);

		CategoryWO staffWO = categoryService.findById(id);
		if (staffWO == null)
		{
			System.out.println("Unable to delete. Category with id " + id + " not found");
			return new ResponseEntity<CategoryWO>(HttpStatus.NOT_FOUND);
		}

		categoryService.deleteCategoryById(id);
		return new ResponseEntity<CategoryWO>(HttpStatus.NO_CONTENT);
	}
}
