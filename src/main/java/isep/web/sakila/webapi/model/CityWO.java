package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.City;
import isep.web.sakila.jpa.entities.Country;
import isep.web.sakila.webapi.model.CountryWO;
public class CityWO extends WebObject
{

	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int cityId;
	protected String cityName;
	protected int countryId;
	protected CountryWO countryWO;

	public CityWO()
	{
		super();
	}

	public CityWO(int cityId, String cityName, CountryWO countryWO)
	{
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.countryWO = countryWO;			
	}

	public CityWO(final City city)
	{
		super();
		this.cityId = city.getCityId();
		this.cityName = city.getCity();
		this.countryId = city.getCountry().getCountryId();
		//this.countryName = city.getCountry().getCountry();
		this.countryWO = new CountryWO(city.getCountry());
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
		this.countryId = countryId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}
	
	public CountryWO getCountry() {
		return countryWO;
	}

	public void setCountry(CountryWO countryWO) {
		this.countryWO = countryWO;
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
