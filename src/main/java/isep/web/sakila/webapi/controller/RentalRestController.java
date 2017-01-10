package isep.web.sakila.webapi.controller;

import java.text.ParseException;
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

import isep.web.sakila.webapi.model.RentalAndFilmWO;
import isep.web.sakila.webapi.model.RentalWO;
import isep.web.sakila.webapi.service.InventoryService;
import isep.web.sakila.webapi.service.RentalService;

@RestController
public class RentalRestController {

	@Autowired
	RentalService rentalService;

	@Autowired
	InventoryService inventoryService;

	private static final Log log = LogFactory.getLog(RentalRestController.class);

	// ------------------- ----------------------------------

	@RequestMapping(value = "/rental/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentalWO> getRental(@PathVariable("id") int id) {
		System.out.println("Fetching Rental with id " + id);
		RentalWO rentalWO = rentalService.findById(id);
		if (rentalWO == null) {
			System.out.println("rental with id " + id + " not found");
			return new ResponseEntity<RentalWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RentalWO>(rentalWO, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRentals/", method = RequestMethod.GET)
	public ResponseEntity<List<RentalWO>> listAllRentals() {
		List<RentalWO> rentals = rentalService.findAllRentals();
		if (rentals.isEmpty()) {
			return new ResponseEntity<List<RentalWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RentalWO>>(rentals, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRentalAndFilmByCustomerId/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<RentalAndFilmWO>> getRentalAndFilmByCustomerId(@PathVariable("id") int id) {

		List<RentalAndFilmWO> rentalAndFilm = rentalService.findByIdCustomer(id);
		if (rentalAndFilm.isEmpty()) {
			return new ResponseEntity<List<RentalAndFilmWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RentalAndFilmWO>>(rentalAndFilm, HttpStatus.OK);
	}
	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/createRental/", method = RequestMethod.POST)
	public ResponseEntity<Void> createRental(@RequestBody RentalWO rentalWO, UriComponentsBuilder ucBuilder)
			throws ParseException {
		System.out.println("Creating Rental " + rentalWO.getRentalId());
		if (rentalWO.getReturnDate() != null) {
			System.out.println("rental non nul");
		} else {
			System.out.println("rental nul");
		}
		rentalService.saveRental(rentalWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rental/{id}").buildAndExpand(rentalWO.getRentalId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteRental/{id}", method = RequestMethod.GET)
	public ResponseEntity<RentalWO> deleteRental(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Rental with id " + id);

		RentalWO rental = rentalService.findById(id);

		if (rental == null) {
			System.out.println("Unable to delete. rental with id " + id + " not found");
			return new ResponseEntity<RentalWO>(HttpStatus.NOT_FOUND);
		}
		rentalService.deleteRentalById(id);
		return new ResponseEntity<RentalWO>(HttpStatus.NO_CONTENT);
	}

}
