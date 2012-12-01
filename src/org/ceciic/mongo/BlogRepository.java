package org.ceciic.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlogRepository  extends MongoRepository <Blog, String> {
	List<Blog> findByAuthor(String author);
	
	List<Blog> findByText(String author);
	
	@Query(value="{ 'lastName' : ?0, 'firstName' : ?1 }")
	List<Blog> findAND(String lastname, String firstName);
	
	@Query(value="{$or: [{'lastName' : ?0}, {'firstName' : ?1}] }")
	List<Blog> findOR(String lastname, String firstName);
}
