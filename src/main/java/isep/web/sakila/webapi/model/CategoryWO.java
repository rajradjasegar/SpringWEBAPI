package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Category;

public class CategoryWO extends WebObject
{

	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int categoryId;
	protected String categoryName;

	public CategoryWO()
	{
		super();
	}

	public CategoryWO(int categoryId, String categoryName)
	{
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public CategoryWO(final Category category)
	{
		super();
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getName();
	}

	public int getCategoryId()
	{
		return categoryId;
	}

	public String getcategoryName()
	{
		return categoryName;
	}

	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}

	public void setcategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	@Override
	public String toString()
	{
		return "Category [id=" + this.categoryId + ", CategoryNanem=" + this.categoryName + "]";
	}
}
