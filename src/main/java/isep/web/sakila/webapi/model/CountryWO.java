package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Country;

public class CountryWO extends WebObject
{

	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int CountryId;
	protected String countryName;

	public CountryWO()
	{
		super();
	}

	public CountryWO(int CountryId, String countryName)
	{
		super();
		this.CountryId = CountryId;
		this.countryName = countryName;
	}

	public CountryWO(final Country Country)
	{
		super();
		this.CountryId = Country.getCountryId();
		this.countryName = Country.getCountry();
	}

	public int getCountryId()
	{
		return CountryId;
	}

	public String getcountryName()
	{
		return countryName;
	}

	public void setCountryId(int CountryId)
	{
		this.CountryId = CountryId;
	}

	public void setcountryName(String countryName)
	{
		this.countryName = countryName;
	}

	@Override
	public String toString()
	{
		return "Country [id=" + this.CountryId + ", Country=" + this.countryName + "]";
	}
}
