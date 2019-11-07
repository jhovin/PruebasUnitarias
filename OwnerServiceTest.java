package com.tecsup.petclinic.service;


import static org.junit.Assert.assertEquals;

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



}
