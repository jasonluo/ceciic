package org.ceciic.mongo;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.DB;
import com.mongodb.MongoException;


/**
 * http://www.mkyong.com/spring/spring-auto-scanning-components/
 * http://blogs.sourceallies.com/2011/08/spring-injection-with-resource-and-autowired/
 * 
 * use @Service, @Repository, @Component, or @Controller tag for auto scan and create proxy
 * @Component("beanName") to explicitly name the bean
 * 
 * use @Resource, @Autowired or @Inject to perform auto dependency injection
 * @Resource(name="beanName") can explicitly refer a bean dependency
 * */
@Service("userService")
@Transactional
public class UserService {
	private static final Log log = LogFactory.getLog(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Resource(name="mongoTemplate")
	private MongoOperations mongoOperations; 

	public void doSomething() {
		log.debug("userRepository=="+userRepository);
	
		mongoOperations.executeInSession(new DbCallback<User>(){
			@Override
			public User doInDB(DB db) throws MongoException, DataAccessException {
				db.getCollection("User").drop();
				return null;
			}
		}
	);
		
		for(int i=0; i<5; i++){
			User p = new User("Smith", "John"+i, i);
			userRepository.save(p);
		}

		

//		List<Person> people =  repository.findAll();
//		log.debug("people=="+people);  
//		
//		for(Person person: people){
//			log.debug("FirstName="+person.getFirstName()+";  age==="+person.getAge());
//		}
//	
//		log.debug("people size=="+people.size()); 
//		
//		Car honda = new Car("Honda","Accord",2001);
//		carRepository.save(honda);
//		List <Car> cars = carRepository.findAll();
//		log.debug("car size=="+cars.size());  
//		for(Car car: cars){
//			log.debug("car model"+car.getLastName()+";  id="+car.getId());
//		}
//		List<Person> persons = repository.findByLastName("Smith");
//		log.debug("persons=="+persons);
//	
//		List<Person> personsAND = repository.findAND("Smith", "Johnny");
//		log.error("personsAND=="+personsAND);
//		
//		List<Person> personsOR = repository.findOR("Smith", "John2");
//		log.error("personsOR=="+personsOR);
	
		
		
		//Criteria criteria = new Criteria();
		
		//Query query = new Query(criteria).addCriteria(criteria);
		//query.sort();
		//mongoTemplate.find(query, Person.class);
		
		
	}
	
	
	public List <User> findAll(){
		return userRepository.findAll();
	}
	
	public static void main(String [] args){
		com.mongodb.util.JSON.parse("{ $or : [{lastName : 'dd'}, {firstName : 'ee'}] }");
	}
}
