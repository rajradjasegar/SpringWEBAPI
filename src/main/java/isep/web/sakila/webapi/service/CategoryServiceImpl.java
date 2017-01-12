package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.CategoryRepository;
import isep.web.sakila.jpa.entities.Category;
import isep.web.sakila.webapi.model.CategoryWO;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	private static final Log log = LogFactory.getLog(CategoryServiceImpl.class);

	public List<CategoryWO> findAllCategories()
	{
		List<CategoryWO> categories = new LinkedList<CategoryWO>();

		for (Category category : categoryRepository.findAll())
		{
			categories.add(new CategoryWO(category));
			log.debug("Adding " + category);
		}

		return categories;
	}

	public CategoryWO findById(byte id)
	{
		log.debug(String.format("Looking for user by Id %s", id));
		Category category = categoryRepository.findOne(id);

		if (category != null)
		{
			return new CategoryWO(category);
		}
		return null;
	}

	public void saveCategory(CategoryWO categoryWO)
	{
		Category category = new Category();
		category.setName(categoryWO.getcategoryName());
		category.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		categoryRepository.save(category);
	}

	public void updateCategory(CategoryWO categoryWO)
	{
		Category category2update = categoryRepository.findOne((byte)categoryWO.getCategoryId());
		category2update.setName(categoryWO.getcategoryName());
		category2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		categoryRepository.save(category2update);
	}

	@Override
	public void deleteCategoryById(byte id)
	{
		categoryRepository.delete(id);
	}

}
