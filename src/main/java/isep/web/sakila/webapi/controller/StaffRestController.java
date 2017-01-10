package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.webapi.model.StaffWO;
import isep.web.sakila.webapi.service.StaffService;

@RestController
public class StaffRestController {

	@Autowired
	StaffService staffService;
	private static final Log log = LogFactory.getLog(StaffRestController.class);

	// -------------------Sign in ----------------------------------

	@RequestMapping(value = "/staff/", method = RequestMethod.POST)
	public ResponseEntity<StaffWO> signInStaff(@RequestBody StaffWO staffWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Sign in staff " + staffWO.getUsername());
		Staff staff = staffService.signInStaff2(staffWO.getUsername(), staffWO.getPassword());
		StaffWO staffWOreturn = null;
		if (staff != null) {
			System.out.println("logging");
			staffWOreturn = new StaffWO(staff);
		} else {
			staffWOreturn = new StaffWO();
			System.out.println(" pas logging");
		}
		return new ResponseEntity<StaffWO>(staffWOreturn, HttpStatus.OK);

	}

	@RequestMapping(value = "/getstaffs/", method = RequestMethod.GET)
	public ResponseEntity<List<StaffWO>> listAllStaffs() {
		List<StaffWO> staffs = staffService.findAllStaffs();
		if (staffs.isEmpty()) {
			return new ResponseEntity<List<StaffWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StaffWO>>(staffs, HttpStatus.OK);
	}

}
