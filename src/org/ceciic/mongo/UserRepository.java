package org.ceciic.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserRepository extends MongoRepository <User, String> {

	List<User> findByLastName(String lastname);
	
	@Query(value="{ 'lastName' : ?0, 'firstName' : ?1 }")
	List<User> findAND(String lastname, String firstName);
	
	@Query(value="{$or: [{'lastName' : ?0}, {'firstName' : ?1}] }")
	List<User> findOR(String lastname, String firstName);
}
