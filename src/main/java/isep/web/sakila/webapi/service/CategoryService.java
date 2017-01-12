package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.CategoryWO;

public interface CategoryService
{
	CategoryWO findById(byte id);

	void saveCategory(CategoryWO categoryWO);

	void updateCategory(CategoryWO categoryWO);

	void deleteCategoryById(byte id);

	List<CategoryWO> findAllCategories();

}
