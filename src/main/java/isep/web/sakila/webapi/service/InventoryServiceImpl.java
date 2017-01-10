package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.InventoryRepository;
import isep.web.sakila.dao.repositories.StoreRepository;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Store;
import isep.web.sakila.webapi.model.InventoryWO;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private StoreRepository storeRepository;

	private static final Log log = LogFactory.getLog(InventoryServiceImpl.class);

	@Override
	public List<InventoryWO> findAllInventories() {
		List<InventoryWO> inventories = new LinkedList<InventoryWO>();

		for (Inventory inventory : inventoryRepository.findAll()) {
			inventories.add(new InventoryWO(inventory));
		}

		return inventories;
	}

	@Override
	public List<InventoryWO> findAllInventoriesByIdStore(byte idStore) {
		List<InventoryWO> inventories = new LinkedList<InventoryWO>();

		Store store = storeRepository.findOne(idStore);
		for (Inventory inventory : inventoryRepository.findAllInventoriesByIdStore(store)) {
			inventories.add(new InventoryWO(inventory));
		}

		return inventories;
	}

	;

	@Override
	public List<InventoryWO> findAllInventoriesByIdFilm(int id_film) {
		List<InventoryWO> inventories = new LinkedList<InventoryWO>();

		for (Inventory inventory : inventoryRepository.findAllInventoriesByIdFilm(filmRepository.findOne(id_film))) {
			inventories.add(new InventoryWO(inventory));
		}

		return inventories;
	}

	@Override
	public InventoryWO findById(int id) {
		System.out.println(String.format("Looking for inventory by Id %s", id));
		Inventory inventory = inventoryRepository.findOne(id);

		if (inventory != null) {
			return new InventoryWO(inventory);
		}
		return null;
	}

	@Override
	public void saveInventory(InventoryWO inventoryWO) {
		Inventory inventory = new Inventory();

		Film film = filmRepository.findOne(inventoryWO.getFilmId());
		Store store = storeRepository.findOne(inventoryWO.getStoreId());

		inventory.setFilm(film);
		inventory.setStore(store);
		inventory.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		inventoryRepository.save(inventory);
	}

	@Override
	public void deleteInventoryById(int id) {
		inventoryRepository.delete(id);
	}

}