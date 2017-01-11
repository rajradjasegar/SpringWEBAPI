package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.City;
import isep.web.sakila.jpa.entities.Country;

public class CityWO extends WebObject
{

	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int cityId;
	protected String cityName;
	protected int countryId;
	//protected String country;
	//protected Country countryOB;

	public CityWO()
	{
		super();
	}

	public CityWO(int cityId, String cityName, String country)
	{
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		//this.country = country;
		
	}

	public CityWO(final City city)
	{
		super();
		this.cityId = city.getCityId();
		this.cityName = city.getCity();
		this.countryId = city.getCountry().getCountryId();
	}

	public int getcountryId()
	{
		return countryId;
	}

	/*public Country getcountryOB()
	{
		return countryOB;
	}*/
	
	public int getCityId()
	{
		return cityId;
	}

	public String getcityName()
	{
		return cityName;
	}

	public void setcountryId(int countryId)
	{
		//this.countryOB = countryOB;
		this.countryId = countryId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public void setcityName(String cityName)
	{
		this.cityName = cityName;
	}

	@Override
	public String toString()
	{
		return "City [id=" + this.cityId + ", CityName" + this.cityName + ", CountryId=" + this.countryId + "]";
	}
}
