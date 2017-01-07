package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Film;

public class CustomerWO {

	private static final long	serialVersionUID	= -1377067679473844279L;
	
	protected int customerId;
	protected String firstName;
	protected String lastName;
	
	public CustomerWO()
	{
		super();
	}
	
	public CustomerWO(int customerId, String firstName, String lastName)
	{
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public CustomerWO(final Customer customer)
	{
		super();
		this.customerId = customer.getCustomerId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
	}

	

	public int getCustomerId()
	{
		return customerId;
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}

	

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}
	
	public void setFirstName (String firstName)
	{
		this.firstName= firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return "Customer [id=" + this.customerId + ", First Name =" + this.firstName+ ", Last Name =" + this.lastName+ "]";
	}
}
