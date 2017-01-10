package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.jpa.entities.Store;

public class StoreWO extends WebObject {

	protected byte storeId;
	protected Staff staff;
	protected Address address;

	public StoreWO() {
		super();
	}

	public StoreWO(byte storeId, Staff staff, Address address) {
		super();
		this.storeId = storeId;
		this.staff = staff;
		this.address = address;
	}

	public StoreWO(final Store store) {
		super();
		this.storeId = store.getStoreId();
		this.staff = store.getStaff();
		this.address = store.getAddress();
	}

	public byte getStoreId() {
		return storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
