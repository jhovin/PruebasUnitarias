package com.tecsup.petclinic.service;

import java.util.List;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

public interface OwnerService {
	/**
	 * 
	 * @param owner
	 * @return
	 */
	Owner create(Owner owner);

	/**
	 * 
	 * @param pet
	 * @return
	 */
	Owner update(Owner owner);

	/**
	 * 
	 * @param id
	 * @throws PetNotFoundException
	 */
	void delete(Long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	Owner findById(long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Owner> findByName(String first_name);
	
	List<Owner> findByLast(String last_name);
	
	List<Owner> findByAddress(String address);
	
	List<Owner> findByCity(String city);
	
	List<Owner> findByTelephone(String telephone);
	
	
	



	/**
	 * 
	 * @param typeId
	 * @return
	 */
	List<Owner> findByTypeId(int id);


	/**
	 * 
	 * @return
	 */
	Iterable<Owner> findAll();
}
