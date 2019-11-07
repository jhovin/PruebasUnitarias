package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	*
	*/
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String FIRST_NAME = "George";
		Owner owner = null;

		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(FIRST_NAME, owner.getFirst_name());

	}

	@Test
	public void testCreateOwner() {

		String OWNER_NAME = "Jhovin";
		String OWNER_LAST = "Boni";
		String OWNER_ADDRESS = "Av.Los Cedros Mz LT 23";
		String OWNER_CITY = "Lima";
		String OWNER_TELEPHONE = "6085551023";

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		assertThat(owner.getId()).isNotNull();
		assertEquals(OWNER_NAME, owner.getFirst_name());
		assertEquals(OWNER_LAST, owner.getLast_name());
		assertEquals(OWNER_ADDRESS, owner.getAddress());
		assertEquals(OWNER_CITY, owner.getCity());
		assertEquals(OWNER_TELEPHONE, owner.getTelephone());

	}

	@Test
	public void testUpdateOwner() {

		String OWNER_NAME = "Betty";
		String OWNER_LAST = "Davis";
		String OWNER_ADDRESS = "638 Cardinal Ave.";
		String OWNER_CITY = "Sun Prairie";
		String OWNER_TELEPHONE = "6085551023";
		long create_id = -1;

		String UP_OWNER_NAME = "Betty 2";
		String UP_OWNER_LAST = "Jaya";
		String UP_OWNER_ADDRESS = "700 Cardinal Ave.";
		String UP_OWNER_CITY = "New York";
		String UP_OWNER_TELEPHONE = "6256215254";

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);

		// Create record
		logger.info(">" + owner);
		Owner readOwner = ownerService.create(owner);
		logger.info(">>" + readOwner);

		create_id = readOwner.getId();

		// Prepare data for update
		readOwner.setFirst_name(UP_OWNER_NAME);
		readOwner.setLast_name(UP_OWNER_LAST);
		readOwner.setAddress(UP_OWNER_ADDRESS);
		readOwner.setCity(UP_OWNER_CITY);
		readOwner.setTelephone(UP_OWNER_TELEPHONE);

		// Execute update
		Owner upgradeOwner = ownerService.update(readOwner);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id).isNotNull();
		assertEquals(create_id, upgradeOwner.getId());
		assertEquals(UP_OWNER_NAME, upgradeOwner.getFirst_name());
		assertEquals(UP_OWNER_LAST, upgradeOwner.getLast_name());
		assertEquals(UP_OWNER_ADDRESS, upgradeOwner.getAddress());
		assertEquals(UP_OWNER_CITY, upgradeOwner.getCity());
		assertEquals(UP_OWNER_TELEPHONE, upgradeOwner.getTelephone());
	}

	@Test
	public void testDeleteOwner() {

		String OWNER_NAME = "Jeff";
		String OWNER_LAST = "Black";
		String OWNER_ADDRESS = "1450 Oak Blvd.";
		String OWNER_CITY = "Monona";
		String OWNER_TELEPHONE = "6085555387";

		Owner owner = new Owner(OWNER_NAME, OWNER_LAST, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			ownerService.findById(owner.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}

	}

}
