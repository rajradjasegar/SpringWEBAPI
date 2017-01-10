package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.CategoryWO;

public interface CategoryService
{
	CategoryWO findById(int id);

	void saveCategory(CategoryWO categoryWO);

	void updateCategory(CategoryWO categoryWO);

	void deleteCategoryById(int id);

	List<CategoryWO> findAllCategories();

}
