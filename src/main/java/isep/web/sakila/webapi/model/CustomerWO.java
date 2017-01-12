package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Film;

public class CustomerWO {

	private static final long	serialVersionUID	= -1377067679473844279L;
	
	protected int customerId;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected int address_id;
	protected int store_id;
	
	public CustomerWO()
	{
		super();
	}
	
	public CustomerWO(int customerId, String firstName, String lastName, String email, int address_id, int store_id)
	{
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address_id = address_id;
		this.store_id = store_id;
	}
	
	public CustomerWO(final Customer customer)
	{
		super();
		this.customerId = customer.getCustomerId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
		this.address_id = customer.getAddress().getAddressId();
		this.store_id = customer.getStore().getStoreId();
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	@Override
	public String toString() {
		return "CustomerWO [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", address_id=" + address_id + ", store_id=" + store_id + "]";
	}



}
