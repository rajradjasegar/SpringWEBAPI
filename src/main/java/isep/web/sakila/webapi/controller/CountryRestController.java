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

import isep.web.sakila.webapi.model.CountryWO;
import isep.web.sakila.webapi.service.CountryService;

@RestController
public class CountryRestController
{

	@Autowired
	CountryService countryService;

	private static final Log log = LogFactory.getLog(CountryRestController.class);

	@RequestMapping(value = "/country/", method = RequestMethod.GET)
	public ResponseEntity<List<CountryWO>> listAllCountrys()
	{
		List<CountryWO> countries = countryService.findAllCountries();
		if (countries.isEmpty())
		{
			return new ResponseEntity<List<CountryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CountryWO>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryWO> getCountry(@PathVariable("id") int id)
	{
		System.out.println("Fetching Country with id " + id);
		CountryWO staffWO = countryService.findById(id);
		if (staffWO == null)
		{
			System.out.println("Country with id " + id + " not found");
			return new ResponseEntity<CountryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CountryWO>(staffWO, HttpStatus.OK);
	}

	// -------------------Create a Country----------------------------------

	@RequestMapping(value = "/country/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCountry(@RequestBody CountryWO countryWO, UriComponentsBuilder ucBuilder)
	{
		System.out.println("Creating Country " + countryWO.getcountryName());

		countryService.saveCountry(countryWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/country/{id}").buildAndExpand(countryWO.getCountryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/countryUpdate/", method = RequestMethod.POST)
	public ResponseEntity<CountryWO> updateCountry(@RequestBody CountryWO countryWO, UriComponentsBuilder ucBuilder)
	{
		log.error(String.format("Updating Country %s ", countryWO.getCountryId()));
		CountryWO currentCountry = countryService.findById(countryWO.getCountryId());

		if (currentCountry == null)
		{
			System.out.println("Country with id " + countryWO.getCountryId() + " not found");
			return new ResponseEntity<CountryWO>(HttpStatus.NOT_FOUND);
		}

		currentCountry.setcountryName(countryWO.getcountryName());		
		countryService.updateCountry(currentCountry);

		return new ResponseEntity<CountryWO>(currentCountry, HttpStatus.OK);
	}

	@RequestMapping(value = "/countryDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<CountryWO> deleteCountry(@PathVariable("id") int id)
	{

		System.out.println("Fetching & Deleting Country with id " + id);

		CountryWO staffWO = countryService.findById(id);
		if (staffWO == null)
		{
			System.out.println("Unable to delete. Country with id " + id + " not found");
			return new ResponseEntity<CountryWO>(HttpStatus.NOT_FOUND);
		}

		countryService.deleteCountryById(id);
		return new ResponseEntity<CountryWO>(HttpStatus.NO_CONTENT);
	}
}
