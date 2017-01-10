package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Inventory;

public class InventoryWO extends WebObject {

	private static final long serialVersionUID = -1377067679473844279L;

	protected int inventoryId;
	protected int filmId;
	protected byte storeId;

	public InventoryWO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryWO(int inventoryId, int filmId, byte storeId) {
		super();
		this.inventoryId = inventoryId;
		this.filmId = filmId;
		this.storeId = storeId;
	}

	public InventoryWO(final Inventory inventory) {
		super();
		this.inventoryId = inventory.getInventoryId();
		this.filmId = inventory.getFilm().getFilmId();
		this.storeId = inventory.getStore().getStoreId();
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public byte getStoreId() {
		return storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

}
