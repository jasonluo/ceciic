package org.ceciic.mongo;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.DB;
import com.mongodb.MongoException;


@Service("blogService")
@Transactional
public class BlogService {
	private static final Log log = LogFactory.getLog(BlogService.class);
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Resource(name="mongoTemplate")
	private MongoOperations mongoOperations; 
	
	public List <Blog> findAllBlogs(){
		Sort sort = new Sort(Sort.Direction.DESC, "postDate");
		return blogRepository.findAll(sort);
	}
	
	public void saveBlog(Blog blog){
		log.debug("save blog=="+blog.getText());
		blogRepository.save(blog);
	}
	
	//http://www.mkyong.com/mongodb/spring-data-mongodb-hello-world-example/
	public void delete(String blogId){
		log.debug("delete blogId=="+blogId);
		
		mongoOperations.remove(new Query(
                Criteria.where("id").is(blogId)
            ), "blog");
	}
	
	public Blog findOne(String blogId){
		log.debug("find=="+blogId);
		Blog blog = blogRepository.findOne(blogId);
		return blog;
		 
	}
	
	public void dropAll() {
		mongoOperations.executeInSession(new DbCallback<Blog>(){
				@Override
				public Blog doInDB(DB db) throws MongoException, DataAccessException {
					Set <String> names = db.getCollectionNames();
					for(String name: names){
						log.debug("collection=="+name);
					}
					db.getCollection("blog").drop();
					return null;
				}
			}
		);
	}
}
