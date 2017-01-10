package isep.web.sakila.webapi.controller;

import java.util.ArrayList;
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

import isep.web.sakila.webapi.model.InventoryWO;
import isep.web.sakila.webapi.model.RentalWO;
import isep.web.sakila.webapi.service.InventoryService;
import isep.web.sakila.webapi.service.RentalService;

@RestController
public class InventoryRestController {

	@Autowired
	InventoryService inventoryService;

	@Autowired
	RentalService rentalService;

	private static final Log log = LogFactory.getLog(InventoryRestController.class);

	// ------------------- ----------------------------------

	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InventoryWO> getInventory(@PathVariable("id") int id) {
		System.out.println("Fetching Inventory with id " + id);
		InventoryWO inventoryWO = inventoryService.findById(id);
		if (inventoryWO == null) {
			System.out.println("Inventory with id " + id + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InventoryWO>(inventoryWO, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInventories/", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryWO>> listAllInventory() {
		List<InventoryWO> inventories = inventoryService.findAllInventories();
		if (inventories.isEmpty()) {
			return new ResponseEntity<List<InventoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InventoryWO>>(inventories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInventoriesByIdStore/{idStore}", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryWO>> listAllInventory(@PathVariable("idStore") byte idStore) {
		List<InventoryWO> inventories = inventoryService.findAllInventoriesByIdStore(idStore);
		if (inventories.isEmpty()) {
			return new ResponseEntity<List<InventoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InventoryWO>>(inventories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInventoryByIdFilm/{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getInventoryByIdFilm(@PathVariable("id") int id) {

		List<InventoryWO> inventories = inventoryService.findAllInventoriesByIdFilm(id);

		List<Integer> listInventory = new ArrayList<Integer>();

		if (inventories.size() > 0) {
			for (InventoryWO inv : inventories) {
				listInventory.add(inv.getInventoryId());
			}

			List<RentalWO> toto = rentalService.findByIdInventory(listInventory);

			if (toto.isEmpty()) {
				return new ResponseEntity<Integer>(inventories.size(), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Integer>(inventories.size() - toto.size(), HttpStatus.CREATED);
			}
		} else {
			return new ResponseEntity<Integer>(new Integer(-1), HttpStatus.CREATED);
		}

	}

	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/createInventory/", method = RequestMethod.POST)
	public ResponseEntity<Void> createInventory(@RequestBody InventoryWO inventoryWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating City " + inventoryWO.getInventoryId());
		inventoryService.saveInventory(inventoryWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventoryWO.getInventoryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteInventory/{id}", method = RequestMethod.GET)
	public ResponseEntity<InventoryWO> deleteInventory(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Inventory with id " + id);

		InventoryWO inventory = inventoryService.findById(id);

		if (inventory == null) {
			System.out.println("Unable to delete. Inventory with id " + id + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}
		System.out.println("before deleting");
		inventoryService.deleteInventoryById(id);
		System.out.println("after deleting");
		return new ResponseEntity<InventoryWO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/deleteInventoryByIdFilm/{id}", method = RequestMethod.GET)
	public ResponseEntity<InventoryWO> deleteInventoryByIdFilm(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Inventory with id " + id);

		InventoryWO inventory = inventoryService.findById(id);

		if (inventory == null) {
			System.out.println("Unable to delete. Inventory with id " + id + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}
		System.out.println("before deleting");
		inventoryService.deleteInventoryById(id);
		System.out.println("after deleting");
		return new ResponseEntity<InventoryWO>(HttpStatus.NO_CONTENT);
	}

}
