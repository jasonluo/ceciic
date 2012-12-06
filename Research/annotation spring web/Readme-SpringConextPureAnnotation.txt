==========need servlet 3.0 (tomcat 7 or glassfish) and java 1.6 and over =================
if "run out of memeory" -XX:PermSize=64m -XX:MaxPermSize=128m for Tomcat jdk start


//Spring context annotation
http://stackoverflow.com/questions/6276561/spring-how-annotationconfigwebapplicationcontext-could-not-overwrite-later-bean
http://www.coderanch.com/t/516087/Spring/Super-Simple-Servlet-API-Based
http://www.theserverside.com/tutorial/How-to-Use-Spring-30-In-a-Servlet-Based-Web-Application


//servlet 3.0 annotation
https://blogs.oracle.com/swchan/entry/servlet_3_0_annotations
http://explodingjava.blogspot.com/2010/05/servlet-30-annotations.html


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


===========Servlet 3.0 annotation vs JSF Annotation vs JEE annotation vs Spring annotation ====================
1. Servelt 3.0 annotation is controlled by web application deployment descriptor (web.xml + web-fragment.xml) 
   when metadata-complete="true" in web-fragment.xml, servlet container igores scanning that jar.
   however, web app classes will always be scanned.
   http://stackoverflow.com/questions/9820379/what-to-do-with-annotations-after-setting-metadata-complete-true-which-resolv

   all servelt3.0 annotations are under package javax.servlet.annotation

2. JSF annotation is controlled by JSF servlet declared in web.xml
       <servlet>
           <servlet-name>FacesServlet</servlet-name>
           <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
           <load-on-startup>0</load-on-startup>
       </servlet>
   It appears that JSF servelt only scans classes within the the web app, it NEVER scans any jars. If needed, use src/META_INF/faces-config.xml 
   JSF annotation overlaps JEE annotation but can be used outside EJB container. 
   all JSF annotations are prefix by "javax.faces"
   

3. JEE annotation will only works within JEE server.  all jee annotation are under package javax.annotation 
    how this is controlled? (in ejb depoyment descriptor??)

4. Spring annotation is contolled by AnnotationConfigWebApplicationContext, which is defined in web.xml like this:
    <context-param>  
	   <param-name>contextClass</param-name>  
	   <param-value>  
	   		org.springframework.web.context.support.AnnotationConfigWebApplicationContext  
	  </param-value>  
   </context-param>
   <context-param>  
	  <param-name>contextConfigLocation</param-name>  
	  <param-value>com.oolong.jieming.app; com.oolong.jieming.service</param-value>  
   </context-param>
   ....
   <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   </listener>

   OR if use XMLWebApplicationContext by using default "contextClass" parameter,  
   the annotation can be scanned by using:
   <context:component-scan base-package="test.example"/>
   <context:annotation-config/> 

5. JEE annotations under "javax.ws.rs" shall work within JEE app server(??).
   could be used outside JEE server using jersey project   

6. Hibernate annotations
   http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/ch01.html
   1. sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
   2. control package in hibernate.cfg.xml
      <hibernate-configuration>
           <session-factory>
              <mapping package="test.animals"/>
              <mapping class="test.Flight"/>
               <mapping class="test.Sky"/>
               <mapping class="test.Person"/>
               <mapping class="test.animals.Dog"/>

              <mapping resource="test/animals/orm.xml"/>
      </session-factory>
      </hibernate-configuration>
   3. can mix annotation and hbm.xml files.

7. JPA annotations. 
   1. can be used outside JEE server.
   2. can be mixed with hibernate hbm.xml files. 